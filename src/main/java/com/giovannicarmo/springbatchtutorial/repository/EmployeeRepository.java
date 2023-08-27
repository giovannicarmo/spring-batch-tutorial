package com.giovannicarmo.springbatchtutorial.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.giovannicarmo.springbatchtutorial.domain.entity.Employee;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM employees", employeeRowMapper);
    }

    private final RowMapper<Employee> employeeRowMapper = (resultSet, rowNum) -> {
        Employee employee = new Employee();
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setAge(resultSet.getInt("age"));
        employee.setEmployeeId(resultSet.getString("employee_id"));
        employee.setPosition(resultSet.getString("position"));
        return employee;
    };
}
