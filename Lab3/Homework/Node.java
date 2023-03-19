package Homework;

import java.util.Map;

public interface Node  {
    int getId();

    String getName();

    Map<Node, String> getRelationships();


}
