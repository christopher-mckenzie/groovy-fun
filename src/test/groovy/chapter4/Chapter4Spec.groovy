package chapter4

import chapter4.bean.Department
import chapter4.bean.Employee
import spock.lang.Specification

/**
 * Created by req85404 on 03/08/2017.
 */
class Chapter4Spec extends Specification{
    private Department department;

    def setup(){
        //can use groovy map constructor even with java class
        department = new Department(name:'IT')
    }
    def "add employee to dept should increase total by 1"(){
        given:
            Employee fred = new Employee(name:'Fred', id:1)
        when:
            //+ can be used to add an object to a list contained with object
            department = department + fred
        then:
            //uses old to compare where department was before add
            department.employees.size() == old(department.employees.size()) + 1
            println 'added employee via +'
    }

    def "add two employees via chained plus"(){
        given:
            Employee fred = new Employee(name:'Fred',id:1)
            Employee barney = new Employee(name:'Barney',id:2)
        when:
            //add to employees to department employee map
            department = department + fred + barney
        then:
            department.employees.size() == 2
            println 'added 2 employees via chained +'
    }

    def "subtract employee from department should decrease by 1"(){
        given:
            Employee fred = new Employee(name:'Fred',id:1)
            department.hire fred
        when:
            //remove employee from department - uses minus operation in department
            department = department - fred
        then:
            department.employees.size() == old(department.employees.size()) - 1
            println 'removed employee via -'
    }

    def "remove two employees via chained minus"(){
        given:
            Employee fred = new Employee(name:'Fred',id:1)
            Employee barney = new Employee(name:'Barney',id:2)
            department.hire fred; department.hire barney
        when:
            //remove employee from department - uses minus operation in department - chained -
            department = department - fred - barney
        then:
            department.employees.size() == 0
            println 'removed 2 employees via chained -'
    }

    def "left shift should increase employee total by 1"(){
        given:
            Employee fred = new Employee(name:'Fred',id:1)
        when:
            //add employee via left shift uses department's leftShift method
            department = department << fred
        then:
            department.employees.size() == old(department.employees.size()) + 1
            println 'added employee via <<'
    }

    def "add two employees via chained left shift"(){
        given:
            Employee fred = new Employee(name:'Fred',id:1)
            Employee barney = new Employee(name:'Barney',id:2)
        when:
            //add two employees through chained left shift
            department = department << fred << barney
        then:
            department.employees.size() == 2
            println 'added 2 employees via chained <<'
    }
}
