package twitter.services;

import java.util.List;

import twitter.entities.*;
import twitter.exceptions.BadRequestException;
import twitter.exceptions.UserNotFoundException;

public interface  TimelineGenerator {
    public List<Tweet> generateTimeline(String userName) throws BadRequestException, UserNotFoundException;
}
