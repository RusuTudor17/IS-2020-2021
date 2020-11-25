package service.report;

import database.FactoryDataBase;
import model.Report;
import model.Role;
import model.User;
import model.builder.ClientConstructor;
import model.builder.UserConstructor;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryCacheDecorator;
import repository.client.ClientRepositoryMySQL;
import repository.cont.ContRepository;
import repository.cont.ContRepositoryCacheDecorator;
import repository.cont.ContRepositoryMySQL;
import repository.report.ReportRepository;
import repository.report.ReportRepositoryCacheDecorator;
import repository.report.ReportRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.cont.ContService;
import service.cont.ContServiceImpl;

import java.sql.Date;
import java.util.Collections;

import static database.Constants.Roles.REGULAR_USER;
import static org.junit.Assert.assertTrue;

public class ReportServiceImplTest {
    private static ReportService reportService;

    @BeforeClass
    public static void setupClass(){
        ReportRepository reportRepository= new ReportRepositoryCacheDecorator(
                new ReportRepositoryMySQL(new FactoryDataBase().getConnectionWrapper(true).getConnection()),
                new Cache<>());
        reportService= new ReportServiceImpl(reportRepository);

        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(new FactoryDataBase().getConnectionWrapper(true).getConnection());
        UserRepository userRepository = new UserRepositoryMySQL(new FactoryDataBase().getConnectionWrapper(true).getConnection(),rightsRolesRepository);


        Role customerRole = rightsRolesRepository.findRoleByTitle(REGULAR_USER);
        User user = new UserConstructor()
                .setNume("rusuTudor2@yahoo.com")
                .setParola("parolaTudor1!")
                .setRoluri(Collections.singletonList(customerRole))
                .build();
        userRepository.save(user);
    }



    @Test
    public void save(){
        boolean bool= reportService.save(1L,new Date(1000,10,10),"Add Cont");
        assertTrue(bool);
    }

    @Test
    public void generateReportForAEmployeeBetweenTwoDates(){
        reportService.save(1L,new Date(1000,10,10),"Add Cont");
        reportService.save(1L,new Date(1000,10,10),"Add Client");
        reportService.save(1L,new Date(1000,10,10),"View Cont");//data echivalenta cu:2900-11-10
        boolean bool= reportService.generateReportForAEmployeeBetweenTwoDates("2899-11-10","2901-11-10",1L);
        assertTrue(bool);
    }
}
