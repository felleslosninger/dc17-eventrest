package no.difi.dc2017.idporteneventapi.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EventData {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private int eventId;
    private int eventDataType;
    private String value;

    public long getId() {
        return id;
    }

    public int getEventId() {
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
