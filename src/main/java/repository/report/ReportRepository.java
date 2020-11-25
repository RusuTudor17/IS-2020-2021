package repository.report;

import model.Cont;
import model.Report;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ReportRepository {
    boolean save(Report report);
    List<Report> getReportsBetweenTwoDates(Date startDate, Date endDate,Long userId);

}
