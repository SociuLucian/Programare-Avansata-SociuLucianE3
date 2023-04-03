package Homework;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

public class Bonus {
    public Map<Document, Set<Document>> getRelatedDocs(Catalog catalog){
        Map<Document,Set<Document>> relatedDocs = new HashMap<>();
        for(Document document : catalog.getDocuments())
        {
            Set<Document> helper = new HashSet<>();
            for(Document document1 : catalog.getDocuments())
            {
                if(document!=document1 )
                {
                    for(String s : document1.getTags().keySet())
                    {
                        if(document.getTags().get(s).equals(document1.getTags().get(s)))
                        {
                            helper.add(document1);
                        }
                    }
                }
            }
            relatedDocs.put(document,helper);
        }
        return relatedDocs;
    }
    public void getColouring(Catalog catalog)
    {

        Map<Document,Set<Document>> relatedDocs = getRelatedDocs(catalog);
        Graph<Document, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        for (Document document : relatedDocs.keySet()) {
            graph.addVertex(document);
            for (Document relatedDoc : relatedDocs.get(document)) {
                graph.addVertex(relatedDoc);
                graph.addEdge(document, relatedDoc);
            }
        }

        GreedyColoring<Document, DefaultEdge> coloring = new GreedyColoring<>(graph);
        //System.out.println(coloring.getColoring().getColors());
        for(Document document : coloring.getColoring().getColors().keySet())
        {
            System.out.println("Documentul " + document.getId() + " \n" + "Cu tag-urile : " + document.getTags() +
                    "\nAre culoarea: "  +  coloring.getColoring().getColors().get(document));
        }
        System.out.println("Number of color: " + coloring.getColoring().getNumberColors());
    }
    }

