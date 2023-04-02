package Homework;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private String id;
    private String location;
    private Map<String, String> tags = new HashMap<>();

    public Document(@JsonProperty("id") String id, @JsonProperty("location") String location) {
        this.id=id;
        this.location = location;
    }
    public Metadata extractMetadata() {
        Metadata metadata = new Metadata();
        try (FileInputStream inputStream = new FileInputStream(location)) {
            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();
            ParseContext context = new ParseContext();
            parser.parse(inputStream, handler, metadata,context);
        } catch (IOException e) {
            System.err.println("Error extracting metadata: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing file: " + e.getMessage());
        }
        return metadata;
    }

    public void addTag(String key, String tag) {
        tags.put(key, tag);
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

