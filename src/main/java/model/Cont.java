package model;

import java.sql.Date;

public class Cont {
    private Long id;
    private Long clientId;

    private int numarDeIndentificare;
    private String tip;
    private double sold;
    private Date dataCreeari;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumarDeIndentificare() {
        return numarDeIndentificare;
    }

    public void setNumarDeIndentificare(int numarDeIndentificare) {
        this.numarDeIndentificare = numarDeIndentificare;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public Date getDataCreeari() {
        return dataCreeari;
    }

    public void setDataCreeari(Date dataCreeari) {
        this.dataCreeari = dataCreeari;
    }
}
