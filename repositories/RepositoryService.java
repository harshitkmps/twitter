package twitter.repositories;

import twitter.entities.User;
import twitter.exceptions.BadRequestException;
import twitter.exceptions.UserAlreadyExistsException;
import twitter.exceptions.UserNotFoundException;

public interface RepositoryService {
    public void addUser(User user) throws UserAlreadyExistsException, BadRequestException;

    public User getUser(String userName) throws UserNotFoundException, BadRequestException;
}
