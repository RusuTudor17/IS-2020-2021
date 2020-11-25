package service.report;

import model.Report;
import model.User;
import model.builder.ReportConstructor;
import model.builder.UserConstructor;
import model.validation.ReportValidator;
import repository.report.ReportRepository;
import repository.security.RightsRolesRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class ReportServiceImpl implements ReportService{
    private final ReportRepository repository;
    public ReportServiceImpl(ReportRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean save(Long idEmployee,Date dataEfectuarii,String activitate) {
        Report report = new ReportConstructor()
                .setId(null)
                .setUserId(idEmployee)
                .setDataActivitate(dataEfectuarii)
                .setActivitate(activitate)
                .build();
        return repository.save(report);
    }

    @Override
    public boolean generateReportForAEmployeeBetweenTwoDates(String startDateString, String endDateString, Long userId) {

        ReportValidator reportValidatorInput= new ReportValidator();
        boolean boolInputValidator=reportValidatorInput.dateValidator(startDateString) && reportValidatorInput.dateValidator(endDateString);




        if(boolInputValidator==false){
            return false;
        }

        Date startDate = Date.valueOf(startDateString);
        Date endDate= Date.valueOf(endDateString);
        List<Report> reports=repository.getReportsBetweenTwoDates(startDate,endDate,userId);


        try {
            File generateReport = new File("reportForEmployeeWithId" + userId + ".txt");
            generateReport.delete();
            if(generateReport.createNewFile()){
                FileWriter fileWriter = new FileWriter("reportForEmployeeWithId" + userId +  ".txt");
                for(Report report:reports)
                {
                    fileWriter.write("Employee-ul cu id-ul" + report.getUserId() + " a realizat activitatea de " + report.getActivitate() + " la data de: "+report.getDataActivitate() + "\n");

                }
                fileWriter.close();
                return true;
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return false;
    }


}
