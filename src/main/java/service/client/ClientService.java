package service.client;

import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

public interface ClientService {
    //operatii
    Notification<Boolean> update(String nume, String adresa, String codNumericPersonal, int idCardNumber);
    Notification<Boolean> save(String nume,String adresa, String CNP, int idCardNumber);
    Client findByIdOrCNP(Long Id ,String CNP)throws EntityNotFoundException;

    //altele
    public boolean existaClientDupaCNP(String CNP);

    //-------------------------
    List<Client> findAll();

}
