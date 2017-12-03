package data.model;

import org.joda.time.DateTime;

import java.io.Serializable;

public class User implements Serializable {

    private long id;
    private String username;
    private String password;
    private String email;
    private DateTime registerDate;

    public User(String username, String password, String email, DateTime registerDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registerDate = registerDate;
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
                .append(", ")
                .append(registerDate)
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

    public DateTime getRegisterDate() {
        return registerDate;
    }
    //endregion
}
