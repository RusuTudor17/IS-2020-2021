package controller;

import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;
import service.security.RightsRoleService;
import service.user.AuthenticationService;



import view.LoginView;
import view.RegularUserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private final LoginView loginView;
    private final AuthenticationService authenticationService;
    private final RightsRoleService rightsRoleService;
    ComponentFactory componentFactory;


    public LoginController(LoginView loginView, AuthenticationService authenticationService, RightsRoleService rightsRoleService,ComponentFactory componentFactory) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setRegisterButtonListener(new RegisterButtonListener());
        this.componentFactory=componentFactory;
        this.rightsRoleService=rightsRoleService;
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = null;
            try {
                loginNotification = authenticationService.login(username, password);
            } catch (AuthenticationException e1) {
                e1.printStackTrace();
            }

            if (loginNotification != null) {

                if (loginNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
                } else {
                    System.out.println(loginNotification.getResult().getRoles());
                    loginView.setVisible(false);
                    if(rightsRoleService.hasEmployeeRole(loginNotification.getResult())) {
                        System.out.println("am ajuns aici");
                        new RegularUserController(new RegularUserView(), componentFactory.getClientService(), componentFactory.getContService());
                    }
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");
                }
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");

                }
            }
        }
    }
}
