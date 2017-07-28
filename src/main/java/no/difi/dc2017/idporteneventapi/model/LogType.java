package no.difi.dc2017.idporteneventapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  get id, name and description for the log.
 */
@Entity
public class LogType {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    private String name;
    private String description;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "LogType{" +
                "id=\'" + id +
                "\', name=\'" + name +
                "\', description=\'" + description +
                "\'}";
    }
}
