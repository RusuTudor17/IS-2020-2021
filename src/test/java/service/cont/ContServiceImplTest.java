package service.cont;

import database.FactoryDataBase;
import model.Cont;
import model.builder.ClientConstructor;
import model.validation.Notification;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryCacheDecorator;
import repository.client.ClientRepositoryMySQL;
import repository.cont.ContRepository;
import repository.cont.ContRepositoryCacheDecorator;
import repository.cont.ContRepositoryMySQL;
import service.client.ClientService;
import service.client.ClientServiceImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ContServiceImplTest {
    private static ContService contService;

    @BeforeClass
    public static void setupClass(){
        ContRepository contRepository= new ContRepositoryCacheDecorator(
                new ContRepositoryMySQL(new FactoryDataBase().getConnectionWrapper(true).getConnection()),
                new Cache<>());

        ClientRepository clientRepository= new ClientRepositoryCacheDecorator(
                new ClientRepositoryMySQL(new FactoryDataBase().getConnectionWrapper(true).getConnection()),
                new Cache<>());
        clientRepository.save(new ClientConstructor().setNume("Rusu Tudor").setAdresa("Cluj-Napoca").setCodNumericPersonal("3210987654321").setIdCardNumber(521).build());
        contService= new ContServiceImpl(contRepository,clientRepository);
    }



    @Test
    public void save(){
        boolean bool= contService.save("3421","3210987654321","Savings","5234.32","2000-01-01").getResult();
        assertTrue(bool);
    }



    @Test
    public void update(){
        boolean bool= contService.update("3210987654321","3214","Savings","32214.32","2001-01-01").getResult();
        assertTrue(bool);
    }


    @Test
    public void delete(){
        boolean bool= contService.delete("3214");
        assertTrue(bool);
    }



}
