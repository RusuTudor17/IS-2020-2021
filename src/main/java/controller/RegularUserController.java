package controller;

import model.Client;
import model.Cont;
import model.validation.Notification;
import repository.EntityNotFoundException;
import service.client.ClientService;
import service.cont.ContService;

import service.report.ReportService;
import view.RegularUserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class RegularUserController {

    private final RegularUserView regularUserView;
    private final ClientService clientService;
    private final ContService contService;
    private final ReportService reportService;
    private final Long idEmployeeLoggedIn;

    public RegularUserController(RegularUserView regularUserView, ClientService clientService, ContService contService, ReportService reportService,Long idEmployeeLoggedIn) {
        this.regularUserView = regularUserView;
        this.clientService = clientService;
        this.contService = contService;
        this.reportService=reportService;
        this.idEmployeeLoggedIn=idEmployeeLoggedIn;

        regularUserView.setAddClientListener(new AddClientListener());
        regularUserView.setUpdateClientListener(new UpdateClientiListener());
        regularUserView.setViewClientListener(new ViewClientListener());

        regularUserView.setAddContListener(new AddContListener());
        regularUserView.setUpdateContListener(new UpdateContListener());
        regularUserView.setViewContListener(new ViewContListener());
        regularUserView.setDeleteContListener(new DeleteContListener());

        regularUserView.setTransferContListener(new TransferContListener());
        regularUserView.setProcessBillListener(new PlataBillContListener());

    }


    private class AddClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nume = regularUserView.getNume();
            String adresa = regularUserView.getAdresa();
            String CNP=regularUserView.getCodNumericPersonal();
            String idCardNumber=regularUserView.getIdCardNumber();




                if (clientService.existaClientDupaCNP(CNP)) {
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Clientul este deja inregistrat.");
                } else {
                    Notification<Boolean> notificationAddClient = clientService.save(nume, adresa, CNP, idCardNumber);

                    if (notificationAddClient.hasErrors()) {
                        JOptionPane.showMessageDialog(regularUserView.getContentPane(), notificationAddClient.getFormattedErrors());
                    } else {
                        if (notificationAddClient.getResult() == false) {
                            JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Inregistrarea clientului nu a reusit.");

                        } else {
                            java.util.Date utilDate = new java.util.Date();
                            reportService.save(idEmployeeLoggedIn, new Date(utilDate.getTime()), "Add Client");
                            JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Inregistrarea clientului a reusit.");
                        }
                    }
                }

        }


    }



    private class UpdateClientiListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            String nume = regularUserView.getNume();
            String adresa = regularUserView.getAdresa();
            String CNP = regularUserView.getCodNumericPersonal();
            String idCardNumber=regularUserView.getIdCardNumber();




                if (clientService.existaClientDupaCNP(CNP) == false) {
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Clientul nu exista, deci nu se poate executa operatia de update.");
                } else {
                    Notification<Boolean> notificationUpdateClient = clientService.update(nume, adresa, CNP, idCardNumber);


                    if (notificationUpdateClient.hasErrors()) {
                        JOptionPane.showMessageDialog(regularUserView.getContentPane(), notificationUpdateClient.getFormattedErrors());
                    } else {
                        if (notificationUpdateClient.getResult() == false) {
                            JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Updateul clientului nu a reusit.");

                        } else {
                            java.util.Date utilDate = new java.util.Date();
                            reportService.save(idEmployeeLoggedIn, new Date(utilDate.getTime()), "Update Client");
                            JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Updateul clientului a reusit.");
                        }
                    }
                }

        }
    }



    private class ViewClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Long id = Long.parseLong(regularUserView.getIdClient());
            String CNP = regularUserView.getCodNumericPersonal();
            try {
                Client client=clientService.findByCNP(CNP);

                regularUserView.getViewidClient().setText(Long.toString(client.getId()));
                regularUserView.getViewnume().setText(client.getNume());
                regularUserView.getViewadresa().setText(client.getAdresa());
                regularUserView.getViewCNP().setText(client.getCodNumericPersonal());
                regularUserView.getViewidentityCardNumber().setText(Integer.toString(client.getIdCardNumber()));

                //TO DO: inseamna ca s-a gasit clientul
                java.util.Date utilDate= new java.util.Date();
                reportService.save(idEmployeeLoggedIn,new Date(utilDate.getTime()),"View Client");
                JOptionPane.showMessageDialog(regularUserView.getContentPane(),"Clientul cerut a fost afisat, cautarea fiind facuta in functie de CNP.");
            } catch (EntityNotFoundException entityNotFoundException) {
                //TO DO: nu s-a gasit client -> notificam.
                JOptionPane.showMessageDialog(regularUserView.getContentPane(),"Clientul nu a fost gasit.");

                entityNotFoundException.printStackTrace();
            }

        }
    }

//--------------------------------------------------------------------------------------------------------------------------------\\

    private class AddContListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {




            String clientCnp = regularUserView.getCnpCreeareCont();
            String tip = regularUserView.getTip();
            String dataCreeari = regularUserView.getDataCreeari();
            String sold=regularUserView.getSold();
            String numarDeIdentificare =regularUserView.getNumarDeIdentificare();



            if (contService.existaContDupaNumarDeIdentificare(numarDeIdentificare)) {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Exista deja un cont cu numarul de identificare specificat!");
            } else {
                Notification<Boolean> notificationAddCont = contService.save(numarDeIdentificare, clientCnp, tip, sold, dataCreeari);

                if (notificationAddCont.hasErrors()) {
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), notificationAddCont.getFormattedErrors());
                } else {
                    if (notificationAddCont.getResult() == false) {
                        JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Inregistrarea contului nu a reusit.");

                    } else {

                        java.util.Date utilDate = new java.util.Date();
                        reportService.save(idEmployeeLoggedIn, new Date(utilDate.getTime()), "Add Cont");
                        JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Inregistrarea contului a reusit.");
                    }
                }
            }

        }
    }

    private class UpdateContListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Long id = Long.parseLong(regularUserView.getIdCont());
            String CNP= regularUserView.getCnpCreeareCont();
            String tip = regularUserView.getTip();
            String dataCreeari=regularUserView.getDataCreeari();
            String sold =regularUserView.getSold();
            String numarDeIdentificare=regularUserView.getNumarDeIdentificare();
                        if (contService.existaContDupaNumarDeIdentificare(numarDeIdentificare) == false) {
                            JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Contul nu exista, deci nu se poate executa operatia de update.");
                        } else {
                            Notification<Boolean> notificationUpdateCont = contService.update(CNP, numarDeIdentificare, tip, sold, dataCreeari);

                            if (notificationUpdateCont.hasErrors()) {
                                JOptionPane.showMessageDialog(regularUserView.getContentPane(), notificationUpdateCont.getFormattedErrors());
                            } else {
                                if (notificationUpdateCont.getResult() == false) {
                                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Updateul contului nu a reusit.");

                                } else {
                                    java.util.Date utilDate = new java.util.Date();
                                    reportService.save(idEmployeeLoggedIn, new Date(utilDate.getTime()), "Update Cont");
                                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Updateul contului a reusit.");
                                }
                            }
                        }
        }
    }

    private class DeleteContListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String numarDeIdentificare = regularUserView.getNumarDeIdentificare();


            if (contService.existaContDupaNumarDeIdentificare(numarDeIdentificare) == false) {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Contul corespunzator numarului de identificare nu exista, deci nu exista nimic de sters.");
            } else {
                boolean bool = contService.delete(numarDeIdentificare);
                if (bool) {
                    java.util.Date utilDate = new java.util.Date();
                    reportService.save(idEmployeeLoggedIn, new Date(utilDate.getTime()), "Delete Cont");
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Stergerea contului a reusit.");
                } else {
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Stergerea contului nu a reusit. Este posibil ca numarul de identificare dat sa nu corespunda cu un numar intreg.");
                }
            }
        }
    }


    private class ViewContListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String numarDeIdentificare = regularUserView.getNumarDeIdentificare();

                try {

                    Cont cont = contService.findByNumarDeIdentificare(numarDeIdentificare);
                    if(cont==null) {
                        JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Numarul de identificare pentru cautarea contului nu este corect introdus. Acesta trebuie sa fie un intreg");

                    }else {
                        JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Contul a fost gasit, si informatiile lui au fost afisate.");
                        java.util.Date utilDate = new java.util.Date();
                        reportService.save(idEmployeeLoggedIn, new Date(utilDate.getTime()), "View Cont");

                        regularUserView.getViewidCont().setText(Long.toString(cont.getId()));
                        regularUserView.getViewidClientCont().setText(Long.toString(cont.getClientId()));
                        regularUserView.getViewnumarDeIdentificare().setText(Integer.toString(cont.getNumarDeIndentificare()));
                        regularUserView.getViewtip().setText(cont.getTip());
                        regularUserView.getViewsold().setText(Double.toString(cont.getSold()));
                        regularUserView.getViewdataCreeari().setText(cont.getDataCreeari().toString());
                    }
                    //TO DO: inseamna ca s-a gasit clientul
                } catch (EntityNotFoundException entityNotFoundException) {
                    //TO DO: nu s-a gasit client -> notificam.
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Contul nu a fost gasit.");
                    entityNotFoundException.printStackTrace();
                }
        }
    }

    private class TransferContListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Long id = Long.parseLong(regularUserView.getIdCont());
            String numarDeIdentificareSender = regularUserView.getNrDeIdentificareSender();
            String numarDeIdentificareReceiver = regularUserView.getNrDeIdentificareReceiver();
            String sold= regularUserView.getSoldPentruTransfer();



            try {
                boolean booleanTransferDeBani = contService.transferSoldDintreConturi(numarDeIdentificareSender, numarDeIdentificareReceiver, sold);
                if (booleanTransferDeBani == false) {
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Operatia de transfer nu a reusit. Nu sunt destui bani disponibili sau datele introduse nu respecta formatul corespunzator: Numerele de identificare numere intregi, iar suma de transfer numar zecimal.");
                } else {
                    java.util.Date utilDate = new java.util.Date();
                    reportService.save(idEmployeeLoggedIn, new Date(utilDate.getTime()), "Transfer conturi");
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Transferul a fost realizat cu succes.");
                }
            } catch (EntityNotFoundException entityNotFoundException) {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Unul sau ambele conturi cerute nu sunt inregistrate. Transferul a esuat.");
                entityNotFoundException.printStackTrace();
            }



        }
    }

    private class PlataBillContListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Long id = Long.parseLong(regularUserView.getIdCont());
            String tipulFacturiiDePlata = regularUserView.getSelectedBill();
            String numarDeIdentificareCont = regularUserView.getNrDeIdentificareContPentruBill();
            String sumaDePlata= regularUserView.getSoldPentruBill();


                    try {
                        boolean booleanPlataBill = contService.plataBill(numarDeIdentificareCont, sumaDePlata);
                        if (booleanPlataBill == false) {
                            JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Plata pentru: " + tipulFacturiiDePlata + " a esuat. Contul nu are destui bani sau datele introduse nu respecta formatul. Numarul de identificare trebuie sa fie numar intreg, iar suma de plata trebuie sa fie numar zecimal.");
                        } else {
                            java.util.Date utilDate = new java.util.Date();
                            reportService.save(idEmployeeLoggedIn, new Date(utilDate.getTime()), "Pay bill");
                            JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Plata pentru: " + tipulFacturiiDePlata + " a fost realizata cu succes.");
                        }
                    } catch (EntityNotFoundException entityNotFoundException) {
                        JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Contul nu este inregistrat, deci plata pentru: " + tipulFacturiiDePlata + " a fost refuzata.");
                        entityNotFoundException.printStackTrace();
                    }



        }
    }



}
