package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class RegularUserView extends JFrame{
    private JTextField idClient;
    private JTextField nume;
    private JTextField adresa;
    private JTextField CNP;
    private JTextField identityCardNumber;

    private JButton btnAddClient;
    private JButton btnUpdateClient;
    private JButton btnViewClient;


    private JTextField idCont;
    private JTextField numarDeIdentificare;
    private JTextField tip;
    private JTextField sold;
    private JTextField dataCreeari;

    private JButton btnAddCont;
    private JButton btnDeleteCont;
    private JButton btnUpdateCont;
    private JButton btnViewCont;


    private JLabel operatiiClient;
    private JLabel operatiiCont;
    private JLabel outputViewClient;
    private JLabel outputViewCont;


    private JTextField ViewidClient;
    private JTextField Viewnume;
    private JTextField Viewadresa;
    private JTextField ViewCNP;
    private JTextField ViewidentityCardNumber;


    private JTextField ViewidCont;
    private JTextField ViewnumarDeIdentificare;
    private JTextField Viewtip;
    private JTextField Viewsold;
    private JTextField ViewdataCreeari;




    public RegularUserView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));

        add(operatiiClient);
        add(idClient);
        add(nume);
        add(adresa);
        add(CNP);
        add(identityCardNumber);
        add(btnAddClient);
        add(btnUpdateClient);
        add(btnViewClient);

        add(outputViewClient);
        add(ViewidClient);
        add(Viewnume);
        add(Viewadresa);
        add(ViewCNP);
        add(ViewidentityCardNumber);





        add(operatiiCont);
        add(idCont);
        add(numarDeIdentificare);
        add(tip);
        add(sold);
        add(dataCreeari);
        add(btnAddCont);
        add(btnDeleteCont);
        add(btnUpdateCont);
        add(btnViewCont);




        add(outputViewCont);
        add(ViewidCont);
        add(ViewnumarDeIdentificare);
        add(Viewtip);
        add(Viewsold);
        add(ViewdataCreeari);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        idClient = new JTextField("id");
        nume = new JTextField("nume");
        adresa = new JTextField("adresa");
        CNP=new JTextField("CNP");
        identityCardNumber=new JTextField("idCardNumber");
        idCont=new JTextField("id");
        numarDeIdentificare=new JTextField("nrIdentificare");
        tip=new JTextField("type");
        sold=new JTextField("sold");
        dataCreeari=new JTextField("date");


        btnAddClient = new JButton("AddClient");
        btnUpdateClient = new JButton("UpdateClient");
        btnViewClient = new JButton("ViewClient");

        btnAddCont = new JButton("AddCont");
        btnUpdateCont = new JButton("UpdateCont");
        btnDeleteCont = new JButton("DeleteCont");
        btnViewCont = new JButton("ViewCont");
        operatiiClient= new JLabel("Operatii Client");
        operatiiCont=new JLabel("Operatii Cont");
        outputViewClient = new JLabel("Output View Client");
        outputViewCont = new JLabel("Output View Cont");


        ViewidClient=new JTextField("id");
        Viewnume=new JTextField("nume");
        Viewadresa=new JTextField("adresa");
        ViewCNP=new JTextField("cnp");
        ViewidentityCardNumber=new JTextField("identityCard");


        ViewidCont=new JTextField("id");
        ViewnumarDeIdentificare=new JTextField("nrDeIdentificare");
        Viewtip=new JTextField("type");
        Viewsold=new JTextField("sold");
        ViewdataCreeari=new JTextField("date");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getIdClient() {
        return idClient.getText();
    }
    public String getNume() {
        return nume.getText();
    }
    public String getAdresa() {
        return adresa.getText();
    }
    public String getCodNumericPersonal() {
        return CNP.getText();
    }
    public String getIdCardNumber(){return identityCardNumber.getText();}

    public String getIdCont() {
        return idCont.getText();
    }
    public String getNumarDeIdentificare() {
        return numarDeIdentificare.getText();
    }
    public String getTip() {
        return tip.getText();
    }
    public String getSold() {
        return sold.getText();
    }
    public String getDataCreeari(){return dataCreeari.getText();}

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setAddClientListener(ActionListener addClientButtonListener) {
        btnAddClient.addActionListener(addClientButtonListener);
    }

    public void setUpdateClientListener(ActionListener updateClientButtonListener) {
        btnUpdateClient.addActionListener(updateClientButtonListener);
    }

    public void setViewClientListener(ActionListener viewClientButtonListener) {
        btnViewClient.addActionListener(viewClientButtonListener);
    }

    public void setAddContListener(ActionListener addContButtonListener) {
        btnAddCont.addActionListener(addContButtonListener);
    }

    public void setUpdateContListener(ActionListener updateContButtonListener) {
        btnUpdateCont.addActionListener(updateContButtonListener);
    }

    public void setDeleteContListener(ActionListener deleteContButtonListener) {
        btnDeleteCont.addActionListener(deleteContButtonListener);
    }

    public void setViewContListener(ActionListener viewContButtonListener) {
        btnViewCont.addActionListener(viewContButtonListener);
    }
    //////////////////////////////////////////////////////////////////////////


    public JTextField getViewidClient() {
        return ViewidClient;
    }

    public JTextField getViewnume() {
        return Viewnume;
    }

    public JTextField getViewadresa() {
        return Viewadresa;
    }

    public JTextField getViewCNP() {
        return ViewCNP;
    }

    public JTextField getViewidentityCardNumber() {
        return ViewidentityCardNumber;
    }

    public JTextField getViewidCont() {
        return ViewidCont;
    }

    public JTextField getViewnumarDeIdentificare() {
        return ViewnumarDeIdentificare;
    }

    public JTextField getViewtip() {
        return Viewtip;
    }

    public JTextField getViewsold() {
        return Viewsold;
    }

    public JTextField getViewdataCreeari() {
        return ViewdataCreeari;
    }
}
