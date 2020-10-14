/*
Acesta este mainul pentru varianta good, acesta l-am pus in folderul good, iar main-ul bad l-am lasat unde era.
 */

package com.solid.i.good;
import java.util.Date;
import com.solid.i.Invoice;
import com.solid.i.ComplexInvoice;


public class IMain {
    public static void main(String[] args) {
        testBadI();
    }

    private static void testBadI() {
        Invoice invoice = new Invoice(-132523);
        ComplexInvoice complexInvoice = new ComplexInvoice(21439, new Date());


        GoodInvoicePrinter_I badInvoicePrinter = new GoodInvoicePrinter();
        badInvoicePrinter.print(invoice);
        badInvoicePrinter.printComplexInvoice(complexInvoice);
        //badInvoicePrinter.someOtherPrintMethod(invoice); //why should I be able to do this?


        GoodInvoicePrinter_I_Other someOtherBadInvoicePrinter = new SomeOtherGoodInvoicePrinter();
        //someOtherBadInvoicePrinter.print(invoice); //why should I be able to do this?
        //someOtherBadInvoicePrinter.printComplexInvoice(complexInvoice); //why should I be able to do this?
        someOtherBadInvoicePrinter.someOtherPrintMethod(invoice);
    }
}
