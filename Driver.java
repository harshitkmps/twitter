package twitter;

import java.util.concurrent.TimeUnit;
import twitter.repositories.InMemoryRepositoryService;
import twitter.serviceImpl.RecentTweetTimelineGenerator;
import twitter.serviceImpl.TweetServiceImpl;
import twitter.serviceImpl.UserServiceImpl;
import twitter.services.TimelineGenerator;
import twitter.services.TweetService;
import twitter.services.UserService;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Hello world!!");
        InMemoryRepositoryService inMemoryRepositoryService = new InMemoryRepositoryService();
        UserService userService = new UserServiceImpl(inMemoryRepositoryService);
        TweetService tweetService = new TweetServiceImpl(inMemoryRepositoryService);
        TimelineGenerator timelineGenerator = new RecentTweetTimelineGenerator(inMemoryRepositoryService);

        // test your cases here....


        System.out.println("case: Happy case ==========");
        try {
            userService.createUser("mark");
            userService.createUser("jeff");
            userService.createUser("elon");
            // mark follows jeff
            userService.followUser("jeff", "mark");
            // jeff follows mark
            userService.followUser("mark", "jeff");
            // elon follows mark
            // elon follows jeff
            userService.followUser("mark", "elon");
            userService.followUser("jeff", "elon");
            tweetService.postTweet("jeff", "Hello, I'm jeff bezos. I run Amazon");
            TimeUnit.SECONDS.sleep(3);
            tweetService.postTweet("mark", "Hello there, I'm Mark Zuckerberg, I run Meta");
            TimeUnit.SECONDS.sleep(2);
            tweetService.postTweet("elon", "Hello there, I'm elon, I just moved into white house");
            timelineGenerator.generateTimeline("mark");
            timelineGenerator.generateTimeline("jeff");
            timelineGenerator.generateTimeline("elon");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("Case: duplicate username ============");
        try {
            userService.createUser("mark");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("case: trying to create with null username ============");
        try {
            userService.createUser(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Case: empty username ============");
        try {
            userService.createUser("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("case: invalid username for followee ============");
        try {
            userService.followUser("randomuser", "mark");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("case: invalid username in follower ============");
        try {
            userService.followUser("mark", "randomuser");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("case: user trying to follow itself ============");
        try {
            userService.followUser("mark", "mark");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("case: invalid username timeline generator ============");
        try {
            timelineGenerator.generateTimeline("randomuser");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("case: Empty tweet msg ============");
        try {
            tweetService.postTweet("mark", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("case: generate timeline - invalid user ============");
        try {
            timelineGenerator.generateTimeline("somerandom");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
