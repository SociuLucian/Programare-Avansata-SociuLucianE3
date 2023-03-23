package Compulasory;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException{
        Main app = new Main();
        app.testCreateSave();
    }

    private void testCreateSave() throws IOException {
        Catalog catalog =
                new Catalog("MyDocuments");
        var book = new Document("001", "article1","/src/main/resources/article1.txt");
        var article = new Document("002","article2","/src/main/resources/article2.txt");
        catalog.add(book);
        catalog.add(article);

        CatalogUtil.save(catalog, "src/main/resources/catalog.json");
    }

    /*private void testLoadView() throws CatalogUtil.InvalidCatalogException, IOException {
        Catalog catalog = CatalogUtil.load("d:/research/catalog.json");
        //CatalogUtil.view(catalog.findById("article1"));
    }*/

}
