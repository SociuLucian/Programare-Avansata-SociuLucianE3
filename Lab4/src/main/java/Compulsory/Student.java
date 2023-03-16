package Compulsory;

import java.util.*;

public class Student implements Comparable<Student>{
    private String name;
    private Set<Project> admissibleProjects;

    public Student(String name, Set<Project> admissibleProjects) {
        this.name = name;
        this.admissibleProjects = admissibleProjects;
    }

    public String getName() {
        return name;
    }

    public Set<Project> getAdmissibleProjects() {
        return admissibleProjects;
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }
}
