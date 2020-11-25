package repository.cont;

import model.Client;
import model.Cont;
import repository.Cache;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;

import java.sql.Date;
import java.util.List;

public class ContRepositoryCacheDecorator extends ContRepositoryDecorator {
    private Cache<Cont> cache;


    public ContRepositoryCacheDecorator(ContRepository contRepository, Cache<Cont> cache) {
        super(contRepository);
        this.cache=cache;
    }




    @Override
    public boolean save(Cont cont) {
        cache.invalidateCache();
        return decoratedRepository.save(cont);
    }

    @Override
    public boolean update(Long clientId, int numarDeIdentificare, String tip, double sold, Date dataCreeari) {
        cache.invalidateCache();
        return decoratedRepository.update(clientId, numarDeIdentificare, tip, sold, dataCreeari);
    }



    @Override
    public Cont findById (Long id) throws EntityNotFoundException {
        return decoratedRepository.findById(id);
    }

    @Override
    public List<Cont> findAll() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<Cont> conts = decoratedRepository.findAll();
        cache.save(conts);
        return conts;
    }
    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
    }



    @Override
    public boolean delete(int numarDeIdentificare) {

        cache.invalidateCache();
        return decoratedRepository.delete(numarDeIdentificare);
    }

    @Override
    public Cont findByNumarDeIdentificare(int numarDeIdentificare) throws EntityNotFoundException {
        return decoratedRepository.findByNumarDeIdentificare(numarDeIdentificare);
    }

    @Override
    public boolean setNewSold(int numarDeIdentificare, Double sold) {

        return decoratedRepository.setNewSold(numarDeIdentificare, sold);
    }


    @Override
    public boolean existaContDupaNumarDeIdentificare(int numarDeIdentificare) {
        return decoratedRepository.existaContDupaNumarDeIdentificare(numarDeIdentificare);
    }



}
