package model.validation;

import model.Client;
import model.Cont;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static java.lang.Math.pow;

public class ContValidator {

    private static final String REGEX_TIPUL_DE_CARD = "[^Mastercard$|^Visa$|^Maestro$|^Visa Electron$]";

    private static final int lungime_numar_de_identificare=4;

    private final List<String> erori;
    private final Cont cont;


    public ContValidator( Cont cont) {
        this.erori = new ArrayList<>();
        this.cont = cont;
    }

    public List<String> getErrori(){
        return erori;
    }

    public boolean validare(){
        validareNumarDeIdentificareSiTipDeCard(cont.getNumarDeIndentificare(),cont.getTip());
        return erori.isEmpty();
    }

    public void validareNumarDeIdentificareSiTipDeCard(int numarDeIdentificare,String tip)
    {

        if(numarDeIdentificare<pow(10,3) || numarDeIdentificare>=pow(10,4))
        {
            erori.add("Numarul de identificare nu este de " + lungime_numar_de_identificare + " cifre");
        }
        if(tipulDeCardEsteRecunoscut(tip)==false)
        {
            erori.add("Tipul de card introdus nu este recunoscut de catre banca");
        }
    }



    private boolean tipulDeCardEsteRecunoscut(String tip)
    {
        if(tip==null || tip.trim().isEmpty())
        {
            return false;
        }
        Pattern pattern = Pattern.compile(REGEX_TIPUL_DE_CARD);
        Matcher matcher=pattern.matcher(tip);
        return !(matcher.find());
    }
}
