package no.difi.dc2017.idporteneventapi.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import no.difi.dc2017.idporteneventapi.data.AuthTypeRepository;
import no.difi.dc2017.idporteneventapi.data.LogTypeRepository;
import no.difi.dc2017.idporteneventapi.data.StatYearRepository;
import no.difi.dc2017.idporteneventapi.model.*;
import no.difi.dc2017.idporteneventapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
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

    @RequestMapping(value = "/eventBySsn/{ssn}")
    public Collection<Event> eventBySsn(@PathVariable String ssn){
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
        return authTypeData.findOne(id);
    }

    @RequestMapping(value = "getAllAuthTypes", method = RequestMethod.GET)
    public List<AuthType> getAllAuthTypes(){
        return authTypeData.findAll();
    }

    @RequestMapping(value = "getMostUsedAuthTypes/{ssn}", method = RequestMethod.GET)
    public List<Object[]> getMostUsedId(@PathVariable String ssn){
        return eventData.getMostUsedAuthTypes(ssn);
    }

    @RequestMapping(value = "getLogTypeById/{id}", method = RequestMethod.GET)
    public LogType getLogTypeById(@PathVariable long id){
        return logTypeData.findOne(id);
    }

    @RequestMapping(value = "isReserved/{ssn}",method = RequestMethod.GET)
    public boolean isReserved(@PathVariable String ssn){
        return eventService.isReserved(ssn);
    }

    @RequestMapping(value = "getUsedServices/{ssn}", method = RequestMethod.GET)
    public List<ServiceData> getUserServices(@PathVariable String ssn){
        return eventService.getUsedServices(ssn);
    }

    @RequestMapping(value = "getRecentUserActivity/{ssn}", method = RequestMethod.GET)
    public List<ActivityData> getRecentUserActivity(@PathVariable String ssn) {
        return eventService.getRecentUserActivity(ssn);
    }

    @RequestMapping(value = "getRecentPublicActivity/{ssn}", method = RequestMethod.GET)
    public List<ActivityData> getRecentPublicActivity(@PathVariable String ssn){
        return eventService.getRecentPublicActivity(ssn);
    }

}
