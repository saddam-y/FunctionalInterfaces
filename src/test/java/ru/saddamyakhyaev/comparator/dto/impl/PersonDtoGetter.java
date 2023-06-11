package ru.saddamyakhyaev.comparator.dto.impl;

import ru.saddamyakhyaev.comparator.dto.DtoGetter;
import ru.saddamyakhyaev.dto.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDtoGetter implements DtoGetter<Person> {

    @Override
    public List<Person> list() {
        return new ArrayList<>(List.of(new Person[] {
                new Person("Alex", 20),
                new Person("Max", 25),
                new Person("Maria", 18),
                new Person("Gleb", 25),
        }));
    }

    @Override
    public List<Person> listWithNullElements() {
        List<Person> persons = list();
        persons.add(1, null);
        persons.add(4, null);

        return list();
    }
}
