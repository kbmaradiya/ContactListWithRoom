package com.example.testapp.views.repositories;

import android.app.Application;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.testapp.views.room.Person;
import com.example.testapp.views.room.PersonDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivityRepository {

    public static void fetchAllContactsFromDevice(final Application application, MutableLiveData<Boolean> isDataAdded) {
        final List<Person> contacts = new ArrayList<>();

        Cursor cursor = application.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

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




        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                PersonDatabase.getInstance(application).personDao().insertPerson(contacts);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("MainActivityRepository","onError");

            }

            @Override
            public void onComplete() {
                Log.e("MainActivityRepository","Data added");
                isDataAdded.setValue(true);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("MainActivityRepository","onError "+ e.getMessage());

            }
        });


    }


    public static void getAllPersonsFromDatabase(Application application, MutableLiveData<List<Person>> personsList) {
        PersonDatabase.getInstance(application).personDao().getAllPersons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Person>>() {
                    @Override
                    public void accept(List<Person> personList) throws Exception {
                        personsList.setValue(personList);
                    }
                });


    }
}
