package org.example.codingevents.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

@MappedSuperclass // it says that the fields in this class should be pushed down into the tables for the objects that extends it
public abstract class AbstractEntity {
    @Id
    @GeneratedValue
    private int id;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity abstractEntity = (AbstractEntity) o;
        return id == abstractEntity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
