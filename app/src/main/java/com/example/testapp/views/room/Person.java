package com.example.testapp.views.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.testapp.views.utils.Constants;

@Entity(tableName = Constants.TABLE_NAME)
public class Person {

    @PrimaryKey
    public int personId;

    @ColumnInfo(name = Constants.FIRST_NAME)
    public String firstName;

    @ColumnInfo(name = Constants.LAST_NAME)
    public String lastName;

    @ColumnInfo(name = Constants.CONTACT_NUMBER)
    public String contactNumber;

    @ColumnInfo(name = Constants.IMAGE_PATH)
    public String imagePath;
}
