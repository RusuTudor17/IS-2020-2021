package service.client;

import model.Client;
import model.builder.ClientConstructor;
import model.validation.ClientValidator;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;

import java.util.List;

public class ClientServiceImpl implements ClientService{
    private final ClientRepository repository;
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }


    @Override
    public Notification<Boolean> update(String nume, String adresa, String codNumericPersonal, int idCardNumber) {
        Client c= new ClientConstructor()
                .setId(null)
                .setNume(nume)
                .setAdresa(adresa)
                .setCodNumericPersonal(codNumericPersonal)
                .setIdCardNumber(idCardNumber)
                .build();

        ClientValidator clientValidator = new ClientValidator(c);

        boolean boolClient=clientValidator.validare();

        Notification<Boolean> updateClientNotification = new Notification<>();
        if(boolClient==false)
        {
            clientValidator.getErrori().forEach(updateClientNotification::addError);
            updateClientNotification.setResult(Boolean.FALSE);
        }
        else
        {
            updateClientNotification.setResult(repository.update(c.getNume(),c.getAdresa(),c.getCodNumericPersonal(),c.getIdCardNumber()));
        }
        return updateClientNotification;

    }


    @Override
    public Notification<Boolean> save(String nume,String adresa, String CNP, int idCardNumber) {
        Client c= new ClientConstructor()
                .setId(null)
                .setNume(nume)
                .setAdresa(adresa)
                .setCodNumericPersonal(CNP)
                .setIdCardNumber(idCardNumber)
                .build();

        ClientValidator clientValidator = new ClientValidator(c);

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
        return saveClientNotification;
    }

    @Override
    public Client findByIdOrCNP(Long Id,String CNP) throws EntityNotFoundException {
        return repository.findByIdOrCNP(Id,CNP);
    }

    @Override
    public boolean existaClientDupaCNP(String CNP) {
        return repository.existaClientDupaCNP(CNP);
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }


}
