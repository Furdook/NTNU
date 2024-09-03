package tasks.O5.twitter;

import java.util.ArrayList;
import java.util.List;

public class TwitterAccount {
    public List<TwitterAccount> following = new ArrayList<TwitterAccount>();
    public List<TwitterAccount> followers = new ArrayList<TwitterAccount>();
    private List<Tweet> liked = new ArrayList<Tweet>();
    private List<Tweet> tweets = new ArrayList<Tweet>();
    private String username;

    public TwitterAccount(String username) {
        this.username = username;
    }

    public String getUserName() {
        return this.username;
    }

    public void follow(TwitterAccount account) {
        this.following.add(account);
        account.followers.add(this);
    }

    public void unfollow(TwitterAccount account) {
        this.following.remove(account);
        account.followers.remove(this);
    }

    public boolean isFollowing(TwitterAccount account) {
        return this.following.contains(account);
    }

    public boolean isFollowedBy(TwitterAccount account) {
        return this.followers.contains(account);
    }

    public void tweet(String text) {
        this.tweets.add(new Tweet(this, text));
    }

    public void retweet(Tweet tweet) {
        this.tweets.add(new Tweet(this, tweet));
    }

    public void like(Tweet tweet) {
        this.liked.add(tweet);
    }

    public void unlike(Tweet tweet) {
        this.liked.remove(tweet);
    }

    public Tweet getTweet(int i) {
        return this.tweets.get(this.tweets.size() - i);
    }

    public int getTweetCount() {
        return this.tweets.size();
    }

    public int getRetweetCount() {
        int i = 0;

        for (Tweet t : this.tweets) 
            if (t.getOriginalTweet().getOwner() == this) 
                i += t.getRetweetCount();
        
        return i;
    }
}
