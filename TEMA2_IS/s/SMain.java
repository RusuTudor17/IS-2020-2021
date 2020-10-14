package com.solid.s;

import com.solid.s.bad.BadEmployee;

public class SMain {

    public static void main(String[] args) {
        testBadS();
    }

    private static void testBadS() {
        BadEmployee employee = new BadEmployee("1", "BadEmployee", 5);
        employee.calculatePay();
        employee.reportHours();
        employee.save();
    }
}
