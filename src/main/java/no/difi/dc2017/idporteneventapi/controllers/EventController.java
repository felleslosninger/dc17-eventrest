package no.difi.dc2017.idporteneventapi.controllers;


import no.difi.dc2017.idporteneventapi.model.Event;
import no.difi.dc2017.idporteneventapi.data.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventData;

    @RequestMapping(value = "/addNewEvent.html", method = RequestMethod.POST)
    public String newEvent(Event event){
        eventData.save(event);
        return ("redirect:/listEvents.html");
    }


    @RequestMapping(value = "/addNewEvent.html", method = RequestMethod.GET)
    public ModelAndView addNewEvebt() {

        Event event = new Event();
        return new ModelAndView("newEvent", "form", event);

    }

    @RequestMapping(value = "/listEvents.html", method = RequestMethod.GET)
    public ModelAndView events() {
        List<Event> allEvents = eventData.findAll();
        return new ModelAndView("allEvents", "events", allEvents);

    }






}
