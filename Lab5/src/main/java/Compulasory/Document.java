package Compulasory;

import java.io.Serializable;
import java.util.*;

public class Document implements Serializable {
    private String id;
    private String title;
    private String location;
    private Map<String, Object> tags = new HashMap<>();

    public Document( String id, String title, String location) {
        this.id=id;
        this.title = title;
        this.location = location;
    }

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
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
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", tags=" + tags +
                '}';
    }
}

