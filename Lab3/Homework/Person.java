package Homework;
import java.time.LocalDate;
import java.util.*;
public class Person implements Node,Comparable<Person>{
    int id;
    private String name;
    private LocalDate birthDate;
    private Map<Node, String> relationships;

    public Person(String name, LocalDate birthDate,int id) {
        this.id=id;
        this.name = name;
        this.birthDate = birthDate;
        this.relationships = new HashMap<>();
    }

    public void addRelationship(Node node, String relationshipType) {
        relationships.put(node, relationshipType);
    }

    public Map<Node, String> getRelationships() {
        return relationships;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFavoriteFood() {
        return "Pizza";
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
    public int compareTo(Person other) {
        if(other.name==null)
            return 0;
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", relationships=" + relationships +
                '}';
    }
}


