package repository.report;

import model.Cont;
import model.Report;
import model.builder.ContConstructor;
import model.builder.ReportConstructor;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.EntityNotFoundException;
import repository.cont.ContRepository;
import repository.cont.ContRepositoryCacheDecorator;
import repository.cont.ContRepositoryMock;
import repository.user.UserRepository;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ReportRepositoryMockTest {
    private static ReportRepository reportRepository;

    @BeforeClass
    public static void setupClass(){
        reportRepository= new ReportRepositoryCacheDecorator(
                new ReportRepositoryMock(),
                new Cache<>());

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

        assertTrue(reportRepository.getReportsBetweenTwoDates(new Date(1899,10,10), new Date(1901,10,10),1L).size()==2);

    }
}
