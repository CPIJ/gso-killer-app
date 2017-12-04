package web.model.response;

public class Response {

    private boolean hasError;
    private String message;

    public Response(boolean hasError, String message) {
        this.hasError = hasError;
        this.message = message;
    }

    public Response() {

    }

    public static Response somethingWentWrong() {
        return new Response(false, "Something went wrong :(");
    }

    //region getters & setters

    public boolean hasError() {
        return hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //endregion

}
