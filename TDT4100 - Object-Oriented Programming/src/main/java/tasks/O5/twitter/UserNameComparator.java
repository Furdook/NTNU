package tasks.O5.twitter;

import java.util.Comparator;

public class UserNameComparator implements Comparator<TwitterAccount>{

    @Override
    public int compare(TwitterAccount a, TwitterAccount b) {
        return a.getUserName().compareTo(b.getUserName());
    }
    
}
