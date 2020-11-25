package repository.report;

import database.FactoryDataBase;
import model.Report;
import model.Role;
import model.User;
import model.builder.ReportConstructor;
import model.builder.UserConstructor;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.security.RightsRoleService;

import java.sql.Date;
import java.util.Collections;

import static database.Constants.Roles.REGULAR_USER;
import static org.junit.Assert.assertTrue;

public class ReportRepositoryMySQLTest {
    private static ReportRepository reportRepository;

    @BeforeClass
    public static void setupClass(){
        reportRepository= new ReportRepositoryCacheDecorator(
                new ReportRepositoryMySQL(new FactoryDataBase().getConnectionWrapper(true).getConnection()),
                new Cache<>());
        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(new FactoryDataBase().getConnectionWrapper(true).getConnection());
        UserRepository userRepository = new UserRepositoryMySQL(new FactoryDataBase().getConnectionWrapper(true).getConnection(),rightsRolesRepository);
        Role customerRole = rightsRolesRepository.findRoleByTitle(REGULAR_USER);
        User user = new UserConstructor()
                .setNume("rusuTudor@yahoo.com")
                .setParola("parolaTudor1!")
                .setRoluri(Collections.singletonList(customerRole))
                .build();


        userRepository.save(user);
    }



    @Test
    public void save(){
        Report report=new ReportConstructor()
                .setActivitate("Add Client")
                .setDataActivitate(new Date(1000,10,10))
                .setUserId(1L)
                .build();
        assertTrue(reportRepository.save(report));
    }



    @Test
    public void getReportsBetweenTwoDates(){
        Report report=new ReportConstructor()
                .setActivitate("Add Client")
                .setDataActivitate(new Date(1900,10,10))
                .setUserId(1L)
                .build();
        reportRepository.save(report);

        Report report1=new ReportConstructor()
                .setActivitate("Add Cont")
                .setDataActivitate(new Date(1900,10,10))
                .setUserId(1L)
                .build();
        reportRepository.save(report1);

        assertTrue(reportRepository.getReportsBetweenTwoDates(new Date(1899,10,10), new Date(1901,10,10),1L).size()>=2);

    }
}
