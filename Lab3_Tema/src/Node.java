import java.util.Map;
import java.util.HashMap;
public abstract class Node implements java.lang.Comparable<Node> {
    private Map<Node, Integer> cost = new HashMap<>();
    public String nume;

    public Node(String nume) {
        this.nume=nume;
    }

    public void setName(String nume){
        this.nume = nume;
    }

    public String getName(){
        return nume;
    }



    //@Override
    public int compareTo(Node other) {
        return this.nume.compareTo(other.nume);
        //not safe, check if name is null before
    }
}
