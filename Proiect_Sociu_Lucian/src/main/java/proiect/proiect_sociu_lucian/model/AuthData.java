package proiect.proiect_sociu_lucian.model;

import java.io.Serializable;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthData)) return false;
        AuthData authData = (AuthData) o;
        return Objects.equals(username, authData.username) && Objects.equals(password, authData.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "AuthData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
