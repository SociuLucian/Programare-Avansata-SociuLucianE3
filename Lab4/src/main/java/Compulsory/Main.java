package Compulsory;
import java.util.*;
public class Main {
    public static void main (String args[])
    {
        Set<Student> students = new HashSet<>();
        students.add(new Student("S1", Set.of(new Project("P0"), new Project("P1"))));
        students.add(new Student("S0", Set.of(new Project("P0"), new Project("P1"), new Project("P2"))));
        students.add(new Student("S2", Set.of(new Project("P0"))));

        Set<Project> projects = new HashSet<>();
        projects.add(new Project("P1"));
        projects.add(new Project("P0"));
        projects.add(new Project("P3"));
        projects.add(new Project("P2"));

        Matching matching = new Matching(students, projects);
        //matching.createMatching();

        System.out.println("Sorted list of students:");
        matching.printStudents();

        System.out.println("\nSorted list of projects:");
        matching.printProjects();
    }
}
