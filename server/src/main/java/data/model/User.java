package data.model;

import java.io.Serializable;

public class User implements Serializable {

    private long id;
    private String username;
    private String password;
    private String email;

    public User() {

    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(username)
                .append(", ")
                .append(password)
                .append(", ")
                .append(email)
                .append(", ")
                .append(email)
                .toString();
    }

    //region getters & setters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //endregion
}
