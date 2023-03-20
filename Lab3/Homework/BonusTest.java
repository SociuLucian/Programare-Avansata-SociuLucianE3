package Homework;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BonusTest {

    @Test
    void findDisconnectingPoints() {
        Person Marcel = new Programmer("Marcel", LocalDate.of(1990, 6, 30), "Java", 1);
        Person Maria = new Programmer("Maria", LocalDate.of(1995, 3, 15), "Python", 2);
        Person Ana = new Designer("Ana", LocalDate.of(1992, 10, 1), "Figma", 3);
        Company Profi = new Company("Profi", 10);
        Marcel.addRelationship(Maria, "friend");
        Marcel.addRelationship(Ana, "colleague");
        Maria.addRelationship(Ana, "boss");
        Ana.addRelationship(Profi, "employee");

        Network net = new Network();
        net.addNode(Maria);
        net.addNode(Marcel);
        net.addNode(Ana);
        net.addNode(Profi);
        Bonus bonus =new Bonus(net.getNodes());
        List<String> disconnections = bonus.findDisconnectingPoints();
        disconnections.remove(1);
        assertLinesMatch(List.of("Ana"),disconnections);
    }
}