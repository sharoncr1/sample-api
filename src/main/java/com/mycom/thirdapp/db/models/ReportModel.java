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

    public boolean isEqual(ReportModel reportModel){
        if(this.date.equalsIgnoreCase(reportModel.date) &&
            this.service.equalsIgnoreCase(reportModel.service)){
            return true;
        }
        return false;
    }

    public ReportModel() {
    }

    public String[] toStringArray() {
        return new String[]{this.date, this.service.replace("hsbc-prod-", ""),this.errorCount,this.exceptionCount,
                String.valueOf(Integer.parseInt(this.errorCount)+Integer.parseInt(this.exceptionCount))};
    }
}
