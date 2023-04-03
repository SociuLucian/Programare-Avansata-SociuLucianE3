package Homework;


import org.apache.tika.metadata.Metadata;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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
        var article6 = new Document("006", "C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\article6.txt");
        var article7 = new Document("007","C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\article7.txt");
        var article8 = new Document("008","C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\article8.txt");
        var article9 = new Document("009","C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\article9.txt");
        var article10 = new Document("010","C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\article10.txt");
        var article11 = new Document("011","C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\article11.txt");
        var article12 = new Document("012","C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\article12.txt");
        var article13 = new Document("013","C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\artictle13.txt");
        var article14 = new Document("014","C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\article14.txt");
        var article15 = new Document("015","C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab5\\src\\main\\resources\\Documente\\article15.txt");
        catalog.add(article1);
        catalog.add(article2);
        catalog.add(article3);
        catalog.add(article4);
        catalog.add(article6);
        catalog.add(article7);
        catalog.add(article8);
        catalog.add(article9);
        catalog.add(article10);
        catalog.add(article11);
        catalog.add(article12);
        catalog.add(article13);
        catalog.add(article14);
        catalog.add(article15);
        catalog.add(photo);
        addTags(catalog);
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
        //list.execute(catalog1);
        View view = new View("view");
        commands[3] = view;
        view.execute(catalog1.findById("002"));
        Report reportGenerator = new Report();
        reportGenerator.generateReport(catalog1);
        Info info = new Info("info", photo);
       info.getInfo();
       commands[4] = info;
        for (Command command : commands) {
            System.out.println(command);
        }
        Bonus bonus = new Bonus();
        Map<Document, Set<Document>> relatetDocs = bonus.getRelatedDocs(catalog1);
        for(Document document : relatetDocs.keySet()) {
            for (Document document1 : relatetDocs.get(document)) {
                System.out.println(document.getTags() + " " + document1.getTags());
            }
        }
        bonus.getColouring(catalog1);

    }
    public void addTags(Catalog catalog)
    {
        catalog.findById("001").addTag("title", "To Kill a Mockingbird");
        catalog.findById("001").addTag("author", "Harper Lee");
        catalog.findById("001").addTag("year", "1960");
        catalog.findById("001").addTag("genre", "Fiction");

        catalog.findById("002").addTag("title", "1984");
        catalog.findById("002").addTag("author", "George Orwell");
        catalog.findById("002").addTag("year", "1949");
        catalog.findById("002").addTag("genre", "Dystopian Fiction");

        catalog.findById("003").addTag("title", "Animal Farm");
        catalog.findById("003").addTag("author", "George Orwell");
        catalog.findById("003").addTag("year", "1945");
        catalog.findById("003").addTag("genre", "Satire");

        catalog.findById("004").addTag("title", "The Great Gatsby");
        catalog.findById("004").addTag("author", "F. Scott Fitzgerald");
        catalog.findById("004").addTag("year", "1925");
        catalog.findById("004").addTag("genre", "Fiction");

        catalog.findById("006").addTag("title", "The Bell Jar");
        catalog.findById("006").addTag("author", "Sylvia Plath");
        catalog.findById("006").addTag("year", "1963");
        catalog.findById("006").addTag("genre", "Autobiographical Fiction");

        catalog.findById("007").addTag("title", "One Hundred Years of Solitude");
        catalog.findById("007").addTag("author", "Gabriel García Márquez");
        catalog.findById("007").addTag("year", "1967");
        catalog.findById("007").addTag("genre", "Magical Realism");

        catalog.findById("008").addTag("title", "Love in the Time of Cholera");
        catalog.findById("008").addTag("author", "Gabriel García Márquez");
        catalog.findById("008").addTag("year", "1985");
        catalog.findById("008").addTag("genre", "Romance");

        catalog.findById("009").addTag("title", "The Alchemist");
        catalog.findById("009").addTag("author", "Paulo Coelho");
        catalog.findById("009").addTag("year", "1988");
        catalog.findById("009").addTag("genre", "Self-help");

        catalog.findById("010").addTag("title", "The Pilgrimage");
        catalog.findById("010").addTag("author", "Paulo Coelho");
        catalog.findById("010").addTag("year", "1987");
        catalog.findById("010").addTag("genre", "Autobiography");

        catalog.findById("011").addTag("title", "The God of Small Things");
        catalog.findById("011").addTag("author", "Arundhati Roy");
        catalog.findById("011").addTag("year", "1997");
        catalog.findById("011").addTag("genre", "Fiction");

        catalog.findById("012").addTag("title", "The White Tiger");
        catalog.findById("012").addTag("author", "Aravind Adiga");
        catalog.findById("012").addTag("year", "2008");
        catalog.findById("012").addTag("genre", "Satire");

        catalog.findById("013").addTag("title", "The Road");
        catalog.findById("013").addTag("author", "Cormac McCarthy");
        catalog.findById("013").addTag("year", "2006");
        catalog.findById("013").addTag("genre", "Post-Apocalyptic Fiction");

        catalog.findById("014").addTag("title", "No Country for Old Men");
        catalog.findById("014").addTag("author", "Cormac McCarthy");
        catalog.findById("014").addTag("year", "2005");
        catalog.findById("014").addTag("genre", "Thriller");

        catalog.findById("015").addTag("title", "The Lord of the Rings");
        catalog.findById("015").addTag("author", "J.R.R. Tolkien");
        catalog.findById("015").addTag("year", "1954");
        catalog.findById("015").addTag("genre", "Fantasy");

        catalog.findById("005").addTag("title","fosta mea poza de profil pe inst");
        catalog.findById("005").addTag("author","eu");
        catalog.findById("005").addTag("year","2022");
        catalog.findById("005").addTag("genre","nush");

    }
    }
