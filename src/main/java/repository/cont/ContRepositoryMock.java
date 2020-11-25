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
        throw new EntityNotFoundException(Cont.class.getSimpleName());
    }


    @Override
    public boolean save(Cont cont) {
        return conts.add(cont);
    }

    @Override
    public boolean update(Long clientId, int numarDeIdentificare, String tip, double sold, Date dataCreeari) {
        List<Cont> filteredConts = conts.parallelStream()
                .filter(it -> it.getNumarDeIndentificare()==numarDeIdentificare)
                .collect(Collectors.toList());

        if (filteredConts.size() > 0) {
            filteredConts.get(0).setClientId(clientId);
            filteredConts.get(0).setTip(tip);
            filteredConts.get(0).setSold(sold);
            filteredConts.get(0).setDataCreeari(dataCreeari);
            return true;
        }
        return false;
    }


    @Override
    public void removeAll() {
        conts.clear();
    }


    @Override
    public boolean delete(int numarDeIdentificare) {
        List<Cont> filteredConts = conts.parallelStream()
                .filter(it -> it.getNumarDeIndentificare()==numarDeIdentificare)
                .collect(Collectors.toList());
        if (filteredConts.size() > 0) {
            conts.remove(filteredConts.get(0));
            return true;
        }
        return false;

    }

    @Override
    public Cont findByNumarDeIdentificare(int numarDeIdentificare) throws EntityNotFoundException {
        List<Cont> filteredConts = conts.parallelStream()
                .filter(it -> it.getNumarDeIndentificare()==numarDeIdentificare)
                .collect(Collectors.toList());

        if (filteredConts.size() > 0) {
            return filteredConts.get(0);
        }
        throw new EntityNotFoundException(Cont.class.getSimpleName());
    }

    @Override
    public boolean setNewSold(int numarDeIdentificare, Double sold) {
        List<Cont> filteredConts = conts.parallelStream()
                .filter(it -> it.getNumarDeIndentificare()==numarDeIdentificare)
                .collect(Collectors.toList());

        if (filteredConts.size() > 0) {
            filteredConts.get(0).setSold(sold);
            return true;
        }
        return false;
    }


    @Override
    public boolean existaContDupaNumarDeIdentificare(int numarDeIdentificare) {
        List<Cont> filteredConts = conts.parallelStream()
                .filter(it -> it.getNumarDeIndentificare()==numarDeIdentificare)
                .collect(Collectors.toList());

        if (filteredConts.size() > 0) {
            return true;
        }
        return false;
    }


}
