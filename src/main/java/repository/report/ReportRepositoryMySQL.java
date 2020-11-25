package repository.report;

import model.Report;
import model.builder.ReportConstructor;
import static database.Constants.Tables.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportRepositoryMySQL implements ReportRepository {
    private final Connection connection;

    public ReportRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }





    @Override
    public boolean save(Report report) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO "+REPORT+"  values (null,?, ?, ?)");
            insertStatement.setInt(1, report.getUserId().intValue());

            insertStatement.setDate(2, report.getDataActivitate());
            insertStatement.setString(3, report.getActivitate());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Report> getReportsBetweenTwoDates(Date startDate, Date endDate,Long userId)  {

        List<Report> reports = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from "+REPORT+" where idEmployee=? and dataEfectuarii BETWEEN ? AND ?");

            statement.setInt(1,userId.intValue());
            statement.setDate(2,startDate);
            statement.setDate(3,endDate);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                reports.add(getReportFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }


    private Report getReportFromResultSet(ResultSet rs) throws SQLException {
        return new ReportConstructor()
                .setId(rs.getLong("id"))
                .setUserId(rs.getLong("idEmployee"))
                .setDataActivitate(rs.getDate("dataEfectuarii"))
                .setActivitate(rs.getString("activitate"))
                .build();
    }
}
