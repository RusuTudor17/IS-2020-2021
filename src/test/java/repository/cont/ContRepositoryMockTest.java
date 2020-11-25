package repository.cont;

import model.Cont;
import model.builder.ContConstructor;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.EntityNotFoundException;

import java.sql.Date;


import static org.junit.Assert.*;

public class ContRepositoryMockTest {
    private static ContRepository contRepository;

    @BeforeClass
    public static void setupClass(){
        contRepository= new ContRepositoryCacheDecorator(
                new ContRepositoryMock(),
                new Cache<>());
    }



    @Test
    public void save(){
        Cont cont= new ContConstructor()
                .setTip("Savings")
                .setNumarDeIndentificare(3415)
                .setSold(323.41)
                .setDataCreeari(new Date(1999,8,20))
                .build();
        assertTrue(contRepository.save(cont));
    }



    @Test
    public void update(){
        Cont cont = new ContConstructor()
                .setClientId(1L)
                .setTip("Spendings")
                .setNumarDeIndentificare(3455)
                .setSold(12233.41)
                .setDataCreeari(new Date(1999,8,20))
                .build();
        contRepository.save(cont);

        assertTrue(contRepository.update(1L,3455,"Savings",32412.50,new Date(2000,9,12)));

    }

    @Test
    public void delete(){
        Cont cont = new ContConstructor()
                .setClientId(1L)
                .setTip("Spendings")
                .setNumarDeIndentificare(3456)
                .setSold(12233.41)
                .setDataCreeari(new Date(1999,8,20))
                .build();
        contRepository.save(cont);
        assertTrue(contRepository.delete(3456));
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByNumarDeIdentificare() throws EntityNotFoundException {
        assertNotNull(contRepository.findByNumarDeIdentificare(3455));
    }

    @Test
    public void setNewSold(){
        assertTrue(contRepository.setNewSold(3455,3232.412));
    }

    @Test
    public void existaContDupaNumarDeIdentificare(){
        assertTrue(contRepository.existaContDupaNumarDeIdentificare(3455));
    }



}
