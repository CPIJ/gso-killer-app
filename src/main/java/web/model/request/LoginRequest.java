package web.model.request;

public class LoginRequest extends Request {

    private String username;
    private String password;

    public LoginRequest() {

    }

    //region getters & setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    //endregion
}
