package no.difi.dc2017.idporteneventapi.model;

import java.util.Date;

/**
 * Created by camp-oob on 27.06.2017.
 */
public class ActivityData {

    private String dateTime;
    private String authType;
    private String issuer;

    public ActivityData(String dateTime, String authType, String issuer){
        this.authType = authType;
        this.dateTime = dateTime;
        this.issuer = issuer;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getAuthType() {
        return authType;
    }

    public String getIssuer() {
        return issuer;
    }
}
