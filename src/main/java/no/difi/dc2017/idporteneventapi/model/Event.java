package no.difi.dc2017.idporteneventapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Event {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private String ssn;
    private int logType;
    private int authType;
    private String issuer;
    private String onBehalfOf;
    private boolean reqForceAuth;
    private int reqAuthLevel;


    /**
     *
     * @return Date as a string in format dd.mm.yyyy
     */
    public String getDate(){
        StringBuilder st = new StringBuilder();
        st.append(getDay());
        st.append('.');
        st.append(getMonth());
        st.append('.');
        st.append(getYear());
        return st.toString();
    }

    /**
     *
     * @return Time as a string in format hh.mm.ss
     */
    public String getTime(){
        StringBuilder st = new StringBuilder();
        st.append(getHour());
        st.append(':');
        st.append(getMinute());
        st.append(':');
        st.append(getSecond());
        return st.toString();
    }

    public long getId() {
        return this.id;
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

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public String getSsn() {
        return ssn;
    }

    public int getLogType() {
        return logType;
    }

    public int getAuthType() {
        return authType;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getOnBehalfOf() {
        return onBehalfOf;
    }

    public boolean isReqForceAuth() {
        return reqForceAuth;
    }

    public int getReqAuthLevel() {
        return reqAuthLevel;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                ", ssn='" + ssn + '\'' +
                ", logType=" + logType +
                ", authType=" + authType +
                ", issuer='" + issuer + '\'' +
                ", onBehalfOf='" + onBehalfOf + '\'' +
                ", reqForceAuth=" + reqForceAuth +
                ", reqAuthLevel=" + reqAuthLevel +
                '}';
    }
}
