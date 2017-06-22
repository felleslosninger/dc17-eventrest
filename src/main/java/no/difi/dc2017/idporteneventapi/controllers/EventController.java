package no.difi.dc2017.idporteneventapi.controllers;

import java.util.Collection;
import java.util.List;

import no.difi.dc2017.idporteneventapi.data.AuthTypeRepository;
import no.difi.dc2017.idporteneventapi.data.StatYearRepository;
import no.difi.dc2017.idporteneventapi.model.AuthType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import no.difi.dc2017.idporteneventapi.data.EventRepository;
import no.difi.dc2017.idporteneventapi.model.Event;
import no.difi.dc2017.idporteneventapi.model.StatYear;

@RestController
public class EventController {

    @Autowired
    private EventRepository eventData;
    @Autowired
    private StatYearRepository statYearData;
    @Autowired
    private AuthTypeRepository authTypeData;

    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public Event event(@PathVariable long id){
        Event ev = eventData.findOne(id);
        return ev;
    }

    @RequestMapping(value = "/events" ,method = RequestMethod.GET)
    public List<Event> events(){
        return eventData.findAll();
    }

    @RequestMapping(value = "/statyear/{id}", method = RequestMethod.GET)
    public StatYear statYear(@PathVariable long id){
        StatYear sY = statYearData.findOne(id);
        return sY;
    }

    @RequestMapping(value = "/statyearSize", method = RequestMethod.GET)
    public String statyearSize(){
        return String.valueOf(eventData.count());
    }

    @RequestMapping(value = "/eventBySsn/{ssn}")
    public Collection<Event> eventBySsn(@PathVariable String ssn){
        return eventData.findFirst10BySsnOrderByIdDesc(ssn);
    }

    @RequestMapping(value = "statYearByIssuer/{issuer}")
    public Collection<StatYear> statYearByIssuer(@PathVariable String issuer){
        return statYearData.findByIssuer(issuer);
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


}
