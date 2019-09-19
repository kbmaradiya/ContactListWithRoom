package com.example.testapp.views.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.testapp.views.utils.Constants;

@Entity(tableName = Constants.TABLE_NAME)
public class Person {

    @PrimaryKey
    private int personId;

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

    public int getPersonId() {
        return personId;
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

    public void setPersonId(int personId) {
        this.personId = personId;
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
