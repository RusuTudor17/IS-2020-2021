package repository.client;


import model.Client;
import model.Cont;
import model.builder.ClientConstructor;
import model.builder.ContConstructor;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.EntityNotFoundException;
import repository.cont.ContRepository;
import repository.cont.ContRepositoryCacheDecorator;
import repository.cont.ContRepositoryMock;

import java.sql.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ClientRepositoryMockTest {
    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setupClass(){
        clientRepository= new ClientRepositoryCacheDecorator(
                new ClientRepositoryMock(),
                new Cache<>());
    }



    @Test
    public void save(){
        Client client= new ClientConstructor()
                .setId(1L)
                .setNume("Rusu Tudor")
                .setAdresa("Cluj-Napoca, cartierul Gheorgheni")
                .setCodNumericPersonal("1234567890123")
                .setIdCardNumber(1234).build();


        assertTrue(clientRepository.save(client));
    }



    @Test
    public void update(){
        Client client= new ClientConstructor()
                .setId(1L)
                .setNume("Rusu Tudor")
                .setAdresa("Cluj-Napoca, cartierul Gheorgheni")
                .setCodNumericPersonal("1234567890123")
                .setIdCardNumber(1234).build();


        clientRepository.save(client);

        assertTrue(clientRepository.update("Rusu Andrei","Bucuresti","1234567890123",3241));
    }


    @Test(expected = EntityNotFoundException.class)
    public void findByCNP() throws EntityNotFoundException {
        Client client= new ClientConstructor()
                .setId(1L)
                .setNume("Rusu Tudor")
                .setAdresa("Cluj-Napoca, cartierul Gheorgheni")
                .setCodNumericPersonal("1234567890123")
                .setIdCardNumber(1234).build();


        clientRepository.save(client);
        assertNotNull(clientRepository.findByCNP("1231567890123"));
    }

    @Test
    public void existaClientDupaCNP(){
        Client client= new ClientConstructor()
                .setId(1L)
                .setNume("Rusu Tudor")
                .setAdresa("Cluj-Napoca, cartierul Gheorgheni")
                .setCodNumericPersonal("1234567890123")
                .setIdCardNumber(1234).build();


        clientRepository.save(client);
        assertTrue(clientRepository.existaClientDupaCNP("1234567890123"));
    }

    @Test
    public void idClientByCNP(){
        Client client= new ClientConstructor()
                .setId(1L)
                .setNume("Rusu Tudor")
                .setAdresa("Cluj-Napoca, cartierul Gheorgheni")
                .setCodNumericPersonal("1234567890123")
                .setIdCardNumber(1234).build();


        clientRepository.save(client);
        assertTrue(clientRepository.idClientByCNP("1234567890123").equals(1L));
    }
}
