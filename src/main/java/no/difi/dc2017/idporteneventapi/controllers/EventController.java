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




    @PreAuthorize("#oauth2.hasScope('profile')")
    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public Event event(@PathVariable long id){
        Event ev = eventData.findOne(id);
        return ev;
    }


    @RequestMapping(value = "/eventBySsn")
    public Collection<Event> eventBySsn(){
        String ssn = eventService.getUserDetails();
        return eventData.findFirst10BySsnOrderByIdDesc(ssn);
    }

    @RequestMapping(value = "getLatestEvents", method = RequestMethod.GET)
    public Page<Event> getLatestEvents(){
        PageRequest limit = new PageRequest(1,10 , Sort.Direction.DESC,"hour");
        Page<Event> evList = eventData.findAll(limit);
        return evList;
    }

    @RequestMapping(value = "getAuthTypeById/{id}", method = RequestMethod.GET)
    public AuthType getAuthTypeById(@PathVariable long id){
        System.out.println(eventService.getUserDetails());
        return authTypeData.findOne(id);
    }

    @RequestMapping(value = "getAllAuthTypes", method = RequestMethod.GET)
    public List<AuthType> getAllAuthTypes(){
        return authTypeData.findAll();
    }

    @RequestMapping(value = "getMostUsedAuthTypes", method = RequestMethod.GET)
    public List<Object[]> getMostUsedAuthTypes(){
        String ssn = eventService.getUserDetails();
        return eventData.getMostUsedAuthTypes(ssn);
    }

    @RequestMapping(value = "getUnusedAuthTypes", method = RequestMethod.GET)
    public List<AuthType> getUnusedAuthTypes(){
        return eventService.getUnusedAuthTypes();
    }

    @RequestMapping(value = "getLogTypeById/{id}", method = RequestMethod.GET)
    public LogType getLogTypeById(@PathVariable long id){
        return logTypeData.findOne(id);
    }

    @RequestMapping(value = "getUsedServices", method = RequestMethod.GET)
    public List<ServiceData> getUserServices(){
        String ssn = eventService.getUserDetails();
        return eventService.getUsedServices(ssn);
    }

    @RequestMapping(value = "getRecentUserActivity", method = RequestMethod.GET)
    public List<ActivityData> getRecentUserActivity() {
        String ssn = eventService.getUserDetails();
        return eventService.getRecentUserActivity(ssn);
    }

    @RequestMapping(value = "getRecentPublicActivity", method = RequestMethod.GET)
    public List<ActivityData> getRecentPublicActivity(){
        String ssn = eventService.getUserDetails();
        return eventService.getRecentPublicActivity(ssn);
    }

    @RequestMapping(value="getPostBoks", method = RequestMethod.GET)
    public String getPostBoks(){
        String ssn = eventService.getUserDetails();
        return eventService.getPostBoks(ssn);
    }



}
