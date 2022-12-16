package com.example.sharedpreferences;

import java.util.ArrayList;

public class Employee
{
    private String firstname;
    private int age;
    private String mail;
    private Address mAddress;
    private ArrayList<FamilyMember> fam;

    public Employee(String firstName, int age, String mail, Address address, ArrayList<FamilyMember> f){
        this.firstname = firstName;
        this.age = age;
        this.mail = mail;
        mAddress = address;
        fam = f;


    }
}
