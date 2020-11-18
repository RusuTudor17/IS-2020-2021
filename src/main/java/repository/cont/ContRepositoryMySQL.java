package repository.cont;


import model.Cont;

import model.builder.ContConstructor;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContRepositoryMySQL implements ContRepository{
    private final Connection connection;

    public ContRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean delete(int numarDeIdentificare){
        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement("DELETE FROM cont WHERE numarDeIdentificare=? ");

            updateStatement.setInt(1, numarDeIdentificare);

            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean update(int numarDeIdentificare, String tip,double sold, Date dataCreeari) {
        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement("UPDATE cont SET numarDeIdentificare=?, tip=?, sold=?, dataCreeari=? WHERE numarDeIdentificare=?");

            updateStatement.setInt(1, numarDeIdentificare);
            updateStatement.setString(2, tip);
            updateStatement.setDouble(3, sold);
            updateStatement.setDate(4, dataCreeari);
            updateStatement.setInt(5, numarDeIdentificare);

            // insertStatement.setDate(3, new Date(book.getPublishedDate().getTime()));
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean save(Cont cont) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO cont values (null, ?, ?, ?, ?)");
            insertStatement.setInt(1, cont.getNumarDeIndentificare());
            insertStatement.setString(2, cont.getTip());
            insertStatement.setDouble(3, cont.getSold());
            insertStatement.setDate(4, cont.getDataCreeari());


            // insertStatement.setDate(3, new Date(book.getPublishedDate().getTime()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cont findByNumarDeIdentificareOrId(Long id,int numarDeIdentificare) throws EntityNotFoundException {
        try {

            PreparedStatement statement = connection.prepareStatement("Select * from cont where numarDeIdentificare=? or id=?");
            statement.setInt(1,numarDeIdentificare);
            statement.setLong(2,id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return getContFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Cont.class.getSimpleName());
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Cont.class.getSimpleName());
        }
    }

    @Override
    public Cont findById(Long Id) throws EntityNotFoundException {
        try {

            PreparedStatement statement = connection.prepareStatement("Select * from cont where id=?");
            statement.setLong(1,Id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return getContFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(Id, Cont.class.getSimpleName());
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(Id, Cont.class.getSimpleName());
        }
    }


    @Override
    public List<Cont> findAll() {


        List<Cont> conts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sqlString = "Select * from cont";
            ResultSet rs = statement.executeQuery(sqlString);

            while (rs.next()) {
                conts.add(getContFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conts;
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

    private Cont getContFromResultSet(ResultSet rs) throws SQLException {
        return new ContConstructor()
                .setId(rs.getLong("id"))
                .setNumarDeIndentificare(rs.getInt("numarDeIdentificare"))
                .setTip(rs.getString("tip"))
                .setSold(rs.getDouble("sold"))
                .setDataCreeari(rs.getDate("dataCreeari"))
                .build();
    }
}
