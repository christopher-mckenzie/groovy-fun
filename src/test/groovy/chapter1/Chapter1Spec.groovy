package chapter1

import spock.lang.Specification

/**
 * Created by cmcke on 2/12/2017.
 */
class Chapter1Spec extends Specification{

    def "POGO test 1: population"(){
        given:
        POGO pogo = new POGO()

        when:
        //parentheses are optional
        pogo.setName 'test 1'
        pogo.setPriority 0
        pogo.setStartDate Calendar.instance.getTime()
        pogo.setEndDate Calendar.instance.getTime()
        //or pass map to constructor
        //new POGO(name:'test 1', priority: 0, startDate: Calendar.instance.getTime(), endDate: Calendar.instance.getTime())
        then:
        println pogo.toString()

    }

    def "Sort sample"(){
        given:
        def strings = ['this', 'is', 'a', 'list', 'of', 'strings']

        when:
        Collections.sort strings, {s1,s2 -> s2.size() - s1.size()} as Comparator
        //------------------OR------------------
        //strings.sort { -it?.size() }

        then:
        assert strings*.size() == [7, 4, 4, 2, 2, 1]
        println 'Sort sample success' + strings*.size()

    }
}

