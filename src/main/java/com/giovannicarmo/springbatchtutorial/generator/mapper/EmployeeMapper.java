package com.giovannicarmo.springbatchtutorial.generator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.giovannicarmo.springbatchtutorial.domain.Employee;
import com.giovannicarmo.springbatchtutorial.generator.valueobject.EmployeeVO;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "employeeId", target = "employeeId")
    @Mapping(target = "fullName", expression = "java(employee.getFirstName() + \" \" + employee.getLastName())")
    EmployeeVO toVO(Employee employee);
}
