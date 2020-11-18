package repository.client;

import model.Client;
import model.Role;
import model.User;
import model.builder.ClientConstructor;
import model.validation.Notification;
import model.validation.UserValidator;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ClientRepositoryMySQL implements ClientRepository{
    private final Connection connection;

    public ClientRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean update(String nume, String adresa, String codNumericPersonal, int idCardNumber) {
        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement("UPDATE client SET nume=?, adresa=?, codNumericPersonal=?, idCardNumber=? WHERE codNumericPersonal=?");

            updateStatement.setString(1, nume);
            updateStatement.setString(2, adresa);
            updateStatement.setString(3, codNumericPersonal);
            updateStatement.setInt(4, idCardNumber);
            updateStatement.setString(5, codNumericPersonal);

            // insertStatement.setDate(3, new Date(book.getPublishedDate().getTime()));
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean save(Client client) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO client values (null, ?, ?, ?, ?)");
            insertStatement.setString(1, client.getNume());
            insertStatement.setString(2, client.getAdresa());
            insertStatement.setString(3, client.getCodNumericPersonal());
            insertStatement.setInt(4, client.getIdCardNumber());

           // insertStatement.setDate(3, new Date(book.getPublishedDate().getTime()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existaClientDupaCNP(String CNP)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from client where  codNumericPersonal=?");
            statement.setString(1, CNP);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
    @Override
    public Client findByIdOrCNP(Long Id,String CNP) throws EntityNotFoundException {
        try {

            PreparedStatement statement = connection.prepareStatement("Select * from client where id=? or codNumericPersonal=?");
            statement.setLong(1,Id);
            statement.setString(2,CNP);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return getClientFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(Id, Client.class.getSimpleName());
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(Id, Client.class.getSimpleName());
        }
    }


    @Override
    public List<Client> findAll() {


        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sqlString = "Select * from client";
            ResultSet rs = statement.executeQuery(sqlString);

            while (rs.next()) {
                clients.add(getClientFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from client where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        return new ClientConstructor()
                .setId(rs.getLong("id"))
                .setNume(rs.getString("nume"))
                .setAdresa(rs.getString("adresa"))
                .setCodNumericPersonal(rs.getString("codNumericPersonal"))
                .setIdCardNumber(rs.getInt("idCardNumber"))
                .build();
    }
}
