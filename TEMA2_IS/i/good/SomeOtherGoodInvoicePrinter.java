package com.solid.i.good;


import com.solid.i.Invoice;
public class SomeOtherGoodInvoicePrinter implements GoodInvoicePrinter_I_Other{

    @Override
    public void someOtherPrintMethod(Invoice invoice) {
        System.out.println("Printing the invoice in a totally different way "
                + invoice);
    }
}
