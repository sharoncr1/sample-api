package com.mycom.thirdapp.services;

import com.mycom.thirdapp.db.models.ReportModel;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportParser {

    public List<ReportModel> processFiles(File error, File exception) throws IOException {
        List<ReportModel> errors = readCSV(error, true);
        List<ReportModel> exceptions = readCSV(exception, false);
        return combine(errors, exceptions);
    }

    private List<ReportModel> combine(List<ReportModel> errors, List<ReportModel> exceptions) {
        List<ReportModel> report;
        List<ReportModel> compare;
        if(errors.size()>exceptions.size()){
           report = new ArrayList<>(errors);
           compare = new ArrayList<>(exceptions);
        }
        else{
            report = new ArrayList<>(exceptions);
            compare = new ArrayList<>(errors);
        }

        List<ReportModel> toBeRemoved = new ArrayList<>();
        for ( ReportModel reportRow : report) {
            for ( ReportModel compareRow : compare) {
                if(reportRow.date.equalsIgnoreCase(compareRow.date) &&
                    reportRow.service.equalsIgnoreCase(compareRow.service)){
                    if(reportRow.errorCount.equalsIgnoreCase("0"))
                        reportRow.errorCount = compareRow.errorCount;
                    if(reportRow.exceptionCount.equalsIgnoreCase("0"))
                        reportRow.exceptionCount = compareRow.exceptionCount;
                    toBeRemoved.add(compareRow);
                }
            }
        }
        compare.removeAll(toBeRemoved);
        report.addAll(compare);
        preview(report);
        return report;
    }

    private void preview(List<ReportModel> report) {
        for ( ReportModel reportrow: report ) {
            System.out.println( reportrow.date +"\t"+ reportrow.service +"\t"+ reportrow.errorCount +"\t"+ reportrow.exceptionCount);
        }
    }


    public List<ReportModel> readCSV(File file, boolean isError) throws FileNotFoundException, IOException {
        List<ReportModel> rows = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine(); // Reading header, Ignoring

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] fields = line.split(",");
            fields[1] = fields[1].replaceAll("hsbc-prod-", "");
            fields[1] = fields[1].replaceAll("hsbc-prod", "");
            String serviceName =  (fields[1] +"-"+ fields[2]);
            if(serviceName.charAt(0)=='-'){
                serviceName = serviceName.replace("-","");
            }
            if(isError)
                rows.add(new ReportModel(fields[0], serviceName, fields[3],"0"));
            else
                rows.add(new ReportModel(fields[0], serviceName,"0", fields[3]));
        }
        br.close();
        return rows;
    }
}
