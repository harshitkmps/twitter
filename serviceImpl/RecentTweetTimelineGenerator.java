package twitter.serviceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import twitter.entities.Tweet;
import twitter.entities.User;
import twitter.exceptions.BadRequestException;
import twitter.exceptions.UserNotFoundException;
import twitter.repositories.RepositoryService;
import twitter.services.TimelineGenerator;

public class RecentTweetTimelineGenerator implements TimelineGenerator {
    RepositoryService repositoryService;

    public RecentTweetTimelineGenerator(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    public List<Tweet> generateTimeline(String userName) throws BadRequestException, UserNotFoundException {
        User user = this.repositoryService.getUser(userName);
        List<Tweet> timeline = new ArrayList<>();
        List<User> followees = user.getFollowees();
        for (User followee : followees) {
            timeline.addAll(followee.getTweets());
        }
        timeline.sort(Comparator.comparing(Tweet::getTimestamp).reversed());
        System.out.println("timeline for " + user.getUserName());
        for (Tweet tweet : timeline) {
            System.out.println("User " + tweet.getUser().getUserName() + " posted tweet : " + tweet.getMessage()
                    + " at " + tweet.getLocalTimestamp());
        }
        return timeline;
    }
}
