package Homework;
import java.time.LocalDate;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Person Marcel = new Programmer("Marcel", LocalDate.of(1990, 6, 30), "Java", 1);
        Person Maria = new Programmer("Maria", LocalDate.of(1995, 3, 15), "Python", 2);
        Person Ana = new Designer("Ana", LocalDate.of(1992, 10, 1), "Figma", 3);
        Company Profi = new Company("Profi", 10);
        Marcel.addRelationship(Maria, "friend");
        Marcel.addRelationship(Ana, "colleague");
        Maria.addRelationship(Ana, "boss");
        Ana.addRelationship(Profi, "employee");

        Network network = new Network();
        network.addNode(Maria);
        network.addNode(Marcel);
        network.addNode(Ana);
        network.addNode(Profi);

        // Print network, ordered by importance
        List<Node> nodes = network.getNodes();
        nodes.sort((n1, n2) -> network.computeImportance(n2) - network.computeImportance(n1));
        for (Node node : nodes) {
            System.out.println(node + " - importance: " + network.computeImportance(node));
        }
    }
    }

