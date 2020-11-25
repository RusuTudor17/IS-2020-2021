package database;

import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static database.Constants.Rights.RIGHTS;
import static database.Constants.Roles.ROLES;
import static database.Constants.Database.SCHEMAS;
import static database.Constants.getRolesRights;


public class BootStrap {
    private static RightsRolesRepository rightsRolesRepository;

    public static void main(String[] args) throws SQLException {
        dropAll();

         bootstrapTables();

        bootstrapUserData();
    }

    private static void dropAll() throws SQLException {
        for (String schema : SCHEMAS) {
            System.out.println("Dropping all tables in schema: " + schema);

            Connection connection = new ConnectionWrapperJDBC(schema).getConnection();
            Statement statement = connection.createStatement();

            String[] dropStatements = {
                    //"TRUNCATE role_right;",
                    "DROP TABLE if exists role_right;",
                   // "TRUNCATE `right`;",
                    //"TRUNCATE user_role;",
                    "DROP TABLE if exists user_role;",
                   // "TRUNCATE `role`;",
                    "DROP TABLE IF exists `report`",
                    "DROP TABLE if exists `user`;",
                    "DROP TABLE if exists `cont`;",
                    "DROP TABLE if exists `client`;",
                    "DROP TABLE if exists `role`;"


            };

            Arrays.stream(dropStatements).forEach(dropStatement -> {
                try {
                    statement.execute(dropStatement);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println("Done table bootstrap");
    }

    private static void bootstrapTables() throws SQLException {
        FactorySQLCreeareTabele factorySQLCreeareTabele = new FactorySQLCreeareTabele();

        for (String schema : SCHEMAS) {
            System.out.println("Bootstrapping " + schema + " schema");


            ConnectionWrapperJDBC connectionWrapper = new ConnectionWrapperJDBC(schema);
            Connection connection = connectionWrapper.getConnection();

            Statement statement = connection.createStatement();

            for (String table : Constants.Tables.ORDERED_TABLES_FOR_CREATION) {
                String createTableSQL = factorySQLCreeareTabele.getCreateSQLForTable(table);
                statement.execute(createTableSQL);
            }
        }

        System.out.println("Done table bootstrap");
    }

    private static void bootstrapUserData() throws SQLException {
        for (String schema : SCHEMAS) {
            System.out.println("Bootstrapping user data for " + schema);

            ConnectionWrapperJDBC connectionWrapper = new ConnectionWrapperJDBC(schema);
            rightsRolesRepository = new RightsRolesRepositoryMySQL(connectionWrapper.getConnection());

            bootstrapRoles();
            bootstrapRights();
            bootstrapRoleRight();
            bootstrapUserRoles();
        }
    }

    private static void bootstrapRoles() throws SQLException {
        for (String role : ROLES) {
            rightsRolesRepository.addRole(role);
        }
    }

    private static void bootstrapRights() throws SQLException {
        for (String right : RIGHTS) {
            rightsRolesRepository.addRight(right);
        }
    }

    private static void bootstrapRoleRight() throws SQLException {
        Map<String, List<String>> rolesRights = getRolesRights();

        for (String role : rolesRights.keySet()) {
            Long roleId = rightsRolesRepository.findRoleByTitle(role).getId();

            for (String right : rolesRights.get(role)) {
                Long rightId = rightsRolesRepository.findRightByTitle(right).getId();

                rightsRolesRepository.addRoleRight(roleId, rightId);
            }
        }
    }

    private static void bootstrapUserRoles() throws SQLException {

    }
}
