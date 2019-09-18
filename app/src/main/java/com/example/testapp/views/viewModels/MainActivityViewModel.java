package com.example.testapp.views.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MainActivityViewModel extends AndroidViewModel {

    private Application application;
    private MainActivityViewModel mainActivityViewModel;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }

}
