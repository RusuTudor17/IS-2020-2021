package model.validation;

import model.Cont;
import model.Report;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReportValidator {
    private final List<String> erori;
    private final Report report;

    public ReportValidator() {
        this.erori = new ArrayList<>();
        this.report = new Report();
    }
    public List<String> getErrori(){
        return erori;
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

    public boolean dateValidator(String date){
        if(esteDataValida(date)==false)
            erori.add("Data introdusa nu respecta formatul yyyy-mm-dd. Exemplu:2020-01-01");
        return erori.isEmpty();
    }



}
