import java.util.*;
import java.util.Comparator;

public class Network {
    private static List<Node> nodes= new ArrayList<>();
    private static Map<Node, Integer> cost;
    private List <Node> IdentifiableNodes= new ArrayList<>();

    public Network(){
       nodes = new ArrayList<>();
       cost = new HashMap<>();
    }
    public void setNodes(List nodes1)
    {
        this.nodes=nodes1;
    }
    public List<Node> getListNodes() {
       return nodes;
    }
    public void addNode(Node node) {
        nodes.add(node);
    }
    public void setCost(Node node, int value){cost.put(node,value);}
    public void Display()
    {
        for(Node nodelist : nodes)
        {
            if(nodelist instanceof Identifiable )
            {
                IdentifiableNodes.add(nodelist);
            }
        }
        IdentifiableNodes.sort((Comparator.<Node>naturalOrder()));
        System.out.println("The identifiable nodes are: ");
        for(Node nodes : IdentifiableNodes)
        {
            System.out.print(nodes + " ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Network{" +
                "nodes=" + nodes +
                " \n Cost " + cost +
                "\n }";
    }
}
