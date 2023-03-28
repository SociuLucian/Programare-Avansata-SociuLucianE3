package Homework;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private String id;
    private String location;
    private Map<String, Object> tags = new HashMap<>();

    public Document(@JsonProperty("id") String id, @JsonProperty("location") String location) {
        this.id=id;
        this.location = location;
    }

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;
        Document document = (Document) o;
        return id == document.id;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", tags=" + tags +
                '}';
    }
}

