package controller;

import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;
import service.client.ClientService;
import service.cont.ContService;
import service.report.ReportService;
import service.security.RightsRoleService;



import service.user.UserService;
import view.AdministratorView;
import view.LoginView;
import view.RegularUserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LoginController {

    private final LoginView loginView;

    private final UserService userService;
    private final RightsRoleService rightsRoleService;

    private final ClientService clientService;
    private final ContService contService;
    private final ReportService reportService;


    public LoginController(LoginView loginView, UserService userService, RightsRoleService rightsRoleService, ContService contService, ClientService clientService, ReportService reportService) {
        this.loginView = loginView;
        this.userService = userService;
        this.contService=contService;
        this.clientService=clientService;
        this.rightsRoleService=rightsRoleService;
        this.reportService=reportService;

        loginView.setLoginButtonListener(new LoginButtonListener());
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = null;
            try {
                loginNotification = userService.login(username, password);
            } catch (AuthenticationException e1) {
                e1.printStackTrace();
            }

            if (loginNotification != null) {

                if (loginNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
                } else {
                    loginView.setVisible(false);
                    if(rightsRoleService.hasEmployeeRole(loginNotification.getResult())) {
                        Long idEmployeeLoggedIn= userService.viewUserByUsername(username).getId();
                        new RegularUserController(new RegularUserView(), clientService, contService,reportService,idEmployeeLoggedIn);
                    }
                    else
                    {
                        new AdministratorController(new AdministratorView(), userService, reportService);
                    }
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");
                }
            }
        }
    }


}
