package Homework;

import java.util.Objects;

public class Project implements Comparable<Project>
{
    private String name;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name);
    }


    @Override
    public int compareTo(Project other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                '}';
    }
}

