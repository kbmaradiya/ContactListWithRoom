package com.example.testapp.views.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.testapp.views.utils.Constants;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPerson(List<Person> persons);

    @Update
    void updatePerson(Person persons);

    @Query("SELECT * FROM " + Constants.TABLE_NAME+" WHERE "+ Constants.STATUS+" IN (:fav,:contact)")
    Maybe<List<Person>> getAllPersons(String fav,String contact);

    @Query("SELECT * FROM " + Constants.TABLE_NAME+" WHERE "+ Constants.STATUS+" = :status")
    Maybe<List<Person>> getFavouriteOrDeletedPersons(String status);
}
