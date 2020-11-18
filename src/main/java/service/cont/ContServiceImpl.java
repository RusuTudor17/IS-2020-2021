package service.cont;

import model.Cont;
import model.builder.ContConstructor;
import model.validation.ClientValidator;
import model.validation.ContValidator;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;
import repository.cont.ContRepository;

import java.sql.Date;
import java.util.List;

public class ContServiceImpl implements ContService{
    private final ContRepository repository;
    public ContServiceImpl(ContRepository repository) {
        this.repository = repository;
    }
    /*ClientValidator clientValidator = new ClientValidator(c);

    boolean boolClient=clientValidator.validare();

    Notification<Boolean> saveClientNotification = new Notification<>();
        if(boolClient==false)
    {
        clientValidator.getErrori().forEach(saveClientNotification::addError);
        saveClientNotification.setResult(Boolean.FALSE);
    }
        else
    {
        saveClientNotification.setResult(repository.save(c));
    }
        return saveClientNotification;*/


    @Override
    public Notification<Boolean> save(int numarDeIdentificare, String tip, Double sold, Date dataCreeari)
    {

        Cont c= new ContConstructor()
                .setId(null)
                .setNumarDeIndentificare(numarDeIdentificare)
                .setTip(tip)
                .setSold(sold)
                .setDataCreeari(dataCreeari)
                .build();
        ContValidator contValidator = new ContValidator(c);

        boolean boolCont=contValidator.validare();

        Notification<Boolean> saveContNotification = new Notification<>();
        if(boolCont==false)
        {
            contValidator.getErrori().forEach(saveContNotification::addError);
            saveContNotification.setResult(Boolean.FALSE);
        }
        else
        {
            saveContNotification.setResult(repository.save(c));
        }
        return saveContNotification;

    }

    @Override
    public boolean update(int numarDeIdentificare, String tip, double sold, Date dataCreeari) {
        return repository.update(numarDeIdentificare,tip,sold,dataCreeari);
    }

    @Override
    public boolean delete(int numarDeIdentificare) {
        return repository.delete(numarDeIdentificare);
    }

    @Override
    public Cont findByNumarDeIdentificareOrId(Long id, int numarDeIdentificare) throws EntityNotFoundException {
        return repository.findByNumarDeIdentificareOrId(id,numarDeIdentificare);
    }

    @Override
    public Cont findById(Long Id) throws EntityNotFoundException {
        return repository.findById(Id);
    }

    @Override
    public List<Cont> findAll() {
        return repository.findAll();
    }
}
