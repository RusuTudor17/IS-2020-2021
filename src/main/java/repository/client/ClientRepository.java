package repository.client;

import model.Client;
import repository.EntityNotFoundException;


import java.util.List;


public interface ClientRepository {
    List<Client> findAll();
    void removeAll();


    boolean update(String nume,String adresa, String codNumericPersonal, int idCardNumber);
    boolean save(Client client);
     boolean existaClientDupaCNP(String CNP);
     Client findByCNP(String CNP) throws EntityNotFoundException;


    Long idClientByCNP(String CNP);
}
