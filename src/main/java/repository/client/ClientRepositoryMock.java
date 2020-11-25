package repository.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRepositoryMock implements ClientRepository{
    private List<Client> clients;

    public ClientRepositoryMock() {
        clients = new ArrayList<>();
    }



    @Override
    public Long idClientByCNP(String CNP) {
        List<Client> clientiDupaCNP = clients.parallelStream()
                .filter(it -> it.getCodNumericPersonal().equals(CNP))
                .collect(Collectors.toList());
        if(clientiDupaCNP.size()>0){
            return clientiDupaCNP.get(0).getId();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        return clients;
    }



    @Override
    public boolean update(String nume, String adresa, String codNumericPersonal, int idCardNumber) {
        List<Client> clientiDupaCNP = clients.parallelStream()
                .filter(it -> it.getCodNumericPersonal().equals(codNumericPersonal))
                .collect(Collectors.toList());
        if(clientiDupaCNP.size()>0){
            clientiDupaCNP.get(0).setNume(nume);
            clientiDupaCNP.get(0).setAdresa(adresa);
            clientiDupaCNP.get(0).setIdCardNumber(idCardNumber);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean save(Client client) {
        return clients.add(client);
    }

    @Override
    public boolean existaClientDupaCNP(String CNP) {
        List<Client> clientiDupaCNP = clients.parallelStream()
                .filter(it -> it.getCodNumericPersonal().equals(CNP))
                .collect(Collectors.toList());
        if(clientiDupaCNP.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public Client findByCNP(String CNP) throws EntityNotFoundException {
        List<Client> clientiDupaCNP = clients.parallelStream()
                    .filter(it -> it.getCodNumericPersonal().equals(CNP))
                    .collect(Collectors.toList());
        if(clientiDupaCNP.size()>0){
            return clientiDupaCNP.get(0);
        }
        throw new EntityNotFoundException(Client.class.getSimpleName());
    }

    @Override
    public void removeAll() {
        clients.clear();
    }
}
