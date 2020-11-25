package model.validation;

import model.Client;
import model.Cont;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static java.lang.Math.pow;

public class ContValidator {


    private static final int lungime_numar_de_identificare=4;
    private static final String REGEX_LETTERS = "[a-zA-Z]";
    private static final String REGEX_INTREG= "[^\\d+$]";
    private static final String REGEX_ZECIMAL = "[^[0-9](\\.[0-9]+)?$\n]";


    private final List<String> erori;
    private final Cont cont;


    public ContValidator( Cont cont) {
        this.erori = new ArrayList<>();
        this.cont = cont;
    }

    public ContValidator() {
        this.erori = new ArrayList<>();
        this.cont = new Cont();
    }

    public List<String> getErrori(){
        return erori;
    }

    public boolean validare(){
        validareNumarDeIdentificareSiTipDeCard(cont.getNumarDeIndentificare(),cont.getTip());
        validareIdClientNotNullClientGasitDupaCNP(cont.getClientId());
        validareSold(cont.getSold());
        return erori.isEmpty();
    }

    public void validareSold(Double sold)
    {
        if(sold<0)
        {
            erori.add("Soldul nu poate fi negativ");
        }
    }

    public void validareNumarDeIdentificareSiTipDeCard(int numarDeIdentificare,String tip)
    {

        if(numarDeIdentificare<pow(10,3) || numarDeIdentificare>=pow(10,4))
        {
            erori.add("Numarul de identificare nu este de " + lungime_numar_de_identificare + " cifre");
        }
        if(tipulDeContEsteRecunoscut(tip)==false)
        {
            erori.add("Tipul de cont introdus nu este recunoscut de catre banca");
        }
    }

    public void validareIdClientNotNullClientGasitDupaCNP(Long id)
    {
        if(id == null)
        {
            erori.add("Nu a fost gasit clientul dupa CNP pentru care s-a incercat creearea contului. Acesta nu este inregistrat");
        }
    }


    private boolean tipulDeContEsteRecunoscut(String tip)
    {
        if(tip==null || tip.trim().isEmpty())
        {
            return false;
        }
        return tip.matches("(Savings|Spendings)");
    }

    //-------------------------------------------



    private boolean esteNumarZecimal(String sold){
        if(sold==null || sold.trim().isEmpty())
            return false;
        Pattern pattern= Pattern.compile(REGEX_ZECIMAL);
        Matcher matcher=pattern.matcher(sold);
        return !matcher.find();
    }

    private boolean esteDataValida(String date){
        if(date==null || date.trim().isEmpty()){
            return false;
        }
        try {
            Date dateValue = Date.valueOf(date);
            return true;
        }catch(Exception exception){
            return false;
        }
    }

    private boolean esteNumarDeIdentificareValid(String nrDeIdentificare){
        if(nrDeIdentificare==null || nrDeIdentificare.trim().isEmpty()){
            return false;
        }
        Pattern pattern= Pattern.compile(REGEX_INTREG);
        Matcher matcher=pattern.matcher(nrDeIdentificare);
        return !matcher.find();
    }



    public boolean soldValidator(String soldValue){
        if(esteNumarZecimal(soldValue)==false)
            erori.add("Soldul introdus nu respecta formatul unui numar zecimal.");
        return erori.isEmpty();
    }

    public boolean dateValidator(String date){
        if(esteDataValida(date)==false)
            erori.add("Data introdusa nu respecta formatul yyyy-mm-dd. Exemplu:2020-01-01");
        return erori.isEmpty();
    }
    public boolean nrDeIdentificareValidator(String nrDeIdentificare){
        if(esteNumarDeIdentificareValid(nrDeIdentificare)==false)
            erori.add("Numarul de identificare introdus nu este valid. Nu respecta formatul unui numar intreg");
        return erori.isEmpty();
    }



}
