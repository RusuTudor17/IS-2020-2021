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
    private final ClientRepository clientRepository;
    public ContServiceImpl(ContRepository repository, ClientRepository clientRepository) {
        this.repository = repository;
        this.clientRepository=clientRepository;
    }


    @Override
    public Notification<Boolean> save(String numarDeIdentificare, String cnpClient,  String tip, String sold, String dataCreeari)
    {
        Notification<Boolean> saveContNotificationInput = new Notification<>();

        ContValidator contValidatorInput= new ContValidator();
        boolean boolidCardNumberValidator=contValidatorInput.dateValidator(dataCreeari) && contValidatorInput.nrDeIdentificareValidator(numarDeIdentificare) && contValidatorInput.soldValidator(sold);


        if(boolidCardNumberValidator==false){
            contValidatorInput.getErrori().forEach(saveContNotificationInput::addError);
            saveContNotificationInput.setResult(Boolean.FALSE);
            return saveContNotificationInput;
        }

        Long clientId = clientRepository.idClientByCNP(cnpClient);

        Cont cont= new ContConstructor()

                .setId(null)
                .setClientId(clientId)
                .setNumarDeIndentificare(Integer.parseInt(numarDeIdentificare))
                .setTip(tip)
                .setSold(Double.parseDouble(numarDeIdentificare))
                .setDataCreeari(Date.valueOf(dataCreeari))
                .build();

        ContValidator contValidator = new ContValidator(cont);

        boolean boolCont=contValidator.validare();

        Notification<Boolean> saveContNotification = new Notification<>();
        if(boolCont==false)
        {
            contValidator.getErrori().forEach(saveContNotification::addError);
            saveContNotification.setResult(Boolean.FALSE);
        }
        else
        {
            saveContNotification.setResult(repository.save(cont));
        }
        return saveContNotification;

    }

    @Override
    public Notification<Boolean> update(String CNP,String numarDeIdentificare, String tip, String sold, String dataCreeari) {
        Notification<Boolean> updateContNotificationInput = new Notification<>();

        ContValidator contValidatorInput= new ContValidator();
        boolean boolInputValidator=contValidatorInput.dateValidator(dataCreeari) && contValidatorInput.nrDeIdentificareValidator(numarDeIdentificare) && contValidatorInput.soldValidator(sold);



        if(boolInputValidator==false){
            contValidatorInput.getErrori().forEach(updateContNotificationInput::addError);
            updateContNotificationInput.setResult(Boolean.FALSE);
            return updateContNotificationInput;
        }

        Long idClient=clientRepository.idClientByCNP(CNP);

        Cont cont= new ContConstructor()

                .setId(null)
                .setClientId(idClient)
                .setNumarDeIndentificare(Integer.parseInt(numarDeIdentificare))
                .setTip(tip)
                .setSold(Double.parseDouble(sold))
                .setDataCreeari(Date.valueOf(dataCreeari))
                .build();

        ContValidator contValidator = new ContValidator(cont);

        boolean boolCont=contValidator.validare();

        Notification<Boolean> updateContNotification = new Notification<>();
        if(boolCont==false)
        {
            contValidator.getErrori().forEach(updateContNotification::addError);
            updateContNotification.setResult(Boolean.FALSE);
        }
        else
        {
            updateContNotification.setResult(repository.update(cont.getClientId(),cont.getNumarDeIndentificare(),cont.getTip(),cont.getSold(),cont.getDataCreeari()));
        }
        return updateContNotification;
    }

    @Override
    public boolean delete(String numarDeIdentificare) {


        ContValidator contValidatorInput= new ContValidator();
        boolean boolInputValidator=contValidatorInput.nrDeIdentificareValidator(numarDeIdentificare);



        if(boolInputValidator==false){
            return false;
        }
        return repository.delete(Integer.parseInt(numarDeIdentificare));
    }

    @Override
    public Cont findByNumarDeIdentificare( String numarDeIdentificare) throws EntityNotFoundException {
        ContValidator contValidatorInput= new ContValidator();
        boolean boolInputValidator=contValidatorInput.nrDeIdentificareValidator(numarDeIdentificare);



        if(boolInputValidator==false){
            return null;
        }

        return repository.findByNumarDeIdentificare(Integer.parseInt(numarDeIdentificare));
    }


    @Override
    public boolean existaContDupaNumarDeIdentificare(String numarDeIdentificare) {
        ContValidator contValidatorInput= new ContValidator();
        boolean boolInputValidator=contValidatorInput.nrDeIdentificareValidator(numarDeIdentificare);



        if(boolInputValidator==false){
            return false;
        }
        return repository.existaContDupaNumarDeIdentificare(Integer.parseInt(numarDeIdentificare));

    }

    @Override
    public boolean transferSoldDintreConturi(String numarDeIdentificareSender, String numarDeIdentificareReceive, String suma) throws EntityNotFoundException {
        ContValidator contValidatorInput= new ContValidator();
        boolean boolInputValidator=contValidatorInput.nrDeIdentificareValidator(numarDeIdentificareSender) && contValidatorInput.nrDeIdentificareValidator(numarDeIdentificareReceive) && contValidatorInput.soldValidator(suma);




        if(boolInputValidator==false){
            return false;
        }

        Cont contSender = repository.findByNumarDeIdentificare(Integer.parseInt(numarDeIdentificareSender));
        Cont contReceive = repository.findByNumarDeIdentificare(Integer.parseInt(numarDeIdentificareReceive));

        if(contSender.getSold() < Double.parseDouble(suma))
            return false;
        return repository.setNewSold(Integer.parseInt(numarDeIdentificareSender),contSender.getSold()-Double.parseDouble(suma)) && repository.setNewSold(Integer.parseInt(numarDeIdentificareReceive),contReceive.getSold()+Double.parseDouble(suma));
    }

    public boolean plataBill(String numarDeIdentificare, String sumaDePlata) throws EntityNotFoundException {
        ContValidator contValidatorInput= new ContValidator();
        boolean boolInputValidator=contValidatorInput.nrDeIdentificareValidator(numarDeIdentificare)  && contValidatorInput.soldValidator(sumaDePlata);




        if(boolInputValidator==false){
            return false;
        }

        Cont contDePlata = repository.findByNumarDeIdentificare(Integer.parseInt(numarDeIdentificare));

        if(contDePlata.getSold()<Double.parseDouble(sumaDePlata))
            return false;
        return repository.setNewSold(Integer.parseInt(numarDeIdentificare),contDePlata.getSold()-Double.parseDouble(sumaDePlata));
    }

}
