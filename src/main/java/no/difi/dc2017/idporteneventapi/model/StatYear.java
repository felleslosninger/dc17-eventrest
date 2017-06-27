package no.difi.dc2017.idporteneventapi.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatYear {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    public long getId() {
        return id;
    }

    private int logType;
    private int year;
    private String issuer;
    private String onBehalfOf;
    private int authType;
    private int authLevel;
    private int cnt;

    public int getLogType() {
        return logType;
    }

    public int getYear() {
        return year;
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
