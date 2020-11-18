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
    public Client findByIdOrCNP(Long Id, String CNP) throws EntityNotFoundException {
        return null;
    }

    public List<Client> findAll() {
        return clients;
    }

    public Client findById(Long id) throws EntityNotFoundException {
        List<Client> filteredClients = clients.parallelStream()
                .filter(it -> it.getId().equals(id))
                .collect(Collectors.toList());

        if (filteredClients.size() > 0) {
            return filteredClients.get(0);
        }
        throw new EntityNotFoundException(id, Client.class.getSimpleName());
    }

    @Override
    public boolean update(String nume, String adresa, String codNumericPersonal, int idCardNumber) {
        return false;
    }

    public boolean save(Client client) {
        return clients.add(client);
    }

    @Override
    public boolean existaClientDupaCNP(String CNP) {
        return false;
    }

    @Override
    public void removeAll() {
        clients.clear();
    }
}
