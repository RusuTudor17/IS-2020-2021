package model.builder;

import model.Client;
import model.Cont;

import java.sql.Date;
public class ContConstructor {

    /*private int numarDeIndentificare;
    private String tip;
    private double sold;
    private Date dataCreeari;*/

    private Cont cont;

    public ContConstructor() {

        cont = new Cont();
    }

    public ContConstructor setId(Long Id)
    {
        cont.setId(Id);
        return this;
    }

    public ContConstructor setClientId(Long clientId)
    {
        cont.setClientId(clientId);
        return this;
    }

    public ContConstructor setNumarDeIndentificare(int numarDeIndentificare) {
        cont.setNumarDeIndentificare(numarDeIndentificare);
        return this;
    }

    public ContConstructor setTip(String tip) {
        cont.setTip(tip);
        return this;
    }

    public ContConstructor setSold(Double sold) {
        cont.setSold(sold);
        return this;
    }
    public ContConstructor setDataCreeari(Date dataCreeari) {
        cont.setDataCreeari(dataCreeari);
        return this;
    }
    public Cont build() {
        return cont;
    }
}
