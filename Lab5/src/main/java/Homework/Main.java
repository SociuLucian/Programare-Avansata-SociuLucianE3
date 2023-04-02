package Homework;


import org.apache.tika.metadata.Metadata;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) throws IOException, Load.InvalidCatalogException, View.InvalidDocumentException {
        Main app = new Main();
        app.testCreateSave();
    }

    private void testCreateSave() throws IOException, Load.InvalidCatalogException, View.InvalidDocumentException {
        Catalog catalog =
                new Catalog("MyDocuments");
        var article1 = new Document("001", "C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\article1.txt");
        var article2 = new Document("002", "C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\article2.txt");
        var article3 = new Document("003", "C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\article3.txt");
        var article4 = new Document("004", "C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\article4.txt");
        var photo = new Document("005", "C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\poza-inst.jpg");
        catalog.add(article1);
        catalog.add(article2);
        catalog.add(article3);
        catalog.add(article4);
        catalog.add(photo);
        Command[] commands = new Command[5];
        Save save = new Save("save");
        commands[0] = save;
        save.execute(catalog, "C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\catalog.json");
        Load load = new Load("load");
        commands[1] = load;
        try {
            Catalog catalog1 = load.execute("C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\catalog.json");
        } catch (Load.InvalidCatalogException exception) {
            System.out.println("Invalid file");
        }
        Catalog catalog1 = load.execute("C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\catalog.json");
        List list = new List("list");
        commands[2] = list;
        list.execute(catalog1);
        View view = new View("view");
        commands[3] = view;
        view.execute(catalog1.findById("002"));
        for (Command command : commands) {
            System.out.println(command);
        }
        Report reportGenerator = new Report();
        reportGenerator.generateReport(catalog1);
        Info info = new Info("info", photo);
       info.getInfo();

    }
    }
