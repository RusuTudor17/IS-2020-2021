package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class AdministratorView extends JFrame {
    //private JTextField id;
    private JTextField username;
    private JTextField parola;

    private JButton btnRegisterRegularUser;
    private JButton btnUpdateRegularUser;
    private JButton btnViewRegularUser;
    private JButton btnDeleteRegularUser;

    private JTextField viewId;
    //private JTextField viewParola;
    private JTextField viewUsername;






    private JTextField usernameGenerateReports;
    private JTextField dataDeInceputPentruCautareALogurilor;
    private JTextField dataDeFinalPentruCautareALogurilor;
    private JButton btnGenerateReportForEmployee;




    private JPanel administratorUserButtons;

    public AdministratorView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));

        add(username);
        add(parola);
        //add(id);

        administratorUserButtons.add(btnRegisterRegularUser);
        administratorUserButtons.add(btnUpdateRegularUser);
        administratorUserButtons.add(btnDeleteRegularUser);
        administratorUserButtons.add(btnViewRegularUser);
        add(administratorUserButtons);



        add(viewId);
        add(viewUsername);

        add(usernameGenerateReports);
        add(dataDeInceputPentruCautareALogurilor);
        add(dataDeFinalPentruCautareALogurilor);
        add(btnGenerateReportForEmployee);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void initializeFields() {
        administratorUserButtons = new JPanel();
        administratorUserButtons.setLayout(new BoxLayout(administratorUserButtons, BoxLayout.X_AXIS));

        //id=new JTextField("id- change email");
        username = new JTextField("email");
        parola = new JTextField("password");
        btnRegisterRegularUser = new JButton("Register");
        btnUpdateRegularUser = new JButton("Update");
        btnViewRegularUser=new JButton("View");
        btnDeleteRegularUser=new JButton("Delete");

        viewId=new JTextField("id");
        viewUsername=new JTextField("email");
        //viewParola= new JTextField("password");


        usernameGenerateReports= new JTextField("email employee");
        dataDeInceputPentruCautareALogurilor= new JTextField("data de inceput a raportului generat");
        dataDeFinalPentruCautareALogurilor= new JTextField("data de final a raportului generat");
        btnGenerateReportForEmployee=  new JButton("Generate Report");

        viewId.setEditable(false);
        viewUsername.setEditable(false);
        //viewParola.setVisible(false);

    }
    //public String getId(){return id.getText();}
    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return parola.getText();
    }

    public String getUsernameGenerateReports(){return usernameGenerateReports.getText();}
    public String getDataDeInceputPentruCautareALogurilor(){return dataDeInceputPentruCautareALogurilor.getText();}
    public String getDataDeFinalPentruCautareALogurilor(){return dataDeFinalPentruCautareALogurilor.getText();}



    public void setRegisterButtonListener(ActionListener registerButtonListener) {
        btnRegisterRegularUser.addActionListener(registerButtonListener);
    }

    public void setUpdateButtonListener(ActionListener updateButtonListener) {
        btnUpdateRegularUser.addActionListener(updateButtonListener);
    }

    public void setViewButtonListener(ActionListener viewButtonListener) {
        btnViewRegularUser.addActionListener(viewButtonListener);
    }

    public void setDeleteButtonListener(ActionListener deleteButtonListener) {
        btnDeleteRegularUser.addActionListener(deleteButtonListener);
    }

    public void setGenerateReportForEmployeeListener(ActionListener generateReportForEmployeeListener) {
        btnGenerateReportForEmployee.addActionListener(generateReportForEmployeeListener);
    }

    //----------------------------------------------------------------------------------------=
    public JTextField getViewId() {
        return viewId;
    }

    //public JTextField getViewParola() {return viewParola;}

    public JTextField getViewUsername() {
        return viewUsername;
    }
}

