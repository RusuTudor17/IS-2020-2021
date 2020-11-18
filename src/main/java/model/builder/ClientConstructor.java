package model.builder;

import model.Client;

import java.util.List;

public class ClientConstructor {
    private Client client;

    public ClientConstructor() {

        client = new Client();
    }
    public ClientConstructor setId(Long Id)
    {
        client.setId(Id);
        return this;
    }

    public ClientConstructor setNume(String nume) {
        client.setNume(nume);
        return this;
    }
    public ClientConstructor setAdresa(String adresa) {
        client.setAdresa(adresa);
        return this;
    }
    public ClientConstructor setCodNumericPersonal(String codNumericPersonal) {
        client.setCodNumericPersonal(codNumericPersonal);
        return this;
    }
    public ClientConstructor setIdCardNumber(int idCardNumber) {
        client.setIdCardNumber(idCardNumber);
        return this;
    }

    public Client build() {

        return client;
    }
}
