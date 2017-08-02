package no.difi.dc2017.idporteneventapi.controllers;

import java.security.Principal;
import java.util.*;
import log.*;

import no.difi.dc2017.idporteneventapi.data.AuthTypeRepository;
import no.difi.dc2017.idporteneventapi.data.LogTypeRepository;
import no.difi.dc2017.idporteneventapi.data.StatYearRepository;
import no.difi.dc2017.idporteneventapi.model.*;
import no.difi.dc2017.idporteneventapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import no.difi.dc2017.idporteneventapi.data.EventRepository;

/*Principal*/
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/** security holder **/
import org.springframework.security.oauth2.provider.ClientDetails;

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

    public void loggString(char key, String methodname, Principal principal){
        HashMap<String, String> userInfo = eventService.getUserDetails();
        flogger.logg(key, methodname + ": " +
            "authorizationToken=\'" + userInfo.get("authorization") +
            "\', sub=\'" + userInfo.get("sub") +
                "\'clientId=\'" + principal.getName() + "\'");
    }

    @PreAuthorize("#oauth2.hasScope('profile')")
    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public Event event(@PathVariable long id, Principal principal){
        Event ev = eventData.findOne(id);
        loggString('i', "EventController.event", principal);
        return ev;
    }


    @RequestMapping(value = "/eventBySsn")
    public Collection<Event> eventBySsn(Principal principal){
        HashMap<String, String> userInfo = eventService.getUserDetails();
        loggString('i', "EventController.eventBySsn", principal);
        return eventData.findFirst10BySsnOrderByIdDesc(userInfo.get("ssn"));
    }

    @RequestMapping(value = "getLatestEvents", method = RequestMethod.GET)
    public Page<Event> getLatestEvents(Principal principal){
        PageRequest limit = new PageRequest(1,10 , Sort.Direction.DESC,"hour");
        Page<Event> evList = eventData.findAll(limit);
        loggString('i', "EventController.getLatestEvents", principal);
        return evList;
    }

    @RequestMapping(value = "getAuthTypeById/{id}", method = RequestMethod.GET)
    public AuthType getAuthTypeById(@PathVariable long id, Principal principal){

        loggString('i', "EventController.getAuthTypeById", principal);
        return authTypeData.findOne(id);
    }

    @RequestMapping(value = "getAllAuthTypes", method = RequestMethod.GET)
    public List<AuthType> getAllAuthTypes(Principal principal){

        loggString('i', "EventController.getAllAuthTypes", principal);
        return authTypeData.findAll();
    }

    @RequestMapping(value = "getMostUsedAuthTypes", method = RequestMethod.GET)
    public List<Object[]> getMostUsedAuthTypes(Principal principal){
        HashMap<String, String> userInfo = eventService.getUserDetails();
        loggString('i', "EventController.getMostUsedAuthTypes", principal);
        return eventData.getMostUsedAuthTypes(userInfo.get("ssn"));
    }

    @RequestMapping(value = "getUnusedAuthTypes", method = RequestMethod.GET)
    public List<AuthType> getUnusedAuthTypes(Principal principal){
        loggString('i', "EventController.getUnusedAuthTypes", principal);
        return eventService.getUnusedAuthTypes(principal);
    }

    @RequestMapping(value = "getLogTypeById/{id}", method = RequestMethod.GET)
    public LogType getLogTypeById(@PathVariable long id, Principal principal){
        loggString('i', "EventController.getLogTypeById", principal);
        return logTypeData.findOne(id);
    }

    @RequestMapping(value = "getUsedServices", method = RequestMethod.GET)
    public List<ServiceData> getUserServices(Principal principal){
        HashMap<String, String> userInfo = eventService.getUserDetails();
        loggString('i', "EventController.getUsedServices", principal);
        return eventService.getUsedServices(userInfo.get("ssn"), principal);
    }

    @RequestMapping(value = "getRecentUserActivity", method = RequestMethod.GET)
    public List<ActivityData> getRecentUserActivity(Principal principal) {
        HashMap<String, String> userInfo = eventService.getUserDetails();
        loggString('i', "EventController.getRecentUserActivity", principal);
        return eventService.getRecentUserActivity(userInfo.get("ssn"), principal);
    }

    @RequestMapping(value = "getRecentPublicActivity", method = RequestMethod.GET)
    public List<ActivityData> getRecentPublicActivity(Principal principal){
        HashMap<String, String> userInfo = eventService.getUserDetails();
        loggString('i', "EventController.getRecentPublicActivity", principal);
        return eventService.getRecentPublicActivity(userInfo.get("ssn"), principal);
    }

    @RequestMapping(value="getPostBoks", method = RequestMethod.GET)
    public String getPostBoks(Principal principal){
        HashMap<String, String> userInfo = eventService.getUserDetails();
        loggString('i', "EventController.getPostBoks", principal);
        return eventService.getPostBoks(userInfo.get("ssn"));
    }



}
