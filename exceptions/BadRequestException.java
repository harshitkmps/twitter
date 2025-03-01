package twitter.exceptions;

public class BadRequestException extends Exception {
    public BadRequestException(String msg) {
        super("Bad request :: " + msg);
    }
}
