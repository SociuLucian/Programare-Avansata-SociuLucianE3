package Homework;
import java.time.LocalDate;
public class Designer extends Person{
    private String favoriteDesignTool;

    public Designer(String name, LocalDate birthDate, String favoriteDesignTool,int id) {
        super(name, birthDate,id);
        this.favoriteDesignTool = favoriteDesignTool;
    }

    public String getFavoriteDesignTool() {
        return favoriteDesignTool;
    }
}

