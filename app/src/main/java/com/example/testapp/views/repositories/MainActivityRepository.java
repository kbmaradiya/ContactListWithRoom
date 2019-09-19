package com.example.testapp.views.repositories;

import android.app.Application;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.testapp.views.room.Person;
import com.example.testapp.views.room.PersonDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivityRepository {

    public static void fetchAllContactsFromDevice(final Application application) {
        final List<Person> contacts = new ArrayList<>();

        Cursor cursor = application.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if ((cursor != null ? cursor.getCount() : 0) > 0) {
            while (cursor.moveToNext()) {

                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String picUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

                Person person= new Person(name,phoneNumber,picUri);

                contacts.add(person);
            }
        }
        if (cursor != null) {
            cursor.close();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                PersonDatabase.getInstance(application).personDao().insertPerson(contacts);
            }
        }).start();

    }


    public static List<Person> getAllPersonsFromDatabase(Application application) {
        List<Person> personList=PersonDatabase.getInstance(application).personDao().getAllPersons();
        return personList;
    }
}
