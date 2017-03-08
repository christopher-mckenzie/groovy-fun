package chapter4.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by req85404 on 03/08/2017.
 */
public class Department {
    private int id;
    private String name;
    private Map<Integer, Employee> empMap = new HashMap<Integer, Employee>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Employee> getEmployees() {
        return empMap.values();
    }

    public void hire(Employee e){
        empMap.put(e.getId(), e);
    }
    public void layOff(Employee e){
        empMap.remove(e.getId());
    }

    //Overriding operator methods (plus)
    public Department plus(Employee e){
        hire(e);
        return this;
    }

    //Overriding operator methods (minus)
    public Department minus(Employee e){
        layOff(e);
        return this;
    }

    //Overriding operator methods (leftShift)
    public Department leftShift(Employee e){
        hire(e);
        return this;
    }
}
