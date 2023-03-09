package Compulsory;

import java.util.ArrayList;
import java.util.List;

public class Person implements Node,Comparable<Person>{
    private String name;
    private List<Person> friends;
    private int Id;

    public Person(String name) {
        this.name=name;
    }

    public List<Person> getFriends() {
        return friends;
    }

   public void addFriend(Person friend)
   {
       if(friend.name!=null)
       friends.add(friend);
   }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return Id;
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
                "name='" + name + '\'' +
                ", friends=" + friends +
                ", Id=" + Id +
                '}';
    }
}
