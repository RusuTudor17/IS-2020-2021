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
    public boolean update(int numarDeIdentificare, String tip, double sold, Date dataCreeari) {
        return false;
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
        return false;
    }

    @Override
    public Cont findByNumarDeIdentificareOrId(Long id, int numarDeIdentificare) throws EntityNotFoundException {
        return null;
    }
}
