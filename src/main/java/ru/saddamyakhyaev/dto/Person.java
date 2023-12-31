package ru.saddamyakhyaev.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class Person implements Comparable<Person> {
    private final String name;
    private final int age;


    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Person o) {
        if(o == null) throw new NullPointerException();

        if(getAge() > o.getAge()) return 1;
        if(getAge() < o.getAge()) return -1;

        return getName().compareTo(o.getName());
    }
}
