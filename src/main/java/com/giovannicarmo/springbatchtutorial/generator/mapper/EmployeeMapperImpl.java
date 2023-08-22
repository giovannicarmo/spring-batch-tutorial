package com.giovannicarmo.springbatchtutorial.generator.mapper;

import org.springframework.stereotype.Component;

import com.giovannicarmo.springbatchtutorial.domain.Employee;
import com.giovannicarmo.springbatchtutorial.generator.valueobject.EmployeeVO;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeVO toVO(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setEmployeeId(employee.getEmployeeId());
        employeeVO.setFullName(employee.getFirstName() + " " + employee.getLastName());
        // Map other properties as needed

        return employeeVO;
    }

}
