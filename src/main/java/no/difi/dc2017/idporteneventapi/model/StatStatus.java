package no.difi.dc2017.idporteneventapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by camp-oob on 20.06.2017.
 * Get id, logtype, year, month, day and issuer from sql-databasis.
 * */
@Entity
public class StatStatus {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    private int eventID;
    private int completedTime; //Should be datetime
    private int runMillis;
    private int configuredLimit;

    public long getId() {
        return id;
    }

    public int getEventID() {

        return eventID;
    }

    public int getCompletedTime() {
        return completedTime;
    }

    public int getRunMillis() {
        return runMillis;
    }

    public int getConfiguredLimit() {
        return configuredLimit;
    }

    @Override
    public String toString(){
        return "StatStatus{" +
                "id=\'" + id +
                "\', eventID=\'" + eventID +
                "\', completedTime\'" + completedTime +
                "\', runMillis\'" +runMillis +
                "\', configuredLimit\'" + configuredLimit +
                "\'}";
    }
}
