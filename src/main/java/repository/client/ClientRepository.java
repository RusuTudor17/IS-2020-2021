package repository.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.Date;
import java.util.List;


public interface ClientRepository {
    List<Client> findAll();
    void removeAll();


    boolean update(String nume,String adresa, String codNumericPersonal, int idCardNumber);
    boolean save(Client client);
    public boolean existaClientDupaCNP(String CNP);
    Client findByIdOrCNP(Long Id ,String CNP)throws EntityNotFoundException;


}
