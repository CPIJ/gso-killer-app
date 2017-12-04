package web.model.response;

public class SignUpResponse extends Response {

    private SignUpResponse(boolean hasError, String message) {
        super(hasError, message);
    }

    public SignUpResponse() {
        
    }

    //region factory
    public static SignUpResponse EmailAlreadyExists() {
        return new SignUpResponse(true, "Email address was alreay taken");
    }

    public static SignUpResponse Success() {
        return new SignUpResponse(false, "User sucessfully registered!");
    }
    //endregion
}

