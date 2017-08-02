package no.difi.dc2017.idporteneventapi.model;

import java.util.Date;

/**
 * Created by camp-oob on 27.06.2017.
 *
 *  Get the datatime, type and issuer for the activity data
 */
public class ActivityData {

    private String dateTime;
    private String type;
    private String issuer;

    public ActivityData(String dateTime, String type, String issuer){
        this.type = type;
        this.dateTime = dateTime;
        this.issuer = issuer;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getType() {
        return type;
    }

    public String getIssuer() {
        return issuer;
    }
}
