package model.builder;

import model.Cont;
import model.Report;

import java.sql.Date;

public class ReportConstructor {
    private Report report;

    public ReportConstructor() {
        report=new Report();
    }

    public ReportConstructor setId(Long Id)
    {
        report.setId(Id);
        return this;
    }

    public ReportConstructor setUserId(Long userId)
    {
        report.setUserId(userId);
        return this;
    }

    public ReportConstructor setActivitate(String activitate) {
        report.setActivitate(activitate);
        return this;
    }

    public ReportConstructor setDataActivitate(Date dataActivitate) {
        report.setDataActivitate(dataActivitate);
        return this;
    }

    public Report build() {
        return report;
    }
}
