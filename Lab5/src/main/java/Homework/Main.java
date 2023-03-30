package Homework;


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
        var book = new Document("001", "C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\article1.txt");
        var article = new Document("002","C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\article2.txt");
        catalog.add(book);
        catalog.add(article);
        Command[] commands = new Command[5];
        Save save = new Save("save");
        commands[0]=save;
        save.execute(catalog,"C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\catalog.json");
        Load load = new Load("load");
        commands[1] = load;
        try {
            Catalog catalog1 = load.execute("C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\catalog.json");
        }
        catch (Load.InvalidCatalogException exception)
        {
            System.out.println("Invalid file");
        }
        Catalog catalog1 = load.execute("C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\catalog.json");
        List list = new List("list");
        commands[2] = list;
        list.execute(catalog1);
        View view = new View("view");
        commands[3] = view;
        view.execute(catalog1.findById("002"));
        for(Command command : commands)
        {
            System.out.println(command);
        }
        Report reportGenerator = new Report();
        reportGenerator.generateReport(catalog1);

    }


}
