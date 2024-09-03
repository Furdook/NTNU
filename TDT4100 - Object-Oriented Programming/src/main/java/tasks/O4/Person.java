package tasks.O4;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private char gender;
    private String name;
    private Person mother;
    private Person father;
    private List<Person> children = new ArrayList<Person>();

    public Person(String name, char gender) {
        if (gender != 'M' && gender != 'F') throw new IllegalArgumentException("Gender must be male of female");
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return this.name;
    }

    public char getGender() {
        return this.gender;
    }

    public Person getMother() {
        return this.mother;
    }

    public Person getFather() {
        return this.father;
    }

    public int getChildCount() {
        return this.children.size();
    }

    public Person getChild(int i) {
        return this.children.get(i);
    }

    public void setMother(Person mother) {
        if (mother.getGender() != 'F') throw new IllegalArgumentException("Mother must be female");
        if (mother == this) throw new IllegalArgumentException("You cannot be your own mother");
        if (this.mother != mother && this.mother != null) this.mother.children.remove(this);
        
        mother.children.add(this);
        this.mother = mother;
    }

    public void setFather(Person father) {
        if (father.getGender() != 'M') throw new IllegalArgumentException("Father must be male");
        if (father == this) throw new IllegalArgumentException("You cannot be your own father");
        if (this.father != father && this.father != null) this.father.children.remove(this);
        
        father.children.add(this);
        this.father = father;
    }

    public void addChild(Person child) {
        if (this.getGender() == 'F')
            child.setMother(this);
        if (this.getGender() == 'M')
            child.setFather(this);
    }

    public void removeChild(Person child) {
        if (this.getGender() == 'F')
            child.setMother(null);
        if (this.getGender() == 'M')
            child.setFather(null);
    }
}
