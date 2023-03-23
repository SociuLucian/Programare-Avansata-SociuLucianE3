package Homework;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import org.jgrapht.alg.matching.MaximumWeightBipartiteMatching;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.util.SupplierUtil;

import java.util.Set;

public class GraphMaximumMatching {

    public static <V, E> MatchingAlgorithm.Matching<V,E> edmondsKarp(Graph<V, E> graph, Set<V> partition1, Set<V> partition2) {

        // Create a new directed graph and add vertices for the source and sink
        Graph<V, DefaultWeightedEdge> directedGraph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        V source = graph.addVertex();
        V sink = graph.addVertex();

        // Add edges to the directed graph with weights of 1
        for (V vertex : graph.vertexSet()) {
            if (vertex.equals(source) || vertex.equals(sink)) {
                continue;
            }
            directedGraph.addVertex(vertex);
            for (E edge : graph.edgesOf(vertex)) {
                V opposite = Graphs.getOppositeVertex(graph, edge, vertex);
                if (!opposite.equals(source) && !opposite.equals(sink)) {
                    directedGraph.addVertex(opposite);
                    DefaultWeightedEdge directedEdge = directedGraph.addEdge(vertex, opposite);
                    directedGraph.setEdgeWeight(directedEdge, 1.0);
                }
            }
        }


        MaximumFlowAlgorithm<V, DefaultWeightedEdge> maximumFlow = new EdmondsKarpMFImpl<>(directedGraph);
        MaximumFlowAlgorithm.MaximumFlow<DefaultWeightedEdge> flow = maximumFlow.getMaximumFlow(source, sink);

        MatchingAlgorithm.Matching<V,E> matching;
        matching = new MaximumWeightBipartiteMatching<>(graph,partition1, partition2).getMatching();
        for (V vertex : graph.vertexSet()) {
            if (vertex.equals(source) || vertex.equals(sink)) {
                continue;
            }
            for (E edge : graph.edgesOf(vertex)) {
                V opposite = Graphs.getOppositeVertex(graph, edge, vertex);

                if (opposite.equals(source) || opposite.equals(sink)) {
                    continue;
                }
                if (flow.getFlow((DefaultWeightedEdge) edge) == 1.0 && !matching.getEdges().contains(edge)) {
                    matching.getEdges().add(edge);
                }
            }
        }

        // Remove the source and sink vertices from the original graph
        graph.removeVertex(source);
        graph.removeVertex(sink);

        return matching;
    }
}
