package repository.user;

import database.FactoryDataBase;
import model.Report;
import model.Role;
import model.User;
import model.builder.ReportConstructor;
import model.builder.UserConstructor;
import model.validation.Notification;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.report.ReportRepository;
import repository.report.ReportRepositoryCacheDecorator;
import repository.report.ReportRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;

import java.sql.Date;
import java.util.Collections;

import static database.Constants.Roles.REGULAR_USER;
import static org.junit.Assert.assertTrue;

public class UserRepositoryMySQLTest {

    private static UserRepository userRepository;
    private static RightsRolesRepository rightsRolesRepository;
    @BeforeClass
    public static void setupClass(){

        rightsRolesRepository = new RightsRolesRepositoryMySQL(new FactoryDataBase().getConnectionWrapper(true).getConnection());
        userRepository = new UserRepositoryMySQL(new FactoryDataBase().getConnectionWrapper(true).getConnection(),rightsRolesRepository);

    }

    @Test
    public void save(){
        Role customerRole = rightsRolesRepository.findRoleByTitle(REGULAR_USER);
        User user = new UserConstructor()
                .setNume("rusuTudor2@yahoo.com")
                .setParola("parolaTudor1!")
                .setRoluri(Collections.singletonList(customerRole))
                .build();
        assertTrue(userRepository.save(user));
    }

    @Test
    public void update(){
        Role customerRole = rightsRolesRepository.findRoleByTitle(REGULAR_USER);
        User user = new UserConstructor()
                .setNume("rusuTudor2@yahoo.com")
                .setParola("parolaTudor1!2")
                .setRoluri(Collections.singletonList(customerRole))
                .build();
        assertTrue(userRepository.update(user));
    }

    @Test
    public void findByUsername(){
        assertTrue(userRepository.findByEmail("rusuTudor2@yahoo.com"));
    }

    @Test
    public void delete(){
        assertTrue(userRepository.delete("rusuTudor2@yahoo.com"));
    }


}
