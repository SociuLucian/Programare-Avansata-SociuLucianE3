package Homework;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.DenseEdmondsMaximumCardinalityMatching;
import org.jgrapht.graph.DefaultEdge;

import java.util.Iterator;
import java.util.Set;

public class Bonus implements MatchingAlgorithm, MatchingAlgorithm.Matching {
    private Matching matching;


    public void edmondsKarp(Graph<String, DefaultEdge> graph) {
        matching = new DenseEdmondsMaximumCardinalityMatching<>(graph).getMatching();
    }

    @Override
    public boolean isMatched(Object o) {
        return Matching.super.isMatched(o);
    }

    @Override
    public boolean isPerfect() {
        return Matching.super.isPerfect();
    }

    @Override
    public Iterator iterator() {
        return Matching.super.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public Matching getMatching() {
        return matching;
    }

    @Override
    public Graph getGraph() {
        return matching.getGraph();
    }

    @Override
    public double getWeight() {
        return matching.getWeight();
    }

    @Override
    public Set getEdges() {
        return matching.getEdges();
    }
}
