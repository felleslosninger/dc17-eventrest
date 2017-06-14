package no.difi.dc2017.idporteneventapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import no.difi.dc2017.idporteneventapi.data.EventRepository;
import no.difi.dc2017.idporteneventapi.model.Event;

@RestController
public class EventController {

    @Autowired
    private EventRepository eventData;

    @RequestMapping(value = "/addNewEvent.html", method = RequestMethod.POST)
    public String newEvent(Event event){
        eventData.save(event);
        return ("redirect:/listEvents.html");
    }

    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public Event event(@PathVariable long id){
        Event ev = eventData.findOne(id);
        return ev;
    }

    @RequestMapping(value = "/addNewEvent", method = RequestMethod.GET)
    public ModelAndView addNewEvent(){
        Event ev = new Event();
        return new ModelAndView("newEvent", "form", ev);
    }

    @RequestMapping(value = "/events" ,method = RequestMethod.GET)
    public List<Event> events(){
        return eventData.findAll();
    }

    /*
    @RequestMapping(value = "/listEvents.html" ,method = RequestMethod.GET)
    public ModelAndView events(){
        List<Event> allEvents = eventData.findAll();
        return new ModelAndView("allEvents", "events", allEvents);
    }
    */
}
