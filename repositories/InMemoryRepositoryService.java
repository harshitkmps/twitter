package twitter.repositories;

import java.util.concurrent.ConcurrentHashMap;
import twitter.entities.User;
import twitter.exceptions.BadRequestException;
import twitter.exceptions.UserAlreadyExistsException;
import twitter.exceptions.UserNotFoundException;

public class InMemoryRepositoryService implements RepositoryService {
    private final ConcurrentHashMap<String, User> userNameToUserMapping;

    public InMemoryRepositoryService() {
        this.userNameToUserMapping = new ConcurrentHashMap<>();
    }

    private boolean isNonNullNotEmptyString(String s) {
        return s != null && !s.trim().isEmpty();
    }

    @Override
    public void addUser(User user) throws UserAlreadyExistsException, BadRequestException {
        if (user == null) {
            throw new BadRequestException("Please provide a valid user");
        }
        if (userNameToUserMapping.putIfAbsent(user.getUserName(), user) != null) {
            throw new UserAlreadyExistsException(
                    "Another user with username " + user.getUserName() + " already exists");
        }
    }

    @Override
    public User getUser(String userName) throws UserNotFoundException, BadRequestException {
        if (!isNonNullNotEmptyString(userName)) {
            throw new BadRequestException("Please provide a valid username");
        }
        User user = userNameToUserMapping.get(userName);
        if (user == null) {
            throw new UserNotFoundException("User not found with the given username");
        }
        return user;
    }
}
