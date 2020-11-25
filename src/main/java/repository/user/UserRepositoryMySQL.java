package repository.user;

import model.Client;
import model.User;
import model.builder.ClientConstructor;
import model.builder.UserConstructor;
import model.validation.Notification;
import repository.security.RightsRolesRepository;

import java.sql.*;
import java.util.List;

import static database.Constants.Tables.USER;
import static database.Constants.Tables.*;

public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByUsername(String username)  {

        try {
            PreparedStatement statement = connection.prepareStatement("Select * from "+USER+" where username=?");
            statement.setString(1, username);

            ResultSet userResultSet = statement.executeQuery();
            if(userResultSet.next())
            {
                return getUserFromResultSet(userResultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;


    }


    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from "+USER+" where username=? and password=?");
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet userResultSet = statement.executeQuery();
            if (userResultSet.next()) {
                User user = new UserConstructor()
                        .setNume(userResultSet.getString("username"))
                        .setParola(userResultSet.getString("password"))
                        .setRoluri(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();
                findByUsernameAndPasswordNotification.setResult(user);
                return findByUsernameAndPasswordNotification;
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid email or password!");
                return findByUsernameAndPasswordNotification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AuthenticationException();
        }
    }

    @Override
    public boolean update(User user) {
        try {
            PreparedStatement updateUserStatement = connection
                    .prepareStatement("UPDATE "+USER+" SET password=? where username=?", Statement.RETURN_GENERATED_KEYS);
            updateUserStatement.setString(1, user.getPassword());
            updateUserStatement.setString(2, user.getUsername());
            updateUserStatement.executeUpdate();

            ResultSet rs = updateUserStatement.getGeneratedKeys();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO "+USER+" values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(String username){
        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement("DELETE FROM "+USER+" WHERE username=? ");
            updateStatement.setString(1, username);
            updateStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean findByEmail(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from "+USER+" where username=?");
            statement.setString(1, username);

            ResultSet userResultSet = statement.executeQuery();
            return userResultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from "+USER+" where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new UserConstructor()
                .setId(rs.getLong("id"))
                .setNume(rs.getString("username"))
                .setParola(rs.getString("password"))
                .build();
    }

}
