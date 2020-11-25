package repository.report;

import repository.cont.ContRepository;

public abstract class ReportRepositoryDecorator implements ReportRepository{
    protected ReportRepository decoratedRepository;
    public ReportRepositoryDecorator(ReportRepository reportRepository) {
        this.decoratedRepository = reportRepository;
    }
}
