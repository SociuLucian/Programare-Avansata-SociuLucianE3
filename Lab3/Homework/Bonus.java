package Homework;
import java.util.*;

public class Bonus {
    private List<Node> nodes;

    public Bonus(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Map<Node,Set<Node>> ComputeRelations(){
        Map<Node,Set<Node>> relationships = new HashMap<>();
        for(Node node : nodes) {
            Set<Node> con =new HashSet<>();
            con.addAll(node.getRelationships().keySet());
            relationships.put(node, con);
        }
        for(Node node : relationships.keySet())
        {
            for (Node node1 : relationships.get(node)){
                relationships.get(node1).add(node);
            }
        }
        return relationships;
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "nodes=" + nodes +
                '}';
    }
}
