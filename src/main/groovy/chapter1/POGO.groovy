package chapter1

import groovy.transform.EqualsAndHashCode

/**
 * Created by cmcke on 2/12/2017.
 * Plain old groovy obect.
 * @EqualsAndHashCode - generate override methods for equals  and hash code
 * Auto generates getters and setters for attributes. Attributes themselves are  private
 */
@EqualsAndHashCode
class POGO {
    String name
    int priority
    Date startDate
    Date endDate;

    String toString() { "($name, $priority, $startDate, $endDate)" }
}
