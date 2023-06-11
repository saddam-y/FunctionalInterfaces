# Functional Interfaces

Sometimes I will add some solutions related to functional interfaces to this project when something comes to my mind.


## Comparator

### Full-order relationship
Those two interfaces have to satisfy full-order relationship rules:
- Reflexivity
- Antisymmetry
- Transitive

I will not describe these rules, because it does not apply to this issue. You can read it on this website (https://www.programmersought.com/article/8537640425/)

To be precise, each comparator must match:
1. Comparator is consistent with to equals If compare(a,b) == 0 then a.equals(b)
2. sgn(compare(a,b)) == -sgn(compare(b,a))
3. compare(a,b) > 0 and compare(b,c) > 0 then compare(a,c) > 0
4. compare(a,b) == 0 then sgn(compare(a,c)) == sgn(compare(b,c))
5. If compare(a,b) throw exception then compare(b,a) too

* sgn - function that defines the sign 


## Comparable
Comparable also must matches the fields I described for Comparator.

I edited Comparator test class and expanded for testing Comparable.
