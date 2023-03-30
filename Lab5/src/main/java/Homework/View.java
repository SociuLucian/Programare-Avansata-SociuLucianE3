package Homework;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class View extends Command{
    public View(String name) {
        super(name);
    }
    public void execute(Document document) throws IOException ,View.InvalidDocumentException{
        Desktop desktop = Desktop.getDesktop();
        File file = new File(document.getLocation());
            desktop.open(file);
            if(!file.canExecute())
            {
                throw new InvalidDocumentException();
            }
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
        public InvalidDocumentException() {
            super("Invalid document file.");
        }
    }

}
