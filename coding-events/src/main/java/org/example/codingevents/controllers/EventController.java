package org.example.codingevents.controllers;

import jakarta.validation.Valid;
import org.example.codingevents.data.EventCategoryRepository;
import org.example.codingevents.data.EventsRepository;
import org.example.codingevents.data.TagRepository;
import org.example.codingevents.models.Event;
import org.example.codingevents.models.EventCategory;
import org.example.codingevents.models.Tag;
import org.example.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping({"events", "events/"})
public class EventController {

    //specifies that springboot should autopopulate this field, we don't make controllers or get or set because we want spring to manage this class for us
    //dependency injection
    @Autowired //specifies that springboot should autopopulate this field, we don't make controllers or get or set because we want spring to manage this class for us
    private EventsRepository eventsRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model) {

        if(categoryId == null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventsRepository.findAll());
        }
        else {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if(result.isEmpty()) {
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            }
            else {
                EventCategory category = result.get();
                model.addAttribute("title", "Event Category: " + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }

        return "events/index";
    }

    //lives at /events
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
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


        eventsRepository.save(event);
        return "redirect:/events"; // return a redirect response which is a 300 level HTTP response and instructs the browser to go to a different page
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventsRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if(eventIds != null) {
            for (int eventId : eventIds) {
                eventsRepository.deleteById(eventId);
            }
        }

        return "redirect:/events";
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {

        Optional<Event> result = eventsRepository.findById(eventId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
            model.addAttribute("tags", event.getTags());
        }

        return "events/detail";
    }

    //response to /events/add-tag?eventId=13
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model){
        Optional<Event> result = eventsRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to: " + event.getName());
        model.addAttribute("tags", tagRepository.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag", eventTag);
        return "events/add-tag.html";
    }


    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag,
                                    Errors errors,
                                    Model model){
        if(!errors.hasErrors()){
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if(!event.getTags().contains(tag)){
                event.addTag(tag);
                eventsRepository.save(event);
            }
            return "redirect:detail?eventId=" + event.getId();
        }
        return "redirect:add-tag";
    }
}
