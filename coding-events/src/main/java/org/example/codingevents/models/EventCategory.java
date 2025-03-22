package org.example.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity
public class EventCategory extends AbstractEntity {

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;

    //foreign key
    @OneToMany(mappedBy = "category") //mappedBy value has to be the name of the field in the class we are storing here that creates this relationship
    private final List<Event> events = new ArrayList<>();

    public EventCategory(String name) {
        this.name = name;
    }

    public EventCategory() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString(){
        return name;
    }
}