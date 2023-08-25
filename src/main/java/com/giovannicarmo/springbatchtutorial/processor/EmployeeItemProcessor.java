package com.giovannicarmo.springbatchtutorial.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

import com.giovannicarmo.springbatchtutorial.domain.entity.Employee;

public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {

    private static final Logger log = LoggerFactory.getLogger(EmployeeItemProcessor.class);

    @Override
    public Employee process(@NonNull Employee employee) throws Exception {
        final String firstName = employee.getFirstName().toUpperCase();
        final String lastName = employee.getLastName().toUpperCase();
        final String employeeId = employee.getEmployeeId().toUpperCase();
        final String position = employee.getPosition().toUpperCase();

        final Employee transformedEmployee = new Employee(firstName, lastName, employee.getAge(), employeeId, position);

        log.info("Converting (" + employee + ") into (" + transformedEmployee + ")");

        return transformedEmployee;
    }
}
