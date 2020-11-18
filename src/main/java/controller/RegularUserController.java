package controller;

import model.Client;
import model.Cont;
import model.User;
import model.builder.ClientConstructor;
import model.builder.ContConstructor;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.user.AuthenticationException;
import service.client.ClientService;
import service.cont.ContService;

import view.RegularUserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class RegularUserController {

    private final RegularUserView regularUserView;
    private final ClientService clientService;
    private final ContService contService;


    public RegularUserController(RegularUserView regularUserView, ClientService clientService, ContService contService) {
        this.regularUserView = regularUserView;
        this.clientService = clientService;
        this.contService = contService;

        regularUserView.setAddClientListener(new AddClientListener());
        regularUserView.setUpdateClientListener(new UpdateClientiListener());
        regularUserView.setViewClientListener(new ViewClientListener());

        regularUserView.setAddContListener(new AddContListener());
        regularUserView.setUpdateContListener(new UpdateContListener());
        regularUserView.setViewContListener(new ViewContListener());
        regularUserView.setDeleteContListener(new DeleteContListener());

    }


    private class AddClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nume = regularUserView.getNume();
            String adresa = regularUserView.getAdresa();
            String CNP=regularUserView.getCodNumericPersonal();
            int idCardNumber= Integer.parseInt(regularUserView.getIdCardNumber());


            if(clientService.existaClientDupaCNP(CNP))
            {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(),"Clientul este deja inregistrat.");
            }
            else {
                Notification<Boolean> notificationAddClient = clientService.save(nume, adresa, CNP, idCardNumber);

                if (notificationAddClient.hasErrors()) {
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), notificationAddClient.getFormattedErrors());
                } else {
                    if (notificationAddClient.getResult() == false) {
                        JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Inregistrarea clientului nu a reusit.");

                    } else {


                        JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Inregistrarea clientului a reusit.");
                    }
                }
            }


        }
    }


    private class UpdateClientiListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Long id = Long.parseLong(regularUserView.getIdClient());
            String nume = regularUserView.getNume();
            String adresa = regularUserView.getAdresa();
            String CNP = regularUserView.getCodNumericPersonal();
            int idCardNumber = Integer.parseInt(regularUserView.getIdCardNumber());
            if(clientService.existaClientDupaCNP(CNP)==false)
            {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(),"Clientul nu exista, deci nu se poate executa operatia de update.");
            }
            else {
                Notification<Boolean> notificationUpdateClient = clientService.update(nume, adresa, CNP, idCardNumber);


                if (notificationUpdateClient.hasErrors()) {
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), notificationUpdateClient.getFormattedErrors());
                } else {
                    if (notificationUpdateClient.getResult() == false) {
                        JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Updateul clientului nu a reusit.");

                    } else {
                        JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Updateul clientului a reusit.");
                    }
                }
            }
        }

    }

    private class ViewClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long id = Long.parseLong(regularUserView.getIdClient());
            String CNP = regularUserView.getCodNumericPersonal();
            try {
                Client c=clientService.findByIdOrCNP(id,CNP);

                regularUserView.getViewidClient().setText(Long.toString(c.getId()));
                regularUserView.getViewnume().setText(c.getNume());
                regularUserView.getViewadresa().setText(c.getAdresa());
                regularUserView.getViewCNP().setText(c.getCodNumericPersonal());
                regularUserView.getViewidentityCardNumber().setText(Integer.toString(c.getIdCardNumber()));

                //TO DO: inseamna ca s-a gasit clientul
                JOptionPane.showMessageDialog(regularUserView.getContentPane(),"Clientul cerut a fost afisat, cautarea fiind facuta in functie de id SAU cnp.");
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
            int numarDeIdentificare = Integer.parseInt(regularUserView.getNumarDeIdentificare());
            String tip = regularUserView.getTip();
            Double sold=Double.parseDouble(regularUserView.getSold());
            Date dataCreeari= Date.valueOf(regularUserView.getDataCreeari());


            Notification<Boolean> notificationAddCont=contService.save(numarDeIdentificare,tip,sold,dataCreeari);

            if(notificationAddCont.hasErrors())
            {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(),notificationAddCont.getFormattedErrors());
            }
            else
            {
                if(notificationAddCont.getResult()==false)
                {
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(),"Inregistrarea contului nu a reusit.");

                }
                else
                {
                    //TO DO:
                    /*if(clientService.existaClientDupaCNP(CNP))
                    {
                        JOptionPane.showMessageDialog(regularUserView.getContentPane(),"Clientul este deja inregistrat.");
                    }
                    else*/
                        JOptionPane.showMessageDialog(regularUserView.getContentPane(),"Inregistrarea contului a reusit.");
                }
            }
        }
    }

    private class UpdateContListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Long id = Long.parseLong(regularUserView.getIdCont());
            int numarDeIdentificare = Integer.parseInt(regularUserView.getNumarDeIdentificare());
            String tip = regularUserView.getTip();
            Double sold = Double.parseDouble(regularUserView.getSold());
            Date dataCreeari = Date.valueOf(regularUserView.getDataCreeari());
            boolean bool = contService.update(numarDeIdentificare,tip,sold,dataCreeari);
            System.out.println(bool);

        }
    }

    private class DeleteContListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int numarDeIdentificare = Integer.parseInt(regularUserView.getNumarDeIdentificare());
            boolean bool = contService.delete(numarDeIdentificare);
        }
    }


    private class ViewContListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long id = Long.parseLong(regularUserView.getIdCont());
            int numarDeIdentificare = Integer.parseInt(regularUserView.getNumarDeIdentificare());

            try {
                Cont c = contService.findByNumarDeIdentificareOrId(id, numarDeIdentificare);
                regularUserView.getViewidCont().setText(Long.toString(c.getId()));
                regularUserView.getViewnumarDeIdentificare().setText(Integer.toString(c.getNumarDeIndentificare()));
                regularUserView.getViewtip().setText(c.getTip());
                regularUserView.getViewsold().setText(Double.toString(c.getSold()));
                regularUserView.getViewdataCreeari().setText(c.getDataCreeari().toString());

                //TO DO: inseamna ca s-a gasit clientul
            } catch (EntityNotFoundException entityNotFoundException) {
                //TO DO: nu s-a gasit client -> notificam.
                entityNotFoundException.printStackTrace();
            }
            //System.out.println(bool);
        }
    }
}
