package twitter.entities;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Tweet {
    private final User author;
    private final String message;
    private final long timestamp;

    public Tweet(User author, String message) {
        this.author = author;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessage() {
        return this.message;
    }
    
    public User getUser() {
        return this.author;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public LocalDateTime getLocalTimestamp() {
        LocalDateTime date =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(this.getTimestamp()), ZoneId.systemDefault());
        return date;
    }
}
