package repository.cont;


import model.Cont;

import model.builder.ContConstructor;
import repository.EntityNotFoundException;
import static database.Constants.Tables.*;

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
            PreparedStatement deleteStatement = connection
                    .prepareStatement("DELETE FROM "+CONT+" WHERE numarDeIdentificare=? ");

            deleteStatement.setInt(1, numarDeIdentificare);

            deleteStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean update(Long clientId,int numarDeIdentificare, String tip,double sold, Date dataCreeari) {
        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement("UPDATE "+CONT+" SET idClient=?,tip=?, sold=?, dataCreeari=? WHERE numarDeIdentificare=?");

            updateStatement.setInt(1,clientId.intValue());
            updateStatement.setString(2, tip);
            updateStatement.setDouble(3, sold);
            updateStatement.setDate(4, dataCreeari);
            updateStatement.setInt(5, numarDeIdentificare);
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
                    .prepareStatement("INSERT INTO "+CONT+"  values (null,?, ?, ?, ?, ?)");
            insertStatement.setInt(1, cont.getClientId().intValue());

            insertStatement.setInt(2, cont.getNumarDeIndentificare());
            insertStatement.setString(3, cont.getTip());
            insertStatement.setDouble(4, cont.getSold());
            insertStatement.setDate(5, cont.getDataCreeari());


            // insertStatement.setDate(3, new Date(book.getPublishedDate().getTime()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Cont findByNumarDeIdentificare(int numarDeIdentificare) throws EntityNotFoundException {
        try {

            PreparedStatement statement = connection.prepareStatement("Select * from "+CONT+" where numarDeIdentificare=?");
            statement.setInt(1,numarDeIdentificare);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return getContFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(Cont.class.getSimpleName());
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(Cont.class.getSimpleName());
        }
    }

    @Override
    public boolean setNewSold(int numarDeIdentificare,Double sold) {
        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement("UPDATE "+CONT+" SET sold=? WHERE numarDeIdentificare=?");

            updateStatement.setDouble(1, sold);
            updateStatement.setInt(2, numarDeIdentificare);

            updateStatement.executeUpdate();
            return true;
        }catch(SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean existaContDupaNumarDeIdentificare(int numarDeIdentificare) {
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from "+CONT+" where numarDeIdentificare=?");


            statement.setInt(1,numarDeIdentificare);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }


    @Override
    public Cont findById(Long Id) throws EntityNotFoundException {
        try {

            PreparedStatement statement = connection.prepareStatement("Select * from "+CONT+" where id=?");
            statement.setLong(1,Id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return getContFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(Cont.class.getSimpleName());
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(Cont.class.getSimpleName());
        }
    }


    @Override
    public List<Cont> findAll() {


        List<Cont> conts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sqlString = "Select * from "+CONT;
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
            String sql = "DELETE from "+CONT+" where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Cont getContFromResultSet(ResultSet rs) throws SQLException {
        return new ContConstructor()
                .setId(rs.getLong("id"))
                .setClientId(rs.getLong("idClient"))
                .setNumarDeIndentificare(rs.getInt("numarDeIdentificare"))
                .setTip(rs.getString("tip"))
                .setSold(rs.getDouble("sold"))
                .setDataCreeari(rs.getDate("dataCreeari"))
                .build();
    }
}
