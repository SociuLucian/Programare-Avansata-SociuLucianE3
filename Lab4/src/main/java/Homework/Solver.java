package Homework;

import java.util.*;

public class Solver {
    private Problem problem ;

    public Solver(Problem problem ) {
        this.problem=problem;
    }
    public Map<Student,Set<Project>> createMatching() {
        Set<Project> availableProjects = problem.getProjects();
        availableProjects.stream().sorted();
        //LinkedList<Student> students =problem.getStudents();
        //students.stream().sorted();
        Map<Student,Set<Project>> matching = new HashMap<>();
        for(Student student : problem.getStudents())
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
