package Homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.html.HTMLTableRowElement;

import java.io.File;
import java.io.IOException;

public class Load extends Command{


    public Load(String name) {
        super(name);
    }
    public static class InvalidCatalogException extends Exception {
        public InvalidCatalogException() {
            super("Invalid catalog file.");
        }

    }
    public static Catalog execute(String path)
            throws InvalidCatalogException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(path);
        Catalog catalog = objectMapper.readValue(
                file,
                Catalog.class);
        if(!file.canRead())
        {
            throw new InvalidCatalogException();
        }
           return catalog;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String toString() {
        return  super.toString();
    }
}
