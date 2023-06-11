package ru.saddamyakhyaev.comparator;

import ru.saddamyakhyaev.dto.Person;

import java.util.Comparator;

public interface ComparatorFactory {
    Comparator<Person> getPersonAgeComparator();
}
