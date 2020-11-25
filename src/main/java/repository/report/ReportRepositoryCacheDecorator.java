package repository.report;

import model.Cont;
import model.Report;
import repository.Cache;
import repository.cont.ContRepositoryDecorator;

import java.sql.Date;
import java.util.List;

public class ReportRepositoryCacheDecorator extends ReportRepositoryDecorator {
    private Cache<Report> cache;

    public ReportRepositoryCacheDecorator(ReportRepository reportRepository, Cache<Report> cache) {
        super(reportRepository);
        this.cache = cache;
    }

    @Override
    public boolean save(Report report) {
        cache.invalidateCache();
        return decoratedRepository.save(report);
    }

    @Override
    public List<Report> getReportsBetweenTwoDates(Date startDate, Date endDate, Long userId) {
        return decoratedRepository.getReportsBetweenTwoDates(startDate, endDate, userId);
    }


}
