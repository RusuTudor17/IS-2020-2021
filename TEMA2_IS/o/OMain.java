package com.solid.o;

import com.solid.o.bad.AnotherBadClient;
import com.solid.o.bad.BadClient;
import com.solid.o.bad.BadServer;

public class OMain {

    public static void main(String[] args) {
        testBadO();
    }

    private static void testBadO() {
        BadClient client = new BadClient();
        BadServer server = new BadServer();
        server.reactToClient(client);

        AnotherBadClient anotherBadClient = new AnotherBadClient();
//        server.reactToClient(anotherBadClient);


    }
}
