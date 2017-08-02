package no.difi.dc2017.idporteneventapi.controllers;

import log.ConsoleLogger;
import log.FileLogger;
import no.difi.dc2017.idporteneventapi.data.AuthTypeRepository;
import no.difi.dc2017.idporteneventapi.data.EventRepository;
import no.difi.dc2017.idporteneventapi.data.LogTypeRepository;
import no.difi.dc2017.idporteneventapi.data.StatYearRepository;
import no.difi.dc2017.idporteneventapi.model.*;
import no.difi.dc2017.idporteneventapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/*Principal*/
/**
 * security holder
 **/

@RestController
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepository eventData;
    @Autowired
    private StatYearRepository statYearData;
    @Autowired
    private AuthTypeRepository authTypeData;
    @Autowired
    private LogTypeRepository logTypeData;

    FileLogger flogger = new FileLogger();
    ConsoleLogger clogger = new ConsoleLogger();

    public void loggString(char key, String methodname, String description, Principal principal){
        HashMap<String, String> userInfo = eventService.getUserDetails();
        flogger.logg(key, methodname + ": " +
                description +
            ", authorizationToken=\'" + userInfo.get("authorization") +
            "\', sub=\'" + userInfo.get("sub") +
                "\'clientId=\'" + principal.getName() + "\'");
    }

    @PreAuthorize("#oauth2.hasScope('profile')")
    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public Event event(@PathVariable long id, Principal principal){
        Event ev = eventData.findOne(id);
        loggString('i', "EventController.event", "return event", principal);
        return ev;
    }


    @RequestMapping(value = "/eventBySsn")
    public Collection<Event> eventBySsn(Principal principal){
        HashMap<String, String> userInfo = eventService.getUserDetails();
        loggString('i', "EventController.eventBySsn","return 10 latest events by ssn", principal);
        return eventData.findFirst10BySsnOrderByIdDesc(userInfo.get("ssn"));
    }

    @RequestMapping(value = "getLatestEvents", method = RequestMethod.GET)
    public Page<Event> getLatestEvents(Principal principal){
        PageRequest limit = new PageRequest(1,10 , Sort.Direction.DESC,"hour");
        Page<Event> evList = eventData.findAll(limit);
        loggString('i', "EventController.getLatestEvents", "return 10 latest events sorted by time", principal);
        return evList;
    }

    @RequestMapping(value = "getAuthTypeById/{id}", method = RequestMethod.GET)
    public AuthType getAuthTypeById(@PathVariable long id, Principal principal){

        loggString('i', "EventController.getAuthTypeById", "return authorization type by id", principal);
        return authTypeData.findOne(id);
    }

    @RequestMapping(value = "getAllAuthTypes", method = RequestMethod.GET)
    public List<AuthType> getAllAuthTypes(Principal principal){

        loggString('i', "EventController.getAllAuthTypes", "return all authorization types", principal);
        return authTypeData.findAll();
    }

    @RequestMapping(value = "getMostUsedAuthTypes", method = RequestMethod.GET)
    public List<Object[]> getMostUsedAuthTypes(Principal principal){
        HashMap<String, String> userInfo = eventService.getUserDetails();
        loggString('i', "EventController.getMostUsedAuthTypes", "return used authorization types and how many each of them is used", principal);
        return eventData.getMostUsedAuthTypes(userInfo.get("ssn"));
    }

    @RequestMapping(value = "getUnusedAuthTypes", method = RequestMethod.GET)
    public List<AuthType> getUnusedAuthTypes(Principal principal){
        loggString('i', "EventController.getUnusedAuthTypes", "return unused authorization types", principal);
        return eventService.getUnusedAuthTypes(principal);
    }

    @RequestMapping(value = "getLogTypeById/{id}", method = RequestMethod.GET)
    public LogType getLogTypeById(@PathVariable long id, Principal principal){
        loggString('i', "EventController.getLogTypeById", "return log type by id", principal);
        return logTypeData.findOne(id);
    }

    @RequestMapping(value = "getUsedServices", method = RequestMethod.GET)
    public List<ServiceData> getUserServices(Principal principal){
        HashMap<String, String> userInfo = eventService.getUserDetails();
        loggString('i', "EventController.getUsedServices", "return used and non-used services", principal);
        return eventService.getUsedServices(userInfo.get("ssn"), principal);
    }

    @RequestMapping(value = "getRecentUserActivity", method = RequestMethod.GET)
    public List<ActivityData> getRecentUserActivity(Principal principal) {
        HashMap<String, String> userInfo = eventService.getUserDetails();
        loggString('i', "EventController.getRecentUserActivity", "return recent user activity", principal);
        return eventService.getRecentUserActivity(userInfo.get("ssn"), principal);
    }

    @RequestMapping(value = "getRecentPublicActivity", method = RequestMethod.GET)
    public List<ActivityData> getRecentPublicActivity(Principal principal){
        HashMap<String, String> userInfo = eventService.getUserDetails();
        loggString('i', "EventController.getRecentPublicActivity", "return recent user activity from the public", principal);
        return eventService.getRecentPublicActivity(userInfo.get("ssn"), principal);
    }

    @RequestMapping(value="getPostBoks", method = RequestMethod.GET)
    public String getPostBoks(Principal principal){
        HashMap<String, String> userInfo = eventService.getUserDetails();
        loggString('i', "EventController.getPostBoks", "return the name of the post box to the user", principal);
        return eventService.getPostBoks(userInfo.get("ssn"));
    }



}
