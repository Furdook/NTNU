package tasks.O5.twitter;

import java.util.Comparator;

public class FollowersCountComparator implements Comparator<TwitterAccount> {

    @Override
    public int compare(TwitterAccount a, TwitterAccount b) {
        return b.followers.size() - a.followers.size();
    }
    
}
