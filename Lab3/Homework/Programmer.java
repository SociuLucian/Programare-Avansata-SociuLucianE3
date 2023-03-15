package Homework;

import java.time.LocalDate;

public class Programmer extends Person{
            private String favoriteProgrammingLanguage;

            public Programmer(String name, LocalDate birthDate, String favoriteProgrammingLanguage,int id) {
                super(name, birthDate,id);
                this.favoriteProgrammingLanguage = favoriteProgrammingLanguage;
            }

            public String getFavoriteProgrammingLanguage() {
                return favoriteProgrammingLanguage;
            }
        }

