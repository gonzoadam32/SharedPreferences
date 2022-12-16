package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = (EditText) findViewById(R.id.name);
        EditText age = (EditText) findViewById(R.id.age);
        CheckBox enrolled = (CheckBox) findViewById(R.id.checkBox);

        Gson gson = new Gson();
        Address address = new Address("Germany", "Berlin");

        ArrayList<FamilyMember> family = new ArrayList<>();
        family.add(new FamilyMember("wife", 30));
        family.add(new FamilyMember("son", 10));

        Employee employee = new Employee("John", 30, "john@gmail.com", address, family);
        String json = gson.toJson(employee);

        Employee employee2 = gson.fromJson(json,Employee.class);
        String json2 = gson.toJson(family);

        ArrayList<FamilyMember> family2 = new ArrayList<>();

        Type familyType = new TypeToken<ArrayList<FamilyMember>>(){}.getType();
        ArrayList<FamilyMember>Family = gson.fromJson(json2,familyType);

        // Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        String s1 = sh.getString("name", "");
        int a = sh.getInt("age", 0);
        boolean e = sh.getBoolean("enrolled",false);

// We can then use the data
        name.setText(s1);
        age.setText(String.valueOf(a));
        enrolled.setChecked(e);
    }
    public void save (View v) {
        EditText name = (EditText) findViewById(R.id.name);
        EditText age = (EditText) findViewById(R.id.age);
        CheckBox enrolled = (CheckBox) findViewById(R.id.checkBox);

        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

// Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
        myEdit.putString("name", name.getText().toString());
        myEdit.putInt("age", Integer.parseInt(age.getText().toString()));

// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
        myEdit.commit();

    }


}