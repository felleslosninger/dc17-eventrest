package no.difi.dc2017.idporteneventapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  Get the id and value for the eventdata.
 */
@Entity
public class EventDataType {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String value;

    public long getId() {

        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "EventDataType{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }

}
