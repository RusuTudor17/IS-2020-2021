package service.cont;

import model.Cont;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.sql.Date;
import java.util.List;

public interface ContService {


    //operatii
    Notification<Boolean> save(String numarDeIdentificare, String cnpClient,  String tip, String sold, String dataCreeari);
    Notification<Boolean> update(String CNP,String numarDeIdentificare, String tip,String sold, String dataCreeari);
    boolean delete(String numarDeIdentificare);

    Cont findByNumarDeIdentificare(String numarDeIdentificare) throws EntityNotFoundException;

    //altele
    boolean existaContDupaNumarDeIdentificare(String numarDeIdentificare);

    boolean transferSoldDintreConturi(String numarDeIdentificareSender, String numarDeIdentificareReceive, String suma) throws EntityNotFoundException;
    boolean plataBill(String numarDeIdentificare, String sumaDePlata) throws EntityNotFoundException;
    //---------------

}
