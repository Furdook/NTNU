package tasks.O2;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Person {

    private final String[] domains = {"ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv", "tw", "tz", "ua", "ug", "um", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw"};
    private String name;
    private String email;
    private char gender;
    private Date dob;
    private String ssn;

    public void setName(String name) {
        String[] split = name.split(" ");

        if ((split.length != 2)
        || (split[0].length() < 2 || split[1].length() < 2)
        || (!split[0].matches("[a-zA-Z]+") || !split[1].matches("[a-zA-Z]+"))) 
            throw new IllegalArgumentException();

        this.name = name;
    } 

    public void setEmail(String email) {

        if ((!email.contains("@"))
        || (!Arrays.asList(domains).contains(email.substring(email.length() - 2)) || email.charAt(email.length()-3) != '.')
        || (!email.substring(0, email.indexOf("@")).equals(this.name.toLowerCase().replace(" ", ".")))) 
            throw new IllegalArgumentException("Not a valid email address");

        this.email = email;
    }  
    
    public void setBirthday(Date dob) {
        if (dob.after(new Date())) throw new IllegalArgumentException();
        this.dob = dob;
    }  
    
    public void setGender(char c) {
        if (c != 'M' && c != 'F' && c != '\0') throw new IllegalArgumentException();
        this.gender = c;
    }  

    public void setSSN(String ssn) {
        /* if ((ssn.length())
        || (ssn.substring(0, 5) != String.valueOf(this.dob))) throw new IllegalArgumentException();
 */
        //convert this.dob to string in format DD MM YY:


        String dob = String.valueOf(this.dob);

        System.out.println(dob);

    
    }

    public static void main(String[] args) {
        Person test = new Person();
        test.setBirthday(new Date());

        System.out.println(new Date());
        test.setSSN("123456789875");
    }

/* - Et personnummer består av 11 siffer, med følgende struktur: **D1D2**M1M2**Y1Y2**N1N2N3**K1K2** (fet skrift for lesbarhet).
- De seks første sifrene, **D1D2**M1M2**Y1Y2**, tilsvarer fødselsdatoens dag (1-31), måned (1-12) og år (0-99).
- De tre neste sifrene, N1N2N3, kan antas å være vilkårlige, men N3 må være partall for kvinner og oddetall for menn.
- De to siste sifrene, K1K2, er kontrollsifre, som hver for seg beregnes ut fra de foregående sifrene. 
Formelen for dem begge er `11 – (VS % 11)`, hvor VS (veid sum) for `K1` er `D1*F1 + D2*F2 + … + N2*F8 + N3*F9` og `VS` for `K2` 
er `D1*G1 + D2*G2 + … + N3*G9 + K1*G10`. F’ene og G’ene er oppgitt i tabellen under. 
Dersom formelen gir tallet 11 så skal verdien 0 brukes isteden. 
Om både K1 og K2 stemmer med kontrollsifferne generert basert på formlene over, så er kontrollsifferne i personnummeret gyldig.
 */

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getBirthday() {
        return this.dob;
    }

    public char getGender() {
        return this.gender;
    }

    public String getSSN() {
        return this.ssn;
    }
}
