package com.example.testapp.views.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.testapp.views.repositories.MainActivityRepository;
import com.example.testapp.views.room.Person;
import com.example.testapp.views.utils.Constants;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private Application application;
    private MutableLiveData<List<Person>> contactPersonsList;
    private MutableLiveData<List<Person>> favouritePersonsList;
    private MutableLiveData<List<Person>> deletedPersonsList;
    private MutableLiveData<Person> deleted;

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

    public MutableLiveData<Person> getDeleted() {
        if (deleted==null){
            deleted=new MutableLiveData<Person>();
        }
        return deleted;
    }

    public void getAllPersonsFromDatabase() {
        MainActivityRepository.getAllPersonsFromDatabase(application, getContactPersonsList());
    }
    public void getFavouritePersonsFromDatabase(String status) {
        MainActivityRepository.getFavouriteOrDeletedPersonsFromDatabase(application, getFavouritePersonsList(),status);
    }public void getDeletedPersonsFromDatabase(String status) {
        MainActivityRepository.getFavouriteOrDeletedPersonsFromDatabase(application, getDeletedPersonsList(),status);
    }

    public void setPersonAsFavourite( Person person){
        person.setStatus(Constants.CONTACT_STATUS.FAVOURITE.getStatus());
        MainActivityRepository.setPersonAsFavouriteOrDeleted(application,person,getContactPersonsList());
    }
    public void setPersonAsDeleted(Person person){
        getDeleted().setValue(person);
    }

    public void adapterItemClick(){
        Log.e("MainActivityViewModel","item click");
    }
}

