package web.model.request;

import org.joda.time.DateTime;

public class SignUpRequest extends Request {

    private String username;
    private String email;
    private String password;
    private DateTime date;

    public SignUpRequest() {
    }

    //region getters & setters

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public DateTime getDate() {
        return date;
    }

    //endregion
}
