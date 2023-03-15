package Homework;

import java.util.*;

public class Company implements Node,Comparable<Company>{
    String name;
    int id;
    Map<Person,String> employees;
    Map<Node,String> relationships;
    public Company(String name,int id) {
        this.name = name;
        this.id=id;
        this.relationships = new HashMap<>();
        this.employees = new HashMap<>();
    }
    public void addRelationship(Node node, String relationshipType) {
        relationships.put(node, relationshipType);
    }

    public Map<Node, String> getRelationships() {
        return relationships;
    }
    public void addEmployees(Person person)
    {
        employees.put(person, person.getName());
    }
    public Map<Person, String> getEmployees() {
        return employees;
    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Company other) {
        if(other.name==null)
            return 0;
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", employees=" + employees +
                ", relationships=" + relationships +
                '}';
    }
}
