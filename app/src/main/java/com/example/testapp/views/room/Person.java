package com.example.testapp.views.room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.testapp.views.utils.Constants;

import java.util.Objects;

@Entity(tableName = Constants.TABLE_NAME,indices = {@Index(value = "contactNumber",
        unique = true)})
public class Person {


//    @PrimaryKey(autoGenerate = true)
//    private int personId;

    @ColumnInfo(name = Constants.NAME)
    private String name;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "contactNumber")
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


//    public int getPersonId() {
//        return personId;
//    }
//
//    public void setPersonId(int personId) {
//        this.personId = personId;
//    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                contactNumber.equals(person.contactNumber) &&
                Objects.equals(imagePath, person.imagePath) &&
                Objects.equals(status, person.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactNumber);
    }
}
