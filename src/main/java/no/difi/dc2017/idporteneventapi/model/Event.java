package no.difi.dc2017.idporteneventapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    private String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", year=" + year +
                '}';
    }
}
