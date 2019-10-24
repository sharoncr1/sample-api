package com.mycom.thirdapp.db.models;

public class ReportModel {

    public String date;
    public String service;
    public String errorCount;
    public String exceptionCount;
    public Integer total;

    public ReportModel(String date, String service, String errorCount, String exceptionCount) {
        this.date = date;
        this.service = service;
        this.errorCount = errorCount;
        this.exceptionCount = exceptionCount;
    }

    public ReportModel() {
    }
}
