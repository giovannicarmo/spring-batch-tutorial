package com.giovannicarmo.springbatchtutorial.generator.xml;

import java.util.Set;

import com.giovannicarmo.springbatchtutorial.domain.dto.EmployeeDTO;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EmployeesOfTheMonth")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlEmployee extends XmlWrapper<EmployeeDTO> {

    public XmlEmployee() {
        super();
    }

    public XmlEmployee(XmlWrapper.Header header, Set<EmployeeDTO> employees) {
        super(header, employees);
    }
}
