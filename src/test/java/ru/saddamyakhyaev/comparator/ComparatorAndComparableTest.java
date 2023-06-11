package ru.saddamyakhyaev.comparator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ru.saddamyakhyaev.comparator.dto.PersonGetter;
import ru.saddamyakhyaev.dto.Person;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Tests for Comparator and Comparable")
class ComparatorAndComparableTest {


    @ParameterizedTest
    @MethodSource("getPersonComparator")
    @DisplayName("Comparator is consistent with to equals If compare(a,b) == 0 then a.equals(b)")
    <T> void testPersonAgeComparatorEquivalence(Comparator<T> personComparator, List<T> elements) {

        for(var a: elements) {
            for(var b: elements) {
                if(personComparator.compare(a, b) == 0) {
                    assertTrue(a.equals(b));
                }
            }
        }
    }

    @ParameterizedTest
    @MethodSource("getPersonComparator")
    @DisplayName("sgn(compare(a,b)) == -sgn(compare(b,a))")
    <T> void testPersonAgeComparatorChangeArgumentOrder(Comparator<T> personComparator, List<T> elements) {

        for(var a: elements) {
            for(var b: elements) {
                assertTrue(sgn(personComparator.compare(a, b)) == -sgn(personComparator.compare(b, a)));
            }
        }
    }

    @ParameterizedTest
    @MethodSource("getPersonComparator")
    @DisplayName("Transitive:  compare(a,b) > 0 and compare(b,c) > 0 then compare(a,c) > 0")
    <T> void testPersonAgeComparatorTransitive(Comparator<T> personComparator, List<T> elements) {

        for(var a: elements) {
            for(var b: elements) {
                var resultComparatorAB = sgn(personComparator.compare(a, b));
                if(resultComparatorAB != 0) {
                    for(var c: elements) {
                        var resultComparatorBC = sgn(personComparator.compare(b, c));
                        if(resultComparatorAB == resultComparatorBC) {
                            assertTrue(sgn(personComparator.compare(a, c)) == resultComparatorAB);
                        }
                    }
                }
            }
        }
    }

    @ParameterizedTest
    @MethodSource("getPersonComparator")
    @DisplayName("compare(a,b) == 0 then sgn(compare(a,c)) == sgn(compare(b,c))")
    <T> void testPersonAgeComparatorResultEqualsForOtherElements(Comparator<T> personComparator, List<T> elements) {
        for(var a: elements) {
            for(var b: elements) {
                var resultComparatorAB = sgn(personComparator.compare(a, b));
                if(resultComparatorAB == 0) {
                    for(var c: elements) {
                        var resultComparatorAC = sgn(personComparator.compare(b, c));
                        var resultComparatorBC = sgn(personComparator.compare(b, c));
                        assertTrue(resultComparatorAC == resultComparatorBC);
                    }
                }
            }
        }
    }

    @ParameterizedTest
    @MethodSource("getPersonComparator")
    @DisplayName("If compare(a,b) throw exception then compare(b,a) too")
    <T> void testPersonAgeComparatorChangeArgumentOrderThrowExceptionToo(Comparator<T> personComparator, List<T> elements) {

        elements.add(null);
        for(var a: elements) {
            for(var b: elements) {
                try {
                    personComparator.compare(a, b);
                }catch (NullPointerException ex){
                    assertThrows(ex.getClass(), () -> {
                        personComparator.compare(b, a);
                    });
                }
            }
        }
    }

    public static Stream<Arguments> getPersonComparator() {
        return Stream.of(
                Arguments.of(PersonComparator.getPersonAgeComparator(), PersonGetter.list()),
                Arguments.of(PersonComparator.getPersonNameComparator(), PersonGetter.list()),
                Arguments.of(Comparator.comparing((Person person) -> person), PersonGetter.list())
        );
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
