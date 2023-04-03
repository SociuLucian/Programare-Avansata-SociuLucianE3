package Homework;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.*;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private List<Document> documents = new ArrayList<>();
    public Catalog(@JsonProperty("name") String name )  {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(Document doc) {
        documents.add(doc);
    }
    public void remove(Document doc)
    {
        documents.remove(doc);
    }
    public Document findById (String id){
        for (var doc : documents) {
            if (doc.getId().equals(id)) {
                return doc;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", documents=" + documents +
                '}';
    }
}
