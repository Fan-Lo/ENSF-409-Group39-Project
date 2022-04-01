package edu.ucalgary.ensf409;

import java.util.*;

public class Family {
    private ArrayList<Person> familyMembers = null;
    private Hamper[] hamper;

    public Family() {
        familyMembers = new ArrayList<>();
    }

    public void addFamilyMember(Person member) {
        this.familyMembers.add(member);
    }
}
