package no.difi.dc2017.idporteneventapi.controllers;

import java.util.*;

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

import log.ConsoleLogger;
import log.FileLogger;

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

    private ConsoleLogger clogg;
    private FileLogger flogg;

    public EventController(){
        clogg = new ConsoleLogger();
        flogg = new FileLogger();
    }

    @PreAuthorize("#oauth2.hasScope('profile')")
    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public Event event(@PathVariable long id){
        Event ev = eventData.findOne(id);
        return ev;
    }


    @RequestMapping(value = "/eventBySsn")
    public Collection<Event> eventBySsn(){
        String ssn = eventService.getUserDetails();
        flogg.logg('i', "Display event:" + ssn);
        return eventData.findFirst10BySsnOrderByIdDesc(ssn);
    }

    @RequestMapping(value = "getLatestEvents", method = RequestMethod.GET)
    public Page<Event> getLatestEvents(){
        PageRequest limit = new PageRequest(1,10 , Sort.Direction.DESC,"hour");
        Page<Event> evList = eventData.findAll(limit);
        flogg.logg('i', "Display event: " + evList);
        return evList;
    }

    @RequestMapping(value = "getAuthTypeById/{id}", method = RequestMethod.GET)
    public AuthType getAuthTypeById(@PathVariable long id){
        flogg.logg('i', "Display authorization type to " + id );
        return authTypeData.findOne(id);
    }

    @RequestMapping(value = "getAllAuthTypes", method = RequestMethod.GET)
    public List<AuthType> getAllAuthTypes(){
        flogg.logg('i', "Display all authorization types");
        return authTypeData.findAll();
    }

    @RequestMapping(value = "getMostUsedAuthTypes", method = RequestMethod.GET)
    public List<Object[]> getMostUsedAuthTypes(){
        String ssn = eventService.getUserDetails();
        flogg.logg('i', "Display most used authorization types to " + ssn);
        return eventData.getMostUsedAuthTypes(ssn);
    }

    @RequestMapping(value = "getUnusedAuthTypes", method = RequestMethod.GET)
    public List<AuthType> getUnusedAuthTypes(){
        flogg.logg('i', "Display unused authorization types");
        return eventService.getUnusedAuthTypes();
    }

    @RequestMapping(value = "getLogTypeById/{id}", method = RequestMethod.GET)
    public LogType getLogTypeById(@PathVariable long id){
        flogg.logg('i', "Display log type to " + id);
        return logTypeData.findOne(id);
    }

    @RequestMapping(value = "getUsedServices", method = RequestMethod.GET)
    public List<ServiceData> getUserServices(){
        String ssn = eventService.getUserDetails();
        flogg.logg('i', "Display user services to " + ssn);
        return eventService.getUsedServices(ssn);
    }

    @RequestMapping(value = "getRecentUserActivity", method = RequestMethod.GET)
    public List<ActivityData> getRecentUserActivity() {
        String ssn = eventService.getUserDetails();
        flogg.logg('i', "Display recently user activity to " + ssn);
        return eventService.getRecentUserActivity(ssn);
    }

    @RequestMapping(value = "getRecentPublicActivity", method = RequestMethod.GET)
    public List<ActivityData> getRecentPublicActivity(){
        String ssn = eventService.getUserDetails();
        flogg.logg('i', "Display recently public activity to " + ssn);
        return eventService.getRecentPublicActivity(ssn);
    }

    @RequestMapping(value="getPostBoks", method = RequestMethod.GET)
    public String getPostBoks(){
        String ssn = eventService.getUserDetails();
        flogg.logg('i', "Display postbox to " + ssn);
        return eventService.getPostBoks(ssn);
    }



}
