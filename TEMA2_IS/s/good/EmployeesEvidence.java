package com.solid.s.good;

public class EmployeesEvidence {
    private GoodEmployee employee;
    public EmployeesEvidence(GoodEmployee employee)
    {
        this.employee=employee;
    }

    public void save() {
        System.out.printf("%s saved to database.\n", employee.getName());
    }
}
