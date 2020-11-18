package repository.cont;



import model.Cont;
import repository.EntityNotFoundException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContRepositoryMock implements ContRepository{
    private List<Cont> conts;

    public ContRepositoryMock() {
        conts = new ArrayList<>();
    }

    public List<Cont> findAll() {
        return conts;
    }

    public Cont findById(Long id) throws EntityNotFoundException {
        List<Cont> filteredConts = conts.parallelStream()
                .filter(it -> it.getId().equals(id))
                .collect(Collectors.toList());

        if (filteredConts.size() > 0) {
            return filteredConts.get(0);
        }
        throw new EntityNotFoundException(id, Cont.class.getSimpleName());
    }


    @Override
    public boolean save(Cont cont) {
        return conts.add(cont);
    }

    @Override
    public boolean update(int numarDeIdentificare, String tip, double sold, Date dataCreeari) {
        return false;
    }

    @Override
    public void removeAll() {
        conts.clear();
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
