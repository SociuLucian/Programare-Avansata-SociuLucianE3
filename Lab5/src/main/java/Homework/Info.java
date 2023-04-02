package Homework;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.DefaultParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.FileInputStream;
import java.io.IOException;

public class Info extends Command {
    private Document document;
    private Metadata metadata;

    public Info(String name, Document document) {
        super(name);
        this.document = document;
        this.metadata = new Metadata();
        extractMetadata();
    }

    public void extractMetadata() {
        try (FileInputStream inputStream = new FileInputStream(document.getLocation())) {
            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();
            ParseContext context = new ParseContext();
            parser.parse(inputStream, handler, metadata, context);
        } catch (IOException e) {
            System.err.println("Error extracting metadata: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing file: " + e.getMessage());
        }
    }
    public void getInfo()
    {
        String[] metadataNames = metadata.names();

        for(String name : metadataNames) {
            System.out.println(name + ": " + metadata.get(name));
        }
    }
    public Metadata getMetadata() {
        return metadata;
    }

}

