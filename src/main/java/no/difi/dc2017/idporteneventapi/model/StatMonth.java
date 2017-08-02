package no.difi.dc2017.idporteneventapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by camp-ano on 20.06.2017.
 * Get id, logtype, year, month, day and issuer from sql-databasis.
 * */
@Entity
public class StatMonth {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private int logType;
    private int year;
    private int month;
    private String issuer;
    private String onBehalfOf;
    private int authType;
    private int authLevel;
    private int cnt;

    public long getId() {
        return id;
    }

    public int getLogType() {
        return logType;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getOnBehalfOf() {
        return onBehalfOf;
    }

    public int getAuthType() {
        return authType;
    }

    public int getAuthLevel() {
        return authLevel;
    }

    public int getCnt() {
        return cnt;
    }
}
