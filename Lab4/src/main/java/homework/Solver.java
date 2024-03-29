package homework;

import java.util.*;

public class Solver {
    private Problem problem ;

    public Solver(Problem problem ) {
        this.problem=problem;
    }
    public Map<Student,Set<Project>> createMatching() {
        Set<Project> availableProjects = new HashSet<>();
        for(Project project : problem.getProjects())
        {
            availableProjects.add(project);
        }
        availableProjects.stream().sorted();
        List<Student> students =problem.getStudents();
        students.sort(Comparator.comparing(student -> student.getAdmissibleProjects().size()));
        Map<Student,Set<Project>> matching = new HashMap<>();
        for(Student student : students)
        {
            for(Project project : student.getAdmissibleProjects())
            {
                if(availableProjects.contains(project))
                {
                    Set<Project> helper = new HashSet<>();
                    helper.add(project);
                    matching.put(student,helper);
                    availableProjects.remove(project);
                    break;
                }
            }
        }
        return matching;
    }
}
