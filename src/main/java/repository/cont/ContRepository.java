package repository.cont;

import model.Client;
import model.Cont;
import repository.EntityNotFoundException;

import java.sql.Date;
import java.util.List;

public interface ContRepository {

    Cont findById(Long Id ) throws EntityNotFoundException;
    List<Cont> findAll();
    void removeAll();

    //------------------------------------------------------------------------\\
    boolean save(Cont cont);
    boolean update(int numarDeIdentificare, String tip,double sold, Date dataCreeari);
    boolean delete(int numarDeIdentificare);
    Cont findByNumarDeIdentificareOrId(Long id,int numarDeIdentificare) throws EntityNotFoundException;
    //-------------------------------------------------------------------------\\
}
