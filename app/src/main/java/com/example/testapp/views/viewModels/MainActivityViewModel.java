package com.example.testapp.views.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.testapp.views.repositories.MainActivityRepository;
import com.example.testapp.views.room.Person;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private Application application;
    private MutableLiveData<List<Person>> personsList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }


    public MutableLiveData<List<Person>> getPersonsList() {
        if (personsList==null){
            personsList=new MutableLiveData<>();
        }
        return personsList;
    }

    public void fetchContactsFromDevice(){
        MainActivityRepository.fetchAllContactsFromDevice(application);
    }

    public void getAllPersonsFromDatabase() {
        personsList.postValue(MainActivityRepository.getAllPersonsFromDatabase(application));
    }
}
