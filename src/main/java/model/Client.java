package model;

public class Client {
    private Long id;

    private String nume;
    private String adresa;
    private String CodNumericPersonal;
    private int idCardNumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getCodNumericPersonal() {
        return CodNumericPersonal;
    }

    public void setCodNumericPersonal(String codNumericPersonal) {
        CodNumericPersonal = codNumericPersonal;
    }

    public int getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(int idCardNumber) {
        this.idCardNumber = idCardNumber;
    }
}
