package oop;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Employee {
    public static final String DEFAULT_NAME = "UNKNOWN";
    private static int nextId;   //static (class) attribute

    private Integer id;
    private String name;
    private LocalDate hireDate;

    public Employee() {
        this(DEFAULT_NAME);  //this as a method
    } //default

    public Employee(String name) {
        id = nextId++;
        this.name = name;
        hireDate = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract double getPay();  // abstract method

    @Override
    public String toString() {

        return String.format("Employee{id=%d, name='%s', hireDate=%s}"
                , id, name, hireDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  //equals implementation in Object
        if (!(o instanceof Employee)) return false; //needed if subclass of Employee can be equal to Employee

        Employee employee = (Employee) o; //casting

        if (id != null ? !id.equals(employee.id) : employee.id != null) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        return hireDate != null ? hireDate.equals(employee.hireDate) : employee.hireDate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        return result;
    }

// intellij generated  with defaults
/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(hireDate, employee.hireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hireDate);
    }
*/
}

