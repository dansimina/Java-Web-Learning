package org.example.codingevents.controllers;

import jakarta.validation.Valid;
import org.example.codingevents.data.EventData;
import org.example.codingevents.data.EventsRepository;
import org.example.codingevents.models.Event;
import org.example.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("events")
public class EventController {

    //specifies that springboot should autopopulate this field, we don't make controllers or get or set because we want spring to manage this class for us
    //dependency injection
    @Autowired //specifies that springboot should autopopulate this field, we don't make controllers or get or set because we want spring to manage this class for us
    private EventsRepository eventsRepository;

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    //lives at /events
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event());
        model.addAttribute("types", EventType.values());
        return "events/create";
    }

    // lives at /events/create
//    @PostMapping("create")
//    public String processCreateEventForm(@RequestParam String eventName, @RequestParam String eventDescription) { //need to use the same name as the one of the field in the HTML (create.html)
//        EventData.add(new Event(eventName, eventDescription));
//        return "redirect:/events"; // return a redirect response which is a 300 level HTTP response and instructs the browser to go to a different page
//    }

    // Model binding: the event is created using reflection, and the names in the HTML must match the property names in the object.
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event event, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("errorMsg", "Bad Data");
            return "events/create";
        }

        EventData.add(event);
        return "redirect:/events"; // return a redirect response which is a 300 level HTTP response and instructs the browser to go to a different page
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if(eventIds != null) {
            for (int eventId : eventIds) {
                EventData.remove(eventId);
            }
        }

        return "redirect:/events";
    }
}
