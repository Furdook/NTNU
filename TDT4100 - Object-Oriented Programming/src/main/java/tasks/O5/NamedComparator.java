package tasks.O5;

import java.util.Comparator;

public class NamedComparator implements Comparator<Named>{

    @Override
    public int compare(Named o1, Named o2) {
        int fName = o1.getFamilyName().compareTo(o2.getFamilyName());

        if (fName != 0) return fName;
        return o1.getGivenName().compareTo(o2.getGivenName());
    }
    
}
