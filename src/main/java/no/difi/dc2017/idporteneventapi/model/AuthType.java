package no.difi.dc2017.idporteneventapi.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AuthType {
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

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AuthType{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
