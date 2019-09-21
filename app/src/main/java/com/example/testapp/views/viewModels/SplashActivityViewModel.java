package com.example.testapp.views.viewModels;

import android.app.Application;

import com.example.testapp.views.repositories.MainActivityRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SplashActivityViewModel extends AndroidViewModel {

    private Application application;
    private MutableLiveData<Boolean> isDataAdded;


    public SplashActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
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
}
