package service.client;

import database.FactoryDataBase;
import model.Client;
import model.builder.ClientConstructor;
import model.validation.Notification;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryCacheDecorator;
import repository.client.ClientRepositoryMySQL;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ClientServiceImplTest {
    private static ClientService clientService;

    @BeforeClass
    public static void setupClass(){
        ClientRepository clientRepository= new ClientRepositoryCacheDecorator(
                new ClientRepositoryMySQL(new FactoryDataBase().getConnectionWrapper(true).getConnection()),
                new Cache<>());
        clientService= new ClientServiceImpl(clientRepository);
    }



    @Test
    public void save(){
        boolean bool= clientService.save("Rusu Tudor","Cluj-Napoca, cartierul Gheorgheni","1234567890123","5234").getResult();
        assertTrue(bool);

    }

    @Test
    public void update(){
        boolean bool= clientService.update("Rusu Tudor Marcel","Cluj-Napoca, cartierul Gheorgheni Bucuresti","1234567890123","5234").getResult();
        assertTrue(bool);
    }

    @Test
    public void findByCnp() throws EntityNotFoundException {

        assertNotNull(clientService.findByCNP("1234567890123"));
    }



}
