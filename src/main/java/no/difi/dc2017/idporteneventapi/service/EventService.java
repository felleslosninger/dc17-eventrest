package no.difi.dc2017.idporteneventapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.difi.dc2017.idporteneventapi.controllers.EventController;
import no.difi.dc2017.idporteneventapi.data.EventRepository;
import no.difi.dc2017.idporteneventapi.model.ActivityData;
import no.difi.dc2017.idporteneventapi.model.Event;
import no.difi.dc2017.idporteneventapi.model.LogType;
import no.difi.dc2017.idporteneventapi.model.ServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
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

    @Autowired
    OAuth2ClientContext oauth2ClientContext;
    private final static String URI = "https://oidc-ver2.difi.no/idporten-oidc-provider/userinfo";

    public String getUserDetails() {
        // read access token from principal
        String at = oauth2ClientContext.getAccessToken().toString();
//        String idt = oauth2ClientContext.getAccessToken().getAdditionalInformation().toString();
        //Make api request to krr endpoint with access token as Authorization http header using RestTemplate
        //Header:
        //Authorization: Bearer <access_token>
        //https://oidc-ver2.difi.no/kontaktinfo-oauth2-server/rest/v1/person
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ at);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        HttpEntity<String> response = rt.exchange(
                URI,
                HttpMethod.GET,
                entity,
                String.class);


        System.out.println(response.getBody());
        String body = response.getBody();
        HashMap<String,String> result =
                null;
        try {
            result = new ObjectMapper().readValue(body, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return content from krr
        return result.get("pid");
    }


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
