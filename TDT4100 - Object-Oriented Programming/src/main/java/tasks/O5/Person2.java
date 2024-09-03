package tasks.O5;

public class Person2 implements Named {
    private String givenName;
    private String familyName;

    public Person2(String fullName) {
        this.setFullName(fullName);
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getGivenName() {
        return this.givenName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public void setFullName(String fullName) {
        String[] tmp = fullName.split(" ");
        this.givenName = tmp[0];
        this.familyName = tmp[1];
    }

    public String getFullName() {
        return this.givenName + " " + this.familyName; 
    }
}
