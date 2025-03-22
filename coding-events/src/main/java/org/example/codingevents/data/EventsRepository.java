package org.example.codingevents.data;


import org.example.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Event = the object that we are storing
// Integer = primary key type
@Repository //it flags to springboot that this is a class that should be managed, springboot should create event repository instances and then inject them wherever our code ask for them
public interface EventsRepository extends CrudRepository<Event, Integer> {

}
