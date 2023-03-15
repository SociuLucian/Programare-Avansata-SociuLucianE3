package Homework;
import java.util.*;
public class Network {
    private List<Node> nodes;

    public Network() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }
    public int computeImportance(Node node) {
        int importance = 0;
        for (Node otherNode : nodes) {
            if (otherNode != node) {
                Map<Node, String> relationships = otherNode.getRelationships();
                if (relationships.containsKey(node)) {
                    importance++;
                }
                }
            }
        for(Node node2: node.getRelationships().keySet())
        {
            importance ++;
        }
        return importance;
    }

    @Override
    public String toString() {
        return "Network{" +
                "nodes=" + nodes +
                '}';
    }
}
