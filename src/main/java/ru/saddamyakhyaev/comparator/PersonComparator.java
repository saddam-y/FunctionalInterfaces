package ru.saddamyakhyaev.comparator;

import ru.saddamyakhyaev.dto.Person;

import java.util.Comparator;

public class PersonComparator {

    public static Comparator<Person> getPersonAgeComparator() {

        return (p1, p2) -> {

            if (p1 == null || p2 == null) throw new NullPointerException();

            if (p1.getAge() > p2.getAge()) {
                return 1;
            }
            if (p1.getAge() < p2.getAge()) {
                return -1;
            }

            return p1.getName().compareTo(p2.getName());
        };
    }

    public static Comparator<Person> getPersonNameComparator() {

        return (p1, p2) -> {

            if (p1 == null || p2 == null) throw new NullPointerException();

            if(p1.getName().compareTo(p2.getName()) != 0) {
                return p1.getName().compareTo(p2.getName());
            }

            return Integer.compare(p1.getAge(), p2.getAge());
        };
    }
}
