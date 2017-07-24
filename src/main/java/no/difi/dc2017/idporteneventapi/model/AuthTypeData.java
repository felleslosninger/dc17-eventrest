package no.difi.dc2017.idporteneventapi.model;

/**
 * Created by camp-ano on 10.07.2017.
 *
 * Get the id, value and is used at the authtypedata.
 * */
public class AuthTypeData {

    private long id;
    private String value;
    private boolean used;

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public boolean isUsed() {
        return used;
    }

    public AuthTypeData(long id, String value, boolean used) {
        this.id = id;
        this.value = value;
        this.used = used;
    }

    @Override
    public String toString() {
        return "AuthTypeData{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", used=" + used +
                '}';
    }
}
