package no.difi.dc2017.idporteneventapi.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
* Get report type, line and linetext from sql-databasis.
* Get year, month, day, issuer, onBehalfOf and Cnt from sql-databasis
* */
@Entity
public class SnapshotStatHour {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String reportType;
    private int reportLine;
    private String reportLineText;
    private int year;
    private int month;
    private int day;
    private int hour;
    private String issuer;
    private String onBehalfOf;
    private int cnt;

    public long getId() {
        return id;
    }

    public String getReportType() {
        return reportType;
    }

    public int getReportLine() {
        return reportLine;
    }

    public String getReportLineText() {
        return reportLineText;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getOnBehalfOf() {
        return onBehalfOf;
    }

    public int getCnt() {
        return cnt;
    }

    @Override
    public String toString(){
        return "SnapshotStatHour{" +
                "id=\'" + id +
                "\', reportType=\'" + reportType +
                "\', reportLine=\'" + reportLine +
                "\', reportLineText=\'" + reportLineText +
                "\', year=\'" + year +
                "\', month=\'" + month +
                "\', day=\'" + day +
                "\', hour=\'" + hour +
                "\'}";
    }
}
