package twitter.serviceImpl;

import twitter.entities.User;
import twitter.exceptions.BadRequestException;
import twitter.exceptions.UserAlreadyExistsException;
import twitter.exceptions.UserNotFoundException;
import twitter.repositories.RepositoryService;
import twitter.services.UserService;

public class UserServiceImpl implements UserService {

    RepositoryService repositoryService;

    public UserServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    private Boolean isNonNullNotEmptyString(String s){
        return !(s == null || s.trim().isEmpty());
    }

    @Override
    public User createUser(String userName) throws UserAlreadyExistsException, BadRequestException{
        if (!isNonNullNotEmptyString(userName)) {
            throw new BadRequestException("Please provide valid non empty userName");
        }
        User user = new User(userName);
        this.repositoryService.addUser(user);
        System.out.println("user with username " + userName + " created");
        return user;
    }

    @Override
    public User getUser(String userName) throws UserNotFoundException, BadRequestException {
        if (!isNonNullNotEmptyString(userName)) {
            throw new BadRequestException("Please provide valid non empty userName");
        }
        return this.repositoryService.getUser(userName);
    }

    @Override
    public void followUser(String followeeUserName, String followerUserName)
            throws BadRequestException, UserNotFoundException {
        if (!isNonNullNotEmptyString(followeeUserName) || !isNonNullNotEmptyString(followerUserName)) {
            throw new BadRequestException("please provide valid usernames for follower and followee");
        }
        if (followeeUserName.equals(followerUserName)) {
            throw new BadRequestException("User cannot follow itself");
        }
        User follower = this.repositoryService.getUser(followerUserName);
        User followee = this.repositoryService.getUser(followeeUserName);
        follower.addFollowee(followee);
        System.out.println("user " + followerUserName + " is now following " + followeeUserName);
    }
}
