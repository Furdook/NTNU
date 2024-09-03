package tasks.O5.twitter;

import java.util.Comparator;

public class TweetsCountComparator implements Comparator<TwitterAccount> {

    @Override
    public int compare(TwitterAccount a, TwitterAccount b) {
        return b.getTweetCount() - a.getTweetCount();
    }
    
}
