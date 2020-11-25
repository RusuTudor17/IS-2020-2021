package service.report;

import model.Report;

import java.sql.Date;
import java.util.List;

public interface ReportService {
    boolean save(Long idEmployee,Date dataEfectuarii,String activitate);

    boolean generateReportForAEmployeeBetweenTwoDates(String startDate, String endDate, Long userId);
}
