package com.giovannicarmo.springbatchtutorial.domain;

public class Employee extends Person {
    private String employeeId;
    private String position;

    public Employee() {
    }

    public Employee(String firstName, String lastName, int age, String employeeId, String position) {
        super(firstName, lastName, age);
        this.employeeId = employeeId;
        this.position = position;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee [firstName: " + firstName + ", lastName: " + lastName + " age: " + age + " employeeId: "
                + employeeId + ", position: " + position + "]";
    }

}
