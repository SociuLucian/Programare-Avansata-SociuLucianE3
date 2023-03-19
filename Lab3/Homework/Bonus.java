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
    public boolean existRelation(Node node1,Node node) {
        boolean answer=false;
        if(node!=node1) {
            if (node1.getRelationships().containsKey(node)) {
                answer = true;
            } else if (node.getRelationships().containsKey(node1)) {
                answer = true;
            }
        }
        return answer;
    }
    public List<String> findArticulationPoints() {
        List<String> articulationPoints = new ArrayList<>();
        int n = nodes.size();
        boolean[] visited = new boolean[n];
        int[] depth = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];

        // initialize arrays
        Arrays.fill(visited, false);
        Arrays.fill(depth, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);

        // perform depth-first search from each node
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, visited, depth, low, parent, articulationPoints);
            }
        }

        return articulationPoints;
    }

    private void dfs(int u, boolean[] visited, int[] depth, int[] low, int[] parent, List<String> articulationPoints) {
        visited[u] = true;
        depth[u] = low[u] = 0;
        int children = 0;
        boolean isArticulation = false;

        for (int i = 0; i < nodes.size(); i++) {
            Node v = nodes.get(i);

            if (!visited[i] && existRelation(nodes.get(u), v)) {
                children++;
                parent[i] = u;
                dfs(i, visited, depth, low, parent, articulationPoints);

                low[u] = Math.min(low[u], low[i]);

                // check if u is an articulation point
                if (parent[u] == -1 && children > 1 || parent[u] != -1 && low[i] >= depth[u]) {
                    isArticulation = true;
                }
            } else if (parent[u] != i) {
                low[u] = Math.min(low[u], depth[i]);
            }
        }
        if (isArticulation) {
            articulationPoints.add(nodes.get(u).getName());
        }
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "nodes=" + nodes +
                '}';
    }
}
