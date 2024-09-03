package tasks.O4;

import java.util.ArrayList;
import java.util.List;

public class Tweet {
    private List<TwitterAccount> likes = new ArrayList<TwitterAccount>();  // TODO 
    private List<TwitterAccount> retweets = new ArrayList<TwitterAccount>();
    private TwitterAccount author;
    private Tweet originalTweet;
    private String tweetText;
    
    public Tweet(TwitterAccount author, String tweet) {
        this.author = author;
        this.tweetText = tweet;
        this.originalTweet = this;
    }

    public Tweet(TwitterAccount author, Tweet tweet) {
        if (author == tweet.getOwner()) throw new IllegalArgumentException("You cannot retweet yourself");
        
        tweet.getOriginalTweet().retweets.add(author);
        this.originalTweet = tweet.getOriginalTweet();
        this.tweetText = tweet.tweetText;
        this.author = author;

    }

    public void like(TwitterAccount author) {
        this.getOriginalTweet().likes.add(author);
    }

    public void unlike(TwitterAccount author) {
        this.getOriginalTweet().likes.remove(author);
    }

    public String getText() {
        return this.tweetText;
    }

    public TwitterAccount getOwner() {
        return this.author;
    }

    public Tweet getOriginalTweet() {
        return this.originalTweet == null ? this : this.originalTweet;
    }

    public int getRetweetCount() {
        return this.retweets.size();
    }
}