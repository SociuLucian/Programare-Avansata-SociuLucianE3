package Compulsory;

import java.util.*;

public class Matching {
    private LinkedList<Student> students;
    private TreeSet<Project> projects;

    public Matching(Set<Student> students, Set<Project> projects) {
        this.students = new LinkedList<>(students);
        this.projects = new TreeSet<>(projects);
    }

    public void createMatching() {
        for (Student student : students) {
            for (Project project : student.getAdmissibleProjects()) {
                if (projects.contains(project)) {
                    projects.remove(project);
                    break;
                }
            }
        }
    }
    public void printStudents() {
        students.stream()
                .sorted()
                .forEach(student -> System.out.println(student.getName()));
    }

    public void printProjects() {
        projects.stream()
                .forEach(project -> System.out.println(project.getName()));
    }
}

