package twitter.services;

import twitter.entities.Tweet;
import twitter.exceptions.BadRequestException;
import twitter.exceptions.UserNotFoundException;

public interface TweetService {
    public Tweet postTweet(String userName, String msg) throws BadRequestException, UserNotFoundException;
}
