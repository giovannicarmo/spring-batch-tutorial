package com.giovannicarmo.springbatchtutorial.generator.xml;

import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.lang.NonNull;

import com.giovannicarmo.springbatchtutorial.generator.valueobject.EmployeeVO;

public class XmlItemWriter implements ItemWriter<EmployeeVO> {

    private final XmlGenerator xmlGenerator;

    public XmlItemWriter(XmlGenerator xmlGenerator) {
        this.xmlGenerator = xmlGenerator;
    }

    @Override
    public void write(@NonNull Chunk<? extends EmployeeVO> chunk) throws Exception {
        Set<EmployeeVO> employeeVOs = new HashSet<>(chunk.getItems());
        xmlGenerator.generate("yourOperationId", employeeVOs);
    }
}
