package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class RegularUserView extends JFrame{


    //JTexts pentru client
   // private JTextField idClient;
    private JTextField nume;
    private JTextField adresa;
    private JTextField CNP;
    private JTextField identityCardNumber;

    //butoane operatii clienti
    private JButton btnAddClient;
    private JButton btnUpdateClient;
    private JButton btnViewClient;

    //JText Conturi
   // private JTextField idCont;
    private JTextField clientCNP;
    private JTextField numarDeIdentificare;
    private JTextField tip;
    private JTextField sold;
    private JTextField dataCreeari;
    //butoane operatii conturi
    private JButton btnAddCont;
    private JButton btnDeleteCont;
    private JButton btnUpdateCont;
    private JButton btnViewCont;

    //Labeluri pentru a intelege mai bine in interfata
    private JLabel operatiiClient;
    private JLabel operatiiCont;
    private JLabel outputViewClient;
    private JLabel outputViewCont;
    private JLabel TransferIntreConturi;
    private JLabel billProcessing;

    //text fielduri pentru operatia de view- client
    private JTextField ViewidClient;
    private JTextField Viewnume;
    private JTextField Viewadresa;
    private JTextField ViewCNP;
    private JTextField ViewidentityCardNumber;

    //text fielduri pentru operatia de view- cont
    private JTextField ViewidCont;
    private JTextField ViewidClientCont;
    private JTextField ViewnumarDeIdentificare;
    private JTextField Viewtip;
    private JTextField Viewsold;
    private JTextField ViewdataCreeari;

    //transfer intre conturi
    private JTextField numarDeIdentificareContSender;
    private JTextField numarDeIdentificareContReceiver;
    private JTextField soldDeTransferat;
    private JButton btnTransfer;

    //procesare de bills
    private final  String[] utilities= {"Electricitate","Gaz","Apa","Intretinere","Impozit proprietate"};
    private JComboBox billChoose;
    private JTextField numarDeIdentificareContPentruBill;
    private JTextField sumaDePlataPentruFactura;
    private JButton btnProcessBill;



    private JPanel regularUserViewPanelClientButtons;
    private JPanel regularUserViewPanelContButtons;
    public RegularUserView() throws HeadlessException {
        setSize(900, 800);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));

        add(operatiiClient);
        //add(idClient);

        add(nume);
        add(adresa);
        add(CNP);
        add(identityCardNumber);

        regularUserViewPanelClientButtons.add(btnAddClient);
        regularUserViewPanelClientButtons.add(btnUpdateClient);
        regularUserViewPanelClientButtons.add(btnViewClient);
        add(regularUserViewPanelClientButtons);



        add(outputViewClient);
        add(ViewidClient);
        add(Viewnume);
        add(Viewadresa);
        add(ViewCNP);
        add(ViewidentityCardNumber);





        add(operatiiCont);
        //add(idCont);
        add(clientCNP);
        add(numarDeIdentificare);
        add(tip);
        add(sold);
        add(dataCreeari);

        regularUserViewPanelContButtons.add(btnAddCont);
        regularUserViewPanelContButtons.add(btnDeleteCont);
        regularUserViewPanelContButtons.add(btnUpdateCont);
        regularUserViewPanelContButtons.add(btnViewCont);
        add(regularUserViewPanelContButtons);



        add(outputViewCont);
        add(ViewidCont);
        add(ViewidClientCont);
        add(ViewnumarDeIdentificare);
        add(Viewtip);
        add(Viewsold);
        add(ViewdataCreeari);

        add(TransferIntreConturi);
        add(numarDeIdentificareContSender);
        add(numarDeIdentificareContReceiver);
        add(soldDeTransferat);
        add(btnTransfer);

        add(billProcessing);
        add(billChoose);

        add(numarDeIdentificareContPentruBill);
        add(sumaDePlataPentruFactura);
        add(btnProcessBill);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {

        regularUserViewPanelClientButtons = new JPanel();
        regularUserViewPanelClientButtons.setLayout(new BoxLayout(regularUserViewPanelClientButtons, BoxLayout.X_AXIS));

        regularUserViewPanelContButtons = new JPanel();
        regularUserViewPanelContButtons.setLayout(new BoxLayout(regularUserViewPanelContButtons,BoxLayout.X_AXIS));



        //idClient = new JTextField("id");
        nume = new JTextField("nume",25);
        adresa = new JTextField("adresa",25);
        CNP=new JTextField("CNP",13);
        identityCardNumber=new JTextField("idCardNumber",4);
        //idCont=new JTextField("id");
        numarDeIdentificare=new JTextField("nrIdentificare",4);
        tip=new JTextField("type",10);
        sold=new JTextField("sold",8);
        dataCreeari=new JTextField("date",20);
        clientCNP=new JTextField("CNP pentru cont",13);
        numarDeIdentificareContReceiver=new JTextField("nrIdentificare cont receiver",4);
        numarDeIdentificareContSender=new JTextField("nrIdentificare cont sender",4);
        soldDeTransferat=new JTextField("sold de transferat",8);
        numarDeIdentificareContPentruBill= new JTextField("nrIdentificare cont de plata",4);
        sumaDePlataPentruFactura= new JTextField("sumaDePlataAFacturii",10);

        billChoose=new JComboBox(utilities);

        btnTransfer= new JButton("Transfer");

        btnAddClient = new JButton("AddClient");
        btnUpdateClient = new JButton("UpdateClient");
        btnViewClient = new JButton("ViewClient");

        btnAddCont = new JButton("AddCont");
        btnUpdateCont = new JButton("UpdateCont");
        btnDeleteCont = new JButton("DeleteCont");
        btnViewCont = new JButton("ViewCont");
        btnProcessBill = new JButton("Process bill");

        operatiiClient= new JLabel("Operatii Client");
        operatiiCont=new JLabel("Operatii Cont");
        outputViewClient = new JLabel("Output View Client");
        outputViewCont = new JLabel("Output View Cont");
        TransferIntreConturi = new JLabel("Transfer Intre Conturi");
        billProcessing=new JLabel("Bill processing");


        ViewidClient=new JTextField("id");
        Viewnume=new JTextField("nume");
        Viewadresa=new JTextField("adresa");
        ViewCNP=new JTextField("cnp");
        ViewidentityCardNumber=new JTextField("identityCard");


        ViewidCont=new JTextField("id");
        ViewidClientCont= new JTextField("idClient");
        ViewnumarDeIdentificare=new JTextField("nrDeIdentificare");
        Viewtip=new JTextField("type");
        Viewsold=new JTextField("sold");
        ViewdataCreeari=new JTextField("date");

        setNotEditableViewTextBoxes();
    }


    void setNotEditableViewTextBoxes()
    {
        ViewidClient.setEditable(false);
        Viewnume.setEditable(false);
        Viewadresa.setEditable(false);
        ViewCNP.setEditable(false);
        ViewidentityCardNumber.setEditable(false);

        ViewidCont.setEditable(false);
        ViewidClientCont.setEditable(false);
        ViewnumarDeIdentificare.setEditable(false);
        Viewtip.setEditable(false);
        Viewsold.setEditable(false);
        ViewdataCreeari.setEditable(false);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //public String getIdClient() {
        //return idClient.getText();
    //}
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

    //public String getIdCont() {
      //  return idCont.getText();
    //}
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
    public String getCnpCreeareCont() {
        return clientCNP.getText();
    }

    public String getNrDeIdentificareSender()
    {
        return numarDeIdentificareContSender.getText();
    }
    public String getNrDeIdentificareReceiver()
    {
        return numarDeIdentificareContReceiver.getText();
    }
    public String getSoldPentruTransfer()
    {
        return soldDeTransferat.getText();
    }
    public String getSelectedBill()
    {
        return String.valueOf(billChoose.getSelectedItem());
    }
    public String getSoldPentruBill()
    {
        return sumaDePlataPentruFactura.getText();
    }
    public String getNrDeIdentificareContPentruBill()
    {
        return numarDeIdentificareContPentruBill.getText();
    }



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
    public void setTransferContListener(ActionListener transferContListener){
        btnTransfer.addActionListener(transferContListener);
    }

    public void setProcessBillListener(ActionListener processBillListener){
        btnProcessBill.addActionListener(processBillListener);
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

    public JTextField getViewidClientCont(){
        return ViewidClientCont;
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
