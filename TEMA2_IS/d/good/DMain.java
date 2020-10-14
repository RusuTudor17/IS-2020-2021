/*
Acesta este mainul pentru varianta good, acesta l-am pus in folderul good, iar main-ul bad l-am lasat unde era.
 */

package com.solid.d.good;

import com.solid.i.good.GoodInvoicePrinter;

import com.solid.i.Invoice;


public class DMain {
    public static void main(String[] args) {
        testBadD();
    }

    private static void testBadD() {
        Invoice invoice = new Invoice(665);

       // System.out.println("salut");
        GoodPrintingService goodPrintingService = new GoodPrintingService(new GoodInvoicePrinter());
        goodPrintingService.print(invoice);


        goodPrintingService = new GoodPrintingService(new HtmlInvoicePrinter()); // but why!?
        goodPrintingService.print(invoice);
    }
}
