package homework;

import java.util.HashSet;
import java.util.*;

import static java.util.Set.of;

public class ProblemGenerator {
    public Problem Generate()
    {
        LinkedList<Student> students = new LinkedList<>();
        TreeSet<Project> projects = new TreeSet<>();
        Random random = new Random();
        int nrStudents = random.nextInt(5,10);
        int nrProjects = random.nextInt(5,10);
        for (int i = 0; i < nrProjects; i++) {
            projects.add(new Project("P" + i));
        }
        for (int i = 0; i < nrStudents; i++) {
            int nrOfAdmissibleProjects = random.nextInt(5);
            Set<Project> admissibleProjects = new HashSet<>();
            int j= 0;
            while(j<nrOfAdmissibleProjects)
            {
                for (Project project : projects)
                {
                    boolean let = random.nextBoolean();
                    if(let)
                    {
                        admissibleProjects.add(project);
                        j++;
                    }
                }
            }
            students.add(new Student("S" + i,admissibleProjects));
        }
        Problem problem = new Problem(students,projects);
        return problem;
    }
}
