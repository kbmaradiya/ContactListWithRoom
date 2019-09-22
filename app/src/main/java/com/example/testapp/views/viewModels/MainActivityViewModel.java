package com.example.testapp.views.viewModels;

import android.app.Application;

import com.example.testapp.views.repositories.MainActivityRepository;
import com.example.testapp.views.room.Person;
import com.example.testapp.views.utils.Constants;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private Application application;
    private MutableLiveData<List<Person>> contactPersonsList;
    private MutableLiveData<List<Person>> favouritePersonsList;
    private MutableLiveData<List<Person>> deletedPersonsList;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }


    public MutableLiveData<List<Person>> getContactPersonsList() {
        if (contactPersonsList ==null){
            contactPersonsList =new MutableLiveData<>();
        }
        return contactPersonsList;
    }


    public MutableLiveData<List<Person>> getFavouritePersonsList() {
        if (favouritePersonsList==null){
            favouritePersonsList =new MutableLiveData<>();
        }
        return favouritePersonsList;
    }

    public MutableLiveData<List<Person>> getDeletedPersonsList() {
        if (deletedPersonsList==null){
            deletedPersonsList =new MutableLiveData<>();
        }
        return deletedPersonsList;
    }

    public void getAllPersonsFromDatabase() {
        MainActivityRepository.getAllPersonsFromDatabase(application, getContactPersonsList());
    }
    public void getFavouritePersonsFromDatabase(String status) {
        MainActivityRepository.getFavouriteOrDeletedPersonsFromDatabase(application, getFavouritePersonsList(),status);
    }public void getDeletedPersonsFromDatabase(String status) {
        MainActivityRepository.getFavouriteOrDeletedPersonsFromDatabase(application, getDeletedPersonsList(),status);
    }

    public void setPersonAsFavouriteOrNot(Person person){
        if (person.getStatus().equals(Constants.CONTACT_STATUS.FAVOURITE.getStatus())){
            person.setStatus(Constants.CONTACT_STATUS.CONTACT.getStatus());
        }else {
            person.setStatus(Constants.CONTACT_STATUS.FAVOURITE.getStatus());
        }
        MainActivityRepository.setPersonAsFavouriteOrDeleted(application,person,getContactPersonsList());
    }

}

