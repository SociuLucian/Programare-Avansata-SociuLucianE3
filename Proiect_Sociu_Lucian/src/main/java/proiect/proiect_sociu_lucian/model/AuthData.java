package proiect.proiect_sociu_lucian.model;

import java.io.Serializable;

public class AuthData implements Serializable {
    private String username;
    private String password;

    public AuthData() {
        // Constructorul implicit necesar pentru serializare/deserializare JSON
    }

    public AuthData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "AuthData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
