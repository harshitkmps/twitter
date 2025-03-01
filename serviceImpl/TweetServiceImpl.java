package twitter.serviceImpl;

import twitter.entities.Tweet;
import twitter.entities.User;
import twitter.exceptions.BadRequestException;
import twitter.exceptions.UserNotFoundException;
import twitter.repositories.RepositoryService;
import twitter.services.TweetService;

public class TweetServiceImpl implements TweetService {
    RepositoryService repositoryService;

    public TweetServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    private Boolean isNonNullNotEmptyString(String s){
        return !(s == null || s.trim().isEmpty());
    }

    @Override
    public Tweet postTweet(String userName, String msg) throws BadRequestException, UserNotFoundException {
        if (!isNonNullNotEmptyString(msg)) {
            throw new BadRequestException("Please provide valid tweet msg");
        }
        User user = this.repositoryService.getUser(userName);
        Tweet tweet = new Tweet(user, msg);
        user.addTweet(tweet);
        System.out.println("user " + userName + " just posted a tweet " + msg);
        return tweet;
    }
}
