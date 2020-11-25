package repository.cont;

import database.FactoryDataBase;
import model.Cont;
import model.builder.ClientConstructor;
import model.builder.ContConstructor;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryCacheDecorator;
import repository.client.ClientRepositoryMySQL;

import java.sql.Date;

import static org.junit.Assert.assertTrue;

public class ContRepositoryMySQLTest {
    private static ContRepository contRepository;
    private static ClientRepository clientRepository;
    @BeforeClass
    public static void setupClass(){
        contRepository= new ContRepositoryCacheDecorator(
                new ContRepositoryMySQL (new FactoryDataBase().getConnectionWrapper(true).getConnection()),
                new Cache<>());
        clientRepository=new ClientRepositoryCacheDecorator(
                new ClientRepositoryMySQL(new FactoryDataBase().getConnectionWrapper(true).getConnection()),
                new Cache<>());

        clientRepository.save(new ClientConstructor()
                .setIdCardNumber(1234)
                .setCodNumericPersonal("9191919191911")
                .setNume("Rusu Marcel")
                .setAdresa("Vaslui")
                .build());
    }



    @Test
    public void save(){

        Cont cont =new ContConstructor()
                .setClientId(1L)
                .setTip("Spendings")
                .setNumarDeIndentificare(3455)
                .setSold(323.41)
                .setDataCreeari(new Date(1999,8,20))
                .build();
        boolean bool= contRepository.save(cont);
        assertTrue(bool);

    }

    @Test
    public void existaContDupaNumarDeIdentificare(){
        Cont cont =new ContConstructor()
                .setClientId(1L)
                .setTip("Spendings")
                .setNumarDeIndentificare(3455)
                .setSold(323.41)
                .setDataCreeari(new Date(1999,8,20))
                .build();
       contRepository.save(cont);

        boolean bool= contRepository.existaContDupaNumarDeIdentificare(3455);
        assertTrue(bool);
    }




    /*@Test(expected = EntityNotFoundException.class)
    public void findByNumarDeIdentificare() throws EntityNotFoundException {
        Cont cont =new ContConstructor()
                .setClientId(1L)
                .setTip("Spendings")
                .setNumarDeIndentificare(3455)
                .setSold(323.41)
                .setDataCreeari(new Date(1999,8,20))
                .build();
        contRepository.save(cont);

        Cont contReturnat=contRepository.findByNumarDeIdentificare(3455);
        assertNotNull(contReturnat);
    }*/






    @Test
    public void setNewSold(){
        assertTrue(contRepository.setNewSold(3455,3232.412));
    }

    @Test
    public void update(){


        assertTrue(contRepository.update(1L,3455,"Savings",32412.50,new Date(2000,9,12)));

    }
    @Test
    public void delete(){

        assertTrue(contRepository.delete(3455));
    }


}
