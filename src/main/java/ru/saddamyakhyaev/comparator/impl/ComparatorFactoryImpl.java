package ru.saddamyakhyaev.comparator.impl;

import ru.saddamyakhyaev.comparator.ComparatorFactory;
import ru.saddamyakhyaev.dto.Person;

import java.util.Comparator;

public class ComparatorFactoryImpl implements ComparatorFactory {

    @Override
    public Comparator<Person> getPersonAgeComparator() {

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

}
