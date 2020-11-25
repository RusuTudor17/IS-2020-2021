package repository.client;

import model.Client;
import model.builder.ClientConstructor;
import repository.EntityNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static database.Constants.Tables.*;


public class ClientRepositoryMySQL implements ClientRepository{
    private final Connection connection;

    public ClientRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean update(String nume, String adresa, String codNumericPersonal, int idCardNumber) {
        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement("UPDATE "+ CLIENT+" SET nume=?, adresa=?, idCardNumber=? WHERE codNumericPersonal=?");

            updateStatement.setString(1, nume);
            updateStatement.setString(2, adresa);

            updateStatement.setInt(3, idCardNumber);
            updateStatement.setString(4, codNumericPersonal);

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
                    .prepareStatement("INSERT INTO "+ CLIENT+" values (null, ?, ?, ?, ?)");
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

    public Long idClientByCNP(String CNP)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from "+CLIENT+" where  codNumericPersonal=?");
            statement.setString(1, CNP);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                Client client= getClientFromResultSet(rs);
                return client.getId();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public boolean existaClientDupaCNP(String CNP)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from "+CLIENT+" where  codNumericPersonal=?");
            statement.setString(1, CNP);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
    @Override
    public Client findByCNP(String CNP) throws EntityNotFoundException {
        try {

            PreparedStatement statement = connection.prepareStatement("Select * from "+CLIENT+" where codNumericPersonal=?");
            statement.setString(1,CNP);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return getClientFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(Client.class.getSimpleName());
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(Client.class.getSimpleName());
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
            String sql = "DELETE from "+CLIENT+" where id >= 0";
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
