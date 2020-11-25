package controller;

import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;
import service.report.ReportService;
import service.user.UserService;
import view.AdministratorView;
import view.LoginView;
import view.RegularUserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class AdministratorController {
    private final AdministratorView administratorView;
    private final UserService userService;
    private final ReportService reportService;

    public AdministratorController(AdministratorView administratorView, UserService userService, ReportService reportService) {
        this.administratorView = administratorView;
        this.userService = userService;
        this.reportService=reportService;

        administratorView.setRegisterButtonListener(new RegisterButtonListener());
        administratorView.setUpdateButtonListener(new UpdateButtonListener());
        administratorView.setViewButtonListener(new ViewButtonListener());
        administratorView.setDeleteButtonListener(new DeleteButtonListener());
        administratorView.setGenerateReportForEmployeeListener(new GenerateReportsButtonListener());
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = administratorView.getUsername();
            String password = administratorView.getPassword();
            if(userService.existaDupaEmail(username)==true)
            {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Inregistrarea employee-ului nu este posibila pentru acest user.");
            }
            else {
                Notification<Boolean> registerNotification = userService.register(username, password);
                if (registerNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(administratorView.getContentPane(), registerNotification.getFormattedErrors());
                } else {
                    if (!registerNotification.getResult()) {
                        JOptionPane.showMessageDialog(administratorView.getContentPane(), "Inregistrarea employee-ului nu a reusit.");
                    } else {
                        JOptionPane.showMessageDialog(administratorView.getContentPane(), "Inregistrarea employee-ului a reusit.");

                    }
                }
            }
        }

    }

    private class UpdateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = administratorView.getUsername();
            String password = administratorView.getPassword();
            if(userService.existaDupaEmail(username)==false)
            {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Updateul employee-ului nu este posibila pentru acest user.");
            }
            else {
                Notification<Boolean> updateNotification = userService.update(username, password);
                if (updateNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(administratorView.getContentPane(), updateNotification.getFormattedErrors());
                } else {
                    if (updateNotification.getResult()==false) {
                        JOptionPane.showMessageDialog(administratorView.getContentPane(), "Schimbarea parolei employee-ului nu a reusit.");
                    } else {
                        JOptionPane.showMessageDialog(administratorView.getContentPane(), "Schimbarea parolei employee-ului a reusit.");

                    }
                }
            }

        }
    }

    private class ViewButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = administratorView.getUsername();

            if(userService.existaDupaEmail(username)==false)
            {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Operatia de view asupra employee-ului nu este posibila pentru acest user. El cel mai probabil nu este inregistrat.");
            }
            else {
                User user = userService.viewUserByUsername(username);
                if (user != null) {
                    administratorView.getViewId().setText(user.getId().toString());
                    administratorView.getViewUsername().setText(user.getUsername());
                    JOptionPane.showMessageDialog(administratorView.getContentPane(), "Operatia de view a userului a reusit.");
                } else {
                    JOptionPane.showMessageDialog(administratorView.getContentPane(), "Operatia de view a esuat.");
                }
            }

        }
    }

    private class DeleteButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = administratorView.getUsername();

            if(userService.existaDupaEmail(username)==false)
            {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Operatia de delete asupra employee-ului nu este posibila pentru acest user. El cel mai probabil nu este inregistrat.");
            }
            else {
                boolean deleteBoolean = userService.delete(username);
                if (deleteBoolean) {
                    JOptionPane.showMessageDialog(administratorView.getContentPane(), "Stergerea employeeului a reusit.");
                } else {
                    JOptionPane.showMessageDialog(administratorView.getContentPane(), "Stergerea employeeului nu a reusit.");
                }
            }

        }
    }


    private class GenerateReportsButtonListener implements ActionListener {



        @Override
        public void actionPerformed(ActionEvent e) {

            String username= administratorView.getUsernameGenerateReports();
            String dateStart = administratorView.getDataDeInceputPentruCautareALogurilor();
            String dateFinal = administratorView.getDataDeFinalPentruCautareALogurilor();




            if (userService.existaDupaEmail(username) == false) {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Employee-ul nu este inregistrat, deci nu se pot genera logurile sale.");
            } else {
                Long idEmployeeFromUsername = userService.viewUserByUsername(username).getId();
                boolean booleanGenerareRaport = reportService.generateReportForAEmployeeBetweenTwoDates(dateStart, dateFinal, idEmployeeFromUsername);
                    if (booleanGenerareRaport) {
                        JOptionPane.showMessageDialog(administratorView.getContentPane(), "Raportul a fost generat cu succes.");

                    } else { JOptionPane.showMessageDialog(administratorView.getContentPane(), "Raportul nu a putut fi generat. Este posibil ca datele introduse sa nu fie corecte. Tine minte, datele trebuie sa respecte formatul yyyy-mm-dd Exemplu: 2002-01-17.");

                    }
            }


        }

    }
}
