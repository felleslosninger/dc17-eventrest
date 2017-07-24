package no.difi.dc2017.idporteneventapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.difi.dc2017.idporteneventapi.controllers.EventController;
import no.difi.dc2017.idporteneventapi.data.EventRepository;
import no.difi.dc2017.idporteneventapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
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

    /**
    * get personal id
    * */
    public String getUserDetails() {
        // read access token from principal
        String at = oauth2ClientContext.getAccessToken().toString();
//        String idt = oauth2ClientContext.getAccessToken().getAdditionalInformation().toString();
        //Make api request to krr endpoint with access token as Authorization http header using RestTemplate
        //Header:
        //Authorization: Bearer <access_token>
        //https://oidc-ver2.difi.no/kontaktinfo-oauth2-server/rest/v1/person
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + at);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        HttpEntity<String> response = rt.exchange(
                URI,
                HttpMethod.GET,
                entity,
                String.class);


        String body = response.getBody();
        HashMap<String, String> result =
                null;
        try {
            result = new ObjectMapper().readValue(body, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return content from krr
        return result.get("pid");
    }

    /**
    * get postboks to the user with social security number
    * @param ssn
    * */
    public String getPostBoks(String ssn) {
        List<Event> postBox = eventData.getPostboks(ssn);
        if (postBox.size() == 0) {
            return "";
        }
        Event currentPostBox = postBox.get(0);
        if (currentPostBox.getIssuer().contains("e-boks")) {
            return "e-Boks";
        } else if (currentPostBox.getIssuer().contains("digi")) {
            return "Digipost";
        } else {
            return "";
        }

    }

/**
*   Get the authorization types that the user do not use
* */
    public List<AuthType> getUnusedAuthTypes() {
        List<AuthType> allAuthTypes = eventController.getAllAuthTypes();
        List<Integer> usedauthTypeIds = new ArrayList<>();
        List<Integer> authTypeIds = new ArrayList<>();
        List<Object[]> allUsedAuthTypes = eventController.getMostUsedAuthTypes();
        List<AuthType> unusedAuthTypes = new ArrayList<>();

        allUsedAuthTypes.forEach(authType -> {
            Integer authId = (Integer) authType[0];
            usedauthTypeIds.add(authId);
        });

        allAuthTypes.forEach(at -> {
            Integer authId = (int) (long) at.getId();
            authTypeIds.add(authId);
        });

        for (Integer i : authTypeIds) {
            if (!usedauthTypeIds.contains(i)) {
                Long id = i.longValue();
                unusedAuthTypes.add(eventController.getAuthTypeById(id));
            }
        }
        return unusedAuthTypes;
    }


    /**
     * get all services the user use according to his/hers id
     * */
    public List<AuthType> getUsedServices() {
        List<Object[]> mostUsed = eventController.getMostUsedAuthTypes();
        List<AuthType> used = new ArrayList<>();
        mostUsed.forEach(at -> {
            Integer id = (Integer) at[0];
            Long lId = id.longValue();
            used.add(eventController.getAuthTypeById(lId));
        });

        return used;

    }

    /**
     * get all services the user use according to his/hers social security number
     * */
    public List<ServiceData> getUsedServices(String ssn) {

        List<Event> events = eventData.getUsedServices(ssn);
        List<Integer> ids = Arrays.asList(51, 510, 605);
        ArrayList<ServiceData> data = new ArrayList<>();

        for (int i : ids) {
            LogType logType = eventController.getLogTypeById(i);
            data.add(new ServiceData(logType.getDescription(), false));
        }

        for (Event ev : events) {
            boolean isUsed = false;
            int logType = ev.getLogType();
            if (ids.contains(logType)) {
                data.get(ids.indexOf(logType)).setUsed(true);
            }
        }

        return data;
    }
    /**
     * get all of the user activity with data and time according to his/hers social security number
     * */
    public List<ActivityData> getRecentUserActivity(String ssn) {
        List<Event> events = eventData.getRecentUserActivity(ssn);

        ArrayList<ActivityData> activityList = new ArrayList<>();

        for (Event event : events) {
            String authType = eventController.getAuthTypeById(event.getAuthType()).getValue();
            activityList.add(new ActivityData(event.getDateTimeString(), authType, event.getIssuer()));
        }

        return activityList;
    }

    /**
     * get all of the public activity with data and time according to his/hers social security number
     * */
    public List<ActivityData> getRecentPublicActivity(String ssn) {
        List<Event> events = eventData.getRecentPublicActivity(ssn);

        ArrayList<ActivityData> activityList = new ArrayList<>();

        for (Event event : events) {
            String logType = eventController.getLogTypeById(event.getLogType()).getDescription();
            activityList.add(new ActivityData(event.getDateTimeString(), logType, event.getIssuer()));
        }
        return activityList;
    }
}
