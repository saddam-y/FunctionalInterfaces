package ru.saddamyakhyaev.comparator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.saddamyakhyaev.comparator.dto.DtoGetter;
import ru.saddamyakhyaev.comparator.dto.impl.PersonDtoGetter;
import ru.saddamyakhyaev.comparator.impl.ComparatorFactoryImpl;
import ru.saddamyakhyaev.dto.Person;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComparatorFactoryTest {

    private final DtoGetter<Person> personsGetter = new PersonDtoGetter();
    private final ComparatorFactory comparatorFactory = new ComparatorFactoryImpl();

    @Test
    @DisplayName("Comparator is consistent with to equals If compare(a,b) == 0 then a.equals(b)")
    public void testPersonAgeComparatorEquivalence() {
        var persons = personsGetter.list();
        var personComparator = comparatorFactory.getPersonAgeComparator();

        for(var personA: persons) {
            for(var personB: persons) {
                if(personComparator.compare(personA, personB) == 0) {
                    assertTrue(personA.equals(personB));
                }
            }
        }
    }

    @Test
    @DisplayName("sgn(compare(a,b)) == -sgn(compare(b,a))")
    void testPersonAgeComparatorChangeArgumentOrder() {
        var persons = personsGetter.list();
        var personComparator = comparatorFactory.getPersonAgeComparator();

        for(var personA: persons) {
            for(var personB: persons) {
                assertTrue(sgn(personComparator.compare(personA, personB)) == -sgn(personComparator.compare(personB, personA)));
            }
        }
    }

    @Test
    @DisplayName("Transitive:  compare(a,b) > 0 and compare(b,c) > 0 then compare(a,c) > 0")
    void testPersonAgeComparatorTransitive() {
        var persons = personsGetter.list();
        var personComparator = comparatorFactory.getPersonAgeComparator();

        for(var personA: persons) {
            for(var personB: persons) {
                var resultComparatorAB = sgn(personComparator.compare(personA, personB));
                if(resultComparatorAB != 0) {
                    for(var personC: persons) {
                        var resultComparatorBC = sgn(personComparator.compare(personB, personC));
                        if(resultComparatorAB == resultComparatorBC) {
                            assertTrue(personComparator.compare(personA, personC) == resultComparatorAB);
                        }
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("compare(a,b) == 0 then sgn(compare(a,c)) == sgn(compare(b,c))")
    void testPersonAgeComparatorResultEqualsForOtherElements() {
        var persons = personsGetter.list();
        var personComparator = comparatorFactory.getPersonAgeComparator();

        for(var personA: persons) {
            for(var personB: persons) {
                var resultComparatorAB = sgn(personComparator.compare(personA, personB));
                if(resultComparatorAB == 0) {
                    for(var personC: persons) {
                        var resultComparatorAC = sgn(personComparator.compare(personB, personC));
                        var resultComparatorBC = sgn(personComparator.compare(personB, personC));
                        assertTrue(resultComparatorAC == resultComparatorBC);
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("If compare(a,b) throw exception then compare(b,a) too")
    void testPersonAgeComparatorChangeArgumentOrderThrowExceptionToo() {
        var persons = personsGetter.listWithNullElements();
        var personComparator = comparatorFactory.getPersonAgeComparator();

        for(var personA: persons) {
            for(var personB: persons) {
                try {
                    personComparator.compare(personA, personB);
                }catch (NullPointerException ex){
                    assertThrows(ex.getClass(), () -> {
                        personComparator.compare(personB, personA);
                    });
                }
            }
        }
    }


    public int sgn(int i) {
        if(i == 0) {
            return 0;
        }
        if(i >= 1){
            return 1;
        }else {
            return -1;
        }
    }
}
