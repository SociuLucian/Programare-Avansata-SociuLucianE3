package homework;


import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class Problem {
    private LinkedList<Student> students;
    private TreeSet<Project> projects;

    public Problem(LinkedList<Student> students, Set<Project> projects) {
        this.students = new LinkedList<>(students);
        this.projects = new TreeSet<>(projects);
    }

    public TreeSet<Project> getProjects() {
        return projects;
    }

    public void setProjects(TreeSet<Project> projects) {
        this.projects = projects;
    }

    public LinkedList<Student> getStudents() {
        return students;
    }

    public void setStudents(LinkedList<Student> students) {
        this.students = students;
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

