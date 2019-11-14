package com.mycom.thirdapp.services;

import com.mycom.thirdapp.db.models.ReportModel;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReportParser {

    public List<ReportModel> processFiles(File error, File exception, File template, String date) throws IOException {
        List<ReportModel> errors = readCSV(error, true);
        List<ReportModel> exceptions = readCSV(exception, false);
        List<ReportModel> finalReport = readCSV(template, date);
        finalReport = combine(finalReport, errors, true);
        finalReport = combine(finalReport, exceptions, false);
        preview(finalReport);
        return finalReport;
    }

    private List<ReportModel> combine(List<ReportModel> report, List<ReportModel> watcherCount, boolean isError) {
        for ( ReportModel countRow : watcherCount ) {
            for ( ReportModel reportRow : report ) {
                if(countRow.isEqual(reportRow)){
                    if(isError){
                        reportRow.errorCount = countRow.errorCount;
                    }
                    else {
                        reportRow.exceptionCount = countRow.exceptionCount;
                    }
                }
            }
        }
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
            line = line.replaceAll("\"", "");
            String[] fields = line.split(",");
            String serviceName =  (fields[1] +"-"+ fields[2]);
            if(isError)
                rows.add(new ReportModel(fields[0], serviceName, fields[3],"0"));
            else
                rows.add(new ReportModel(fields[0], serviceName,"0", fields[3]));
        }
        br.close();
        return rows;
    }

    public List<ReportModel> readCSV(File file, String date) throws IOException {
        List<ReportModel> rows = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine(); // Reading header, Ignoring

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            line = line.replaceAll("\"", "");
            line = line.replaceAll("day1", date);
            line = line.replaceAll("day2", setDate(date, 1));
            line = line.replaceAll("day3", setDate(date, 2));
            line = line.replaceAll("day4", setDate(date, 3));
            line = line.replaceAll("day5", setDate(date, 4));
            line = line.replaceAll("day6", setDate(date, 5));
            line = line.replaceAll("day7", setDate(date, 6));

            String[] fields = line.split(",");
            String serviceName =  (fields[1] +"-"+ fields[2]);
            rows.add(new ReportModel(fields[0], serviceName, "0","0"));
        }
        br.close();
        return rows;
    }

    public String setDate(String date,int offsetInDays){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, offsetInDays);

        return simpleDateFormat.format(calendar.getTime());
    }

}
