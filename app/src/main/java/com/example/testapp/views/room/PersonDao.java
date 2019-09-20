package com.example.testapp.views.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.testapp.views.utils.Constants;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface PersonDao {

    @Insert
    void insertPerson(List<Person> persons);


    @Update
    void updatePerson(Person persons);


    @Query("SELECT * FROM " + Constants.TABLE_NAME)
    Maybe<List<Person>> getAllPersons();

    @Query("SELECT * FROM " + Constants.TABLE_NAME+" WHERE "+ Constants.STATUS+" = :status")
    Maybe<List<Person>> getAllFavouritePersons(String status);
}
