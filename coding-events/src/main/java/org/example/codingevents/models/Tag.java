package org.example.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends  AbstractEntity{

    @Size(min = 1, max = 25)
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Event> events = new ArrayList<>();

    public Tag(){}

    public String getName() {
        return name;
    }

    public String getDisplayName() {return "#" + name + " ";}

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }
}