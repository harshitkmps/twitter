package twitter.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String msg) {
        super("User already exists :: " + msg);
    }
}
