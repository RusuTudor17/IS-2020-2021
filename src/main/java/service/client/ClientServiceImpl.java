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
    public Notification<Boolean> update(String nume, String adresa, String codNumericPersonal, String idCardNumber) {

        Notification<Boolean> updateClientNotification = new Notification<>();

        ClientValidator clientValidatorInput= new ClientValidator();
        boolean boolidCardNumberValidator=clientValidatorInput.IdCardNumberValidator(idCardNumber);
        if(boolidCardNumberValidator==false){
            clientValidatorInput.getErrori().forEach(updateClientNotification::addError);
            updateClientNotification.setResult(Boolean.FALSE);
            return updateClientNotification;
        }

        Client client= new ClientConstructor()
                .setId(null)
                .setNume(nume)
                .setAdresa(adresa)
                .setCodNumericPersonal(codNumericPersonal)
                .setIdCardNumber(Integer.parseInt(idCardNumber))
                .build();

        ClientValidator clientValidator = new ClientValidator(client);

        boolean boolClient=clientValidator.validare();


        if(boolClient==false)
        {
            clientValidator.getErrori().forEach(updateClientNotification::addError);
            updateClientNotification.setResult(Boolean.FALSE);
        }
        else
        {
            updateClientNotification.setResult(repository.update(client.getNume(),client.getAdresa(),client.getCodNumericPersonal(),client.getIdCardNumber()));
        }
        return updateClientNotification;

    }


    @Override
    public Notification<Boolean> save(String nume,String adresa, String CNP, String idCardNumber) {
        Notification<Boolean> saveClientNotification = new Notification<>();

        ClientValidator clientValidatorInput= new ClientValidator();
        boolean boolidCardNumberValidator=clientValidatorInput.IdCardNumberValidator(idCardNumber);

        if(boolidCardNumberValidator==false){
            clientValidatorInput.getErrori().forEach(saveClientNotification::addError);
            saveClientNotification.setResult(Boolean.FALSE);
            return saveClientNotification;
        }


        Client c= new ClientConstructor()
                .setId(null)
                .setNume(nume)
                .setAdresa(adresa)
                .setCodNumericPersonal(CNP)
                .setIdCardNumber(Integer.parseInt(idCardNumber))
                .build();

        ClientValidator clientValidator = new ClientValidator(c);

        boolean boolClient=clientValidator.validare();


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
    public Client findByCNP(String CNP) throws EntityNotFoundException {
        return repository.findByCNP(CNP);
    }

    @Override
    public boolean existaClientDupaCNP(String CNP) {
        return repository.existaClientDupaCNP(CNP);
    }




}
