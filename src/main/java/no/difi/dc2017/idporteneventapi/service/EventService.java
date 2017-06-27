package no.difi.dc2017.idporteneventapi.service;

import no.difi.dc2017.idporteneventapi.controllers.EventController;
import no.difi.dc2017.idporteneventapi.data.EventRepository;
import no.difi.dc2017.idporteneventapi.model.ActivityData;
import no.difi.dc2017.idporteneventapi.model.Event;
import no.difi.dc2017.idporteneventapi.model.LogType;
import no.difi.dc2017.idporteneventapi.model.ServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
        List<Integer> ids = Arrays.asList(51,510,605);
        ArrayList<ServiceData> data = new ArrayList<>();

        for(int i : ids){
            LogType logType = eventController.getLogTypeById(i);
            data.add(new ServiceData(logType.getDescription(), false));
        }

        for(Event ev: events){
            boolean isUsed = false;
            int logType = ev.getLogType();
            if(ids.contains(logType)){
                data.get(ids.indexOf(logType)).setUsed(true);
                System.out.print("hello");
            }
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

    public List<ActivityData> getRecentUserActivity(String ssn){
        List<Event> events = eventData.getRecentUserActivity(ssn);

        ArrayList<ActivityData> activityList = new ArrayList<>();

        for(Event event : events){
            String authType = eventController.getAuthTypeById(event.getAuthType()).getValue();
            activityList.add(new ActivityData(event.getDateTimeString(),authType,event.getIssuer()));
        }

        return activityList;
    }

    public List<ActivityData> getRecentPublicActivity(String ssn){
        List<Event> events = eventData.getRecentPublicActivity(ssn);

        ArrayList<ActivityData> activityList = new ArrayList<>();

        for(Event event : events){
            String logType = eventController.getLogTypeById(event.getLogType()).getDescription();
            activityList.add(new ActivityData(event.getDateTimeString(),logType, event.getIssuer()));
        }
        return activityList;
    }
}
