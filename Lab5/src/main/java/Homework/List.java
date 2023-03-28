package Homework;

public class List extends Command{
    public List(String name) {
        super(name);
    }
    public static void execute(Catalog catalog){
        for(Document document: catalog.getDocuments())
        {
            System.out.println(document + "\n");
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
}
