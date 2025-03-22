package org.example.codingevents.data;

import org.example.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/// always prefer to use interface type
/// this class is encapsulating the behavior of storing event objects so that nobody else needs to know how they are stored
public class EventData {
    // need a place to put events
    private static final Map<Integer, Event> events = new HashMap<>();

    // get all events
    public static Collection<Event> getAll() {
        return events.values();
    }

    // get a single event
    public static Event getById(int id) {
        return events.get(id);
    }

    // add an event
    public static void add(Event event) {
        events.put(event.getId(), event);
    }

    // remove an event
    public static void remove(int id) {
        events.remove(id);
    }
}
