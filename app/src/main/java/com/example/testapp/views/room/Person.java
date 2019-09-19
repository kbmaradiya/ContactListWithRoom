package com.example.testapp.views.room;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.testapp.views.utils.Constants;

@Entity(tableName = Constants.TABLE_NAME)
public class Person {


    @PrimaryKey(autoGenerate = true)
    private long personId;

    @ColumnInfo(name = Constants.NAME)
    private String name;


    @ColumnInfo(name = Constants.CONTACT_NUMBER)
    private String contactNumber;


    @ColumnInfo(name = Constants.IMAGE_PATH)
    private String imagePath;

    public Person(String name, String contactNumber, String imagePath) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.imagePath = imagePath;
    }


    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getImagePath() {
        return imagePath;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



}
