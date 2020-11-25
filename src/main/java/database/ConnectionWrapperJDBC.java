package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionWrapperJDBC {

    private Connection conexiuneJDBC;


    private static final String DRIVER_DATABASE = "com.mysql.jdbc.Driver";
    private static final String URL_BAZA_DE_DATE = "jdbc:mysql://localhost:3306/";

    private static final String USER_LOGIN = "root";
    private static final String PASSWORD_LOGIN = "root";


    private static final int TIMP_PENTRU_INVALIDARE = 5;



    public ConnectionWrapperJDBC(String schema_de_baza_de_date) {



            try {
                Class.forName(DRIVER_DATABASE);
                //System.out.println("da1");
                conexiuneJDBC = DriverManager.getConnection(URL_BAZA_DE_DATE + schema_de_baza_de_date, USER_LOGIN, PASSWORD_LOGIN);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


    }



    public Connection getConnection() {
        return conexiuneJDBC;
    }


    public boolean testConnection() throws SQLException {
        return conexiuneJDBC.isValid(TIMP_PENTRU_INVALIDARE);
    }



}
