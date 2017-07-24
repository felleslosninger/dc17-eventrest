package no.difi.dc2017.idporteneventapi.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
* Ge the id, eventid, eventdatatype and value for the eventdata
* */
@Entity
public class EventData {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private long eventId;
    private int eventDataType;
    private String value;

    public long getId() {
        return id;
    }

    public long getEventId() {
        return eventId;
    }

    public int getEventDataType() {
        return eventDataType;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "EventData{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", eventDataType=" + eventDataType +
                ", value='" + value + '\'' +
                '}';
    }
}
