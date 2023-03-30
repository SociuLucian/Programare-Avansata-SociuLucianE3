package Homework;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.DepthFirstIterator;


import java.util.HashSet;
import java.util.Map;
import java.util.*;


public class Main {
    public static void main(String args[]) {
        /*Set<Student> students = new HashSet<>();
        students.add(new Student("S1", Set.of(new Project("P0"), new Project("P1"))));
        students.add(new Student("S0", Set.of(new Project("P0"), new Project("P1"), new Project("P2"))));
        students.add(new Student("S2", Set.of(new Project("P0"))));

        Set<Project> projects = new HashSet<>();
        projects.add(new Project("P1"));
        projects.add(new Project("P0"));
        projects.add(new Project("P3"));
        projects.add(new Project("P2"));*/

        //matching.createMatching();
        /*Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(faker.name().fullName(),Set.of(new Project(faker.app().name()),
                    new Project(faker.app().name()),new Project(faker.app().name()))));
        }
        for (int i = 0; i < 10; i++) {
            projects.add(new Project(faker.app().name()));
        }*/
        //Problem problem= new Problem(students, projects);
        ProblemGenerator generator = new ProblemGenerator();
        Problem problem = generator.Generate();
        //System.out.println("Sorted list of students:");
        //problem.printStudents();

        //System.out.println("\nSorted list of projects:");
        //problem.printProjects();
        LinkedList<Student> students = problem.getStudents();
        int nr_pref = 0;
        double avg;
        for (Student student : students) {
            nr_pref += student.getAdmissibleProjects().size();
        }
        avg = nr_pref / students.size();
        /*students.stream()
                .filter(student -> student.getAdmissibleProjects().size()<avg)
                .forEach(student -> System.out.println("\n" + "Students with preferences under average "+student.getName()));*/
        Solver solver = new Solver(problem);
        long startTimeGreedy = System.nanoTime();
        Map<Student, Set<Project>> result = solver.createMatching();
        long endTimeGreedy = System.nanoTime();
        long runTimeGreedy = endTimeGreedy - startTimeGreedy;
        long memoryGreedy = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        //System.out.println(result);
        System.out.println(result.keySet().size());
        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        for (Student student : students) {
            g.addVertex(student.getName());
        }
        for (Project project1 : problem.getProjects()) {
            g.addVertex(project1.getName());
        }
        for (Student student : students) {
            for (Project project : student.getAdmissibleProjects()) {
                g.addEdge(student.getName(), project.getName());
            }
        }
        /*Iterator<String> iter = new DepthFirstIterator<>(g);
        while (iter.hasNext()) {
            String vertex = iter.next();
            System.out
                    .println(
                            "Vertex " + vertex + " is connected to: "
                                    + g.edgesOf(vertex).toString());
        }*/
        Bonus matching = new Bonus();
        long startTimeEdmonds = System.nanoTime();
        matching.edmondsKarp(g);
        long endTimeEdmonds = System.nanoTime();
        long runTimeEdmonds = endTimeEdmonds - startTimeEdmonds;
        long memoryEdmonds = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Runtime for Greedy Algorithm : " + runTimeGreedy + " nanoseconds");
        System.out.println("Memory usage for Greedy Algorithm " + memoryGreedy);
        System.out.println("Runtime for Edmonds Algorithm " + runTimeEdmonds + " nanoseconds");
        System.out.println("Memory usage for Edmonds Algorithm " + memoryEdmonds);
        System.out.println(matching.getMatching());
        System.out.println(matching.getMatching().getEdges().size());
        //Minimum set
        Set<String> minimumSet = new HashSet<>();

        System.out.println(minimumSet);

    }
}

