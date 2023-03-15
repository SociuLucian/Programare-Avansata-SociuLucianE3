package Compulsory;
import java.util.HashMap;
import java.util.Map;
public class Company implements Node,Comparable<Company>{
    private String name;
    private Map<Person,String> employees;
    private int Id;
    public Company(String name) {
        this.name = name;
        employees = new HashMap<Person, String>();
    }
    public void addEmployee(Person employee, String position) {
        employees.put(employee, position);
    }

    public Map<Person, String> getEmployees() {
        return employees;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public int compareTo(Company other) {
        if(other.name==null)
            return 0;
        return this.name.compareTo(other.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(Map<Person, String> employees) {
        this.employees = employees;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                ", Id=" + Id +
                '}';
    }
}
