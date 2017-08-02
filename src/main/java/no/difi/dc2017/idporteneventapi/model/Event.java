package no.difi.dc2017.idporteneventapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private String ssn; //social security number
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
    public Date getDate() throws ParseException{
        StringBuilder st = new StringBuilder();
        st.append(getYear());
        st.append('-');
        st.append(getMonth());
        st.append('-');
        st.append(getDay());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(st.toString());
        return date;
    }

    /**
     *
     * @return Time as a string in format hh.mm.ss
     */
    public Date getTime() throws ParseException {
        StringBuilder st = new StringBuilder();
        st.append(getHour());
        st.append(':');
        st.append(getMinute());
        st.append(':');
        st.append(getSecond());

        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        Date time = format.parse(st.toString());
        java.sql.Time ppstime = new java.sql.Time(time.getTime());
        return ppstime;
    }

    public String getDateTimeString(){
        StringBuilder st = new StringBuilder();
        st.append(getYear());
        st.append('-');
        st.append(getMonth());
        st.append('-');
        st.append(getDay());
        st.append(" ");
        st.append(getHour());
        st.append(':');
        st.append(getMinute());
        st.append(':');
        st.append(getSecond());
        return st.toString();
    }

    public DateTime getDateAndTime() {
        String myDate = getDateTimeString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date utilDate = new java.util.Date();
        try {
            utilDate = sdf.parse(myDate);
        } catch (ParseException pe){
            pe.printStackTrace();
        }
        DateTime dateTime = new DateTime(utilDate);

        return dateTime;
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
