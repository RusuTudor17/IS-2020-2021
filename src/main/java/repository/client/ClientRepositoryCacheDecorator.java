package repository.client;

import model.Client;
import repository.Cache;
import repository.EntityNotFoundException;


import java.util.List;

public class ClientRepositoryCacheDecorator extends ClientRepositoryDecorator{
    private Cache<Client> cache;


    public ClientRepositoryCacheDecorator(ClientRepository clientRepository,Cache<Client> cache) {
        super(clientRepository);
        this.cache=cache;
    }


    @Override
    public boolean update(String nume, String adresa, String codNumericPersonal, int idCardNumber) {
        return false;
    }

    @Override
     public boolean save(Client client) {
        cache.invalidateCache();
        return decoratedRepository.save(client);
    }

    @Override
    public boolean existaClientDupaCNP(String CNP) {
        return false;
    }

    @Override
    public Client findByIdOrCNP (Long id,String CNP) throws EntityNotFoundException {
        return decoratedRepository.findByIdOrCNP(id,CNP);
    }

    @Override
    public List<Client> findAll() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<Client> clients = decoratedRepository.findAll();
        cache.save(clients);
        return clients;
    }
    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
    }
}
