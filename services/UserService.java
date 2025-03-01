package twitter.services;

import twitter.entities.User;
import twitter.exceptions.BadRequestException;
import twitter.exceptions.UserAlreadyExistsException;
import twitter.exceptions.UserNotFoundException;

public interface UserService {
    public User createUser(String userName) throws UserAlreadyExistsException, BadRequestException;

    public User getUser(String userName) throws UserNotFoundException, BadRequestException;

    public void followUser(String followeeUserName, String followerUserName) throws BadRequestException, UserNotFoundException;
}
