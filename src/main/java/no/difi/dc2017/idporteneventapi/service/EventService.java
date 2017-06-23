package no.difi.dc2017.idporteneventapi.service;

import no.difi.dc2017.idporteneventapi.controllers.EventController;
import no.difi.dc2017.idporteneventapi.data.EventRepository;
import no.difi.dc2017.idporteneventapi.model.Event;
import no.difi.dc2017.idporteneventapi.model.LogType;
import no.difi.dc2017.idporteneventapi.model.ServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by camp-oob on 22.06.2017.
 */
@Service
public class EventService {

    @Autowired
    private EventRepository eventData;

    @Autowired
    private EventController eventController;

    public List<ServiceData> getUsedServices(String ssn) {
        List<Event> events = eventData.getUsedServices(ssn);

        ArrayList<ServiceData> data = new ArrayList<>();

        for(Event ev: events){
            LogType logType = eventController.getLogTypeById(ev.getLogType());
            ServiceData sD = new ServiceData(logType.getDescription(), true);
            data.add(sD);
        }

        return data;
    }

    public boolean isReserved(String ssn){
        List<Event> events = eventData.isReserved(ssn);

        if(events.size()>0){
            if(events.get(0).getLogType() == 515){
                return true;
            }
        }
        return false;
    }
}
