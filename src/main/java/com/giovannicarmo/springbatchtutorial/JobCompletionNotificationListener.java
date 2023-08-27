package com.giovannicarmo.springbatchtutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import com.giovannicarmo.springbatchtutorial.repository.EmployeeRepository;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EmployeeRepository repository;

    public JobCompletionNotificationListener(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            repository.findAll()
                    .forEach(employee -> log.info("Found <{{}}> in the database.", employee));
        }
    }
}
