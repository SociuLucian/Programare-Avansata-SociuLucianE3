package Homework;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class View extends Command{
    public View(String name) {
        super(name);
    }
    public static void execute(Document document) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        File file = new File(document.getLocation());
            desktop.open(file);
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
        return super.toString();
    }
    public class InvalidDocumentException extends Exception {
        public InvalidDocumentException(Exception ex) {
            super("Invalid document file.", ex);
        }
    }

}
