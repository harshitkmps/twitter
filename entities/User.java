package twitter.entities;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class User {
    private final String userName;
    private final CopyOnWriteArrayList<Tweet> tweets;
    private final CopyOnWriteArraySet<User> followees;

    public User(String userName) {
        this.userName = userName;
        this.tweets = new CopyOnWriteArrayList<>();
        this.followees = new CopyOnWriteArraySet<>();
    }

    public void addFollowee(User user) {
        followees.add(user);
    }
    
    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }
    
    public String getUserName() {
        return this.userName;
    }

    public List<User> getFollowees() {
        return List.copyOf(followees);
    }
    
    public List<Tweet> getTweets() {
        return List.copyOf(tweets);
    }
}
