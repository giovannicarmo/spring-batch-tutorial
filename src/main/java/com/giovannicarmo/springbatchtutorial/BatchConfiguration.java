package com.giovannicarmo.springbatchtutorial;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;

import com.giovannicarmo.springbatchtutorial.domain.dto.EmployeeDTO;
import com.giovannicarmo.springbatchtutorial.domain.entity.Employee;
import com.giovannicarmo.springbatchtutorial.processor.EmployeeConverterProcessor;
import com.giovannicarmo.springbatchtutorial.processor.EmployeeItemProcessor;
import com.giovannicarmo.springbatchtutorial.reader.EmployeeItemReader;
import com.giovannicarmo.springbatchtutorial.repository.EmployeeRepository;
import com.giovannicarmo.springbatchtutorial.ultils.XmlCustomHeaderCallback;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

@Configuration
public class BatchConfiguration {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.giovannicarmo.springbatchtutorial.domain.dto");
        marshaller.setMarshallerProperties(Collections.singletonMap(Marshaller.JAXB_FRAGMENT, true));
        return marshaller;
    }

    @Bean
    public EmployeeItemProcessor processor() {
        return new EmployeeItemProcessor();
    }

    @Bean
    public EmployeeConverterProcessor employeeConverterProcessor() {
        return new EmployeeConverterProcessor();
    }

    @Bean
    public EmployeeItemReader employeeItemReader() {
        return new EmployeeItemReader(employeeRepository);
    }

    @Bean
    @SuppressWarnings("unchecked")
    public XmlWritter<EmployeeDTO> employeeXmlWritter() {
        return new XmlWritter();
    }

    @Bean
    public FlatFileItemReader<Employee> reader() {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("employeeItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names(new String[] { "firstName", "lastName", "age", "employeeId", "position" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {
                    {
                        setTargetType(Employee.class);
                    }
                })
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Employee> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Employee>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO employees (first_name, last_name, age, employee_id, position) VALUES (:firstName, :lastName, :age, :employeeId, :position)")
                .dataSource(dataSource)
                .build();
    }

    @Bean(destroyMethod = "")
    public StaxEventItemWriter<EmployeeDTO> employeeWriter() throws JAXBException {

        FileSystemResource resource = new FileSystemResource("employees.xml");

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(EmployeeDTO.class);

        return new StaxEventItemWriterBuilder<EmployeeDTO>()
                .name("employeeWriter")
                .marshaller(marshaller)
                .resource(resource)
                .rootTagName("EmployeesOfMonth")
                .headerCallback(new XmlCustomHeaderCallback())
                .overwriteOutput(true)
                .build();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository,
            JobCompletionNotificationListener listener, Step persistenceStep) {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                // .listener(listener)
                .start(persistenceStep)
                .next(exportToXmlStep(jobRepository, null, null))
                .build();
    }

    @Bean
    public Step persistenceStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
            JdbcBatchItemWriter<Employee> writer) {
        return new StepBuilder("persistenceStep", jobRepository)
                .<Employee, Employee>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public Step exportToXmlStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
            final ItemProcessor<Employee, EmployeeDTO> processor) {
        return new StepBuilder("exportToXmlStep", jobRepository)
                .<Employee, EmployeeDTO>chunk(10, transactionManager)
                .reader(employeeItemReader())
                .processor(processor)
                .writer(employeeXmlWritter())
                .build();
    }
}
