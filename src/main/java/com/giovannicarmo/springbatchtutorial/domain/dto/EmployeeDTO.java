package com.giovannicarmo.springbatchtutorial.domain.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeDTO {

    @XmlElement(name = "EMPLOYEE_REGISTRATION_ID")
    private String employeeId;

    @XmlElement(name = "EMPLOYEE_NAME")
    private String fullName;

    @XmlElement(name = "EMPLOYEE_YEARS_OLD")
    private int age;

    @XmlElement(name = "EMPLOYEE_ROLE")
    private String position;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String employeeId, String fullName, int age, String position) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.age = age;
        this.position = position;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String firstName) {
        this.fullName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmployeeDTO other = (EmployeeDTO) obj;
        if (employeeId == null) {
            if (other.employeeId != null)
                return false;
        } else if (!employeeId.equals(other.employeeId))
            return false;
        return true;
    }

}