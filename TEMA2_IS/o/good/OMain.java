/*
Acesta este mainul pentru varianta good, acesta l-am pus in folderul good, iar main-ul bad l-am lasat unde era.
 */
package com.solid.o.good;

public class OMain {
    public static void main(String[] args) {
        testBadO();
    }

    private static void testBadO() {
        GeneralClient client = new GoodClient();
        GoodServer server = new GoodServer();
        server.reactToClient(client);

        //System.out.println("salut");
        GeneralClient anotherBadClient = new AnotherGoodClient();
        server.reactToClient(anotherBadClient);


    }

}
