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
    private MutableLiveData<Boolean> isDataAdded;

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

    public MutableLiveData<Boolean> getIsDataAdded() {
        if (isDataAdded==null){
            isDataAdded=new MutableLiveData<>();
        }
        return isDataAdded;
    }

    public void fetchContactsFromDevice(){
        MainActivityRepository.fetchAllContactsFromDevice(application,getIsDataAdded());
    }

    public void getAllPersonsFromDatabase() {
        MainActivityRepository.getAllPersonsFromDatabase(application,getPersonsList());
    }
}
