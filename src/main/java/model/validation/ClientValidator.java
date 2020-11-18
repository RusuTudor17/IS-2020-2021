package model.validation;

import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientValidator {


    private static final String REGEX_CNP = "[a-zA-Z]";
    private static final int lungime_cnp=13;

    private final List<String> erori;
    private final Client client;


    public ClientValidator( Client client) {
        this.erori = new ArrayList<>();
        this.client = client;
    }

    public List<String> getErrori(){
        return erori;
    }

    public boolean validare(){
        validareCNP(client.getCodNumericPersonal());
        return erori.isEmpty();
    }

    public void validareCNP(String CNP)
    {
        if(contineDoarCifre(CNP)==false)
        {
            erori.add("CNP-ul unei persoane, trebuie sa aiba doar numere.");
        }
        if(CNP.length()!=lungime_cnp)
        {
            erori.add("Lungimea CNP-ului adaugat este diferita de 13.");
        }
    }


    private boolean contineDoarCifre(String CNP)
    {
        if(CNP==null || CNP.trim().isEmpty())
        {
            return false;
        }
        Pattern pattern = Pattern.compile(REGEX_CNP);
        Matcher matcher=pattern.matcher(CNP);
        return !(matcher.find());
    }


}
