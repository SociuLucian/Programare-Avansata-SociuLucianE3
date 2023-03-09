package Compulsory;
import java.util.*;
public class Main {
    public static void main(String args[]){
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(new Person("Ana"));
        nodes.add(new Person("Bob"));
        nodes.add(new Company("Auchan"));
        for(Node node : nodes)
        {
            System.out.println(node);
        }
    }
}
