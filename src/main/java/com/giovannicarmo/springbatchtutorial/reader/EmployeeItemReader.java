package com.giovannicarmo.springbatchtutorial.reader;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;

import com.giovannicarmo.springbatchtutorial.domain.Employee;
import com.giovannicarmo.springbatchtutorial.repository.EmployeeRepository;

public class EmployeeItemReader implements ItemReader<Employee> {

    private final EmployeeRepository employeeRepository;
    private Iterator<Employee> employeeIterator;

    public EmployeeItemReader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee read() {
        if (employeeIterator == null) {
            List<Employee> employees = employeeRepository.findAll();
            employeeIterator = employees.iterator();
        }

        if (employeeIterator.hasNext()) {
            return employeeIterator.next();
        } else {
            return null;
        }
    }
}
