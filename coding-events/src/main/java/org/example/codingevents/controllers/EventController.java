package org.example.codingevents.controllers;

import org.example.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {
    private static List<Event> events = new ArrayList<>();

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("events", events);
        return "events/index";
    }

    //lives at /events
    @GetMapping("create")
    public String renderCreateEvent() {
        return "events/create";
    }

    // lives at /events/create
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName, @RequestParam String eventDescription) { //need to use the same name as the one of the field in the HTML (create.html)
        events.add(new Event(eventName, eventDescription));
        return "redirect:/events"; // return a redirect response which is a 300 level HTTP response and instructs the browser to go to a different page
    }
}
