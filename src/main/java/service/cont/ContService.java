package service.cont;

import model.Cont;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.sql.Date;
import java.util.List;

public interface ContService {


    //operatii
    Notification<Boolean> save(int numarDeIdentificare,String tip,Double sold,Date dataCreeari);
    boolean update(int numarDeIdentificare, String tip,double sold, Date dataCreeari);
    boolean delete(int numarDeIdentificare);
    Cont findByNumarDeIdentificareOrId(Long id,int numarDeIdentificare) throws EntityNotFoundException;

    //altele
    Cont findById(Long Id ) throws EntityNotFoundException;

    //---------------
    List<Cont> findAll();
}
