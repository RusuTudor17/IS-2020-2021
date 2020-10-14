package com.solid.s.good;

public class EmployeesPaySistem {
    private GoodEmployee employee;
    public EmployeesPaySistem(GoodEmployee employee)
    {
        this.employee=employee;
    }

    public int calculatePay() {
        switch (this.employee.getStatus()) {
            case "A":
                return 1;
            case "B":
                return 2;
            default:
                return 0;
        }
    }
}
