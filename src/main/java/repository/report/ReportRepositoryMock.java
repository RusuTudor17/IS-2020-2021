package repository.report;

import model.Cont;
import model.Report;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportRepositoryMock implements ReportRepository {
    private List<Report> reports;
    public ReportRepositoryMock() {
        reports = new ArrayList<>();
    }


    @Override
    public boolean save(Report report) {
        return reports.add(report);
    }

    @Override
    public List<Report> getReportsBetweenTwoDates(Date startDate, Date endDate, Long userId) {
        List<Report> filteredConts = reports.parallelStream()
                .filter(it -> it.getUserId()==userId)
                .filter(it -> it.getDataActivitate().compareTo(endDate)<0 && it.getDataActivitate().compareTo(startDate)>0)
                .collect(Collectors.toList());
        return filteredConts;

    }


}
