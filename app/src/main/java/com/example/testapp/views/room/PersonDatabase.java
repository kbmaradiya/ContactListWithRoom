package com.example.testapp.views.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.testapp.views.utils.Constants;


@Database(entities = {Person.class}, version = 1,exportSchema = false)
public abstract class PersonDatabase extends RoomDatabase {

    private static PersonDatabase personDatabase;

    public abstract PersonDao personDao();

    public static synchronized PersonDatabase getInstance(Context context) {
        if (personDatabase == null) {
            personDatabase = Room.databaseBuilder(context.getApplicationContext(), PersonDatabase.class, Constants.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return personDatabase;
    }
}
