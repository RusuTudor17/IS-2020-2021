/*
Acesta este mainul pentru varianta good, acesta l-am pus in folderul good, iar main-ul bad l-am lasat unde era.
 */
package com.solid.s.good;

public class SMain {
    public static void main(String[] args) {
        goodTest();
    }

    private static void goodTest() {
        GoodEmployee employee = new GoodEmployee("1", "BadEmployee", 5);
        EmployeesPaySistem paySystem=new EmployeesPaySistem(employee);
        EmployeesEvidence evidenceSystem = new EmployeesEvidence(employee);


        System.out.println(paySystem.calculatePay());
        System.out.println(employee.reportHours());
        evidenceSystem.save();
    }
}
