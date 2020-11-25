package service.client;

import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

public interface ClientService {
    //operatii
    Notification<Boolean> update(String nume, String adresa, String codNumericPersonal, String idCardNumber);
    Notification<Boolean> save(String nume,String adresa, String CNP, String idCardNumber);
    Client findByCNP (String CNP) throws EntityNotFoundException;

    //altele
    boolean existaClientDupaCNP(String CNP);


}
