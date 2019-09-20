package com.example.testapp.views.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
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

    @ColumnInfo(name = Constants.STATUS)
    private String status;



    public Person(String name, String contactNumber, String imagePath,String status) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.imagePath = imagePath;
        this.status=status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
