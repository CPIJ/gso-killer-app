package web.model.response;

import data.model.User;

public class LoginResponse extends Response {

    private User user;

    private LoginResponse(User user, boolean hasError, String errorMessage) {
        super(hasError, errorMessage);
        this.user = user;
    }

    //region factory

    public static LoginResponse noUserFound() {
        return new LoginResponse(null, true, "No user found with these credentials.");
    }

    public static LoginResponse invalidCredentials() {
        return new LoginResponse(null, true, "Invalid password, username or both");
    }

    public static LoginResponse success(User user) {
        return new LoginResponse(user, false, "Login was successful!");
    }

    //endregion

    //region getters & setters
    public User getUser() {
        return user;
    }
    //endregion
}
