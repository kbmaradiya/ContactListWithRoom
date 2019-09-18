package com.example.testapp.views.room;

import androidx.room.Dao;
import androidx.room.Insert;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
     void insertPerson(List<Person> persons);
}
