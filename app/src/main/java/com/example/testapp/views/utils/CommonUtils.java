package com.example.testapp.views.utils;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.testapp.R;
import com.example.testapp.views.repositories.MainActivityRepository;
import com.example.testapp.views.room.Person;

import java.util.List;

public class CommonUtils {

    public static void deleteAlertDialog(Context application, Person person, MutableLiveData<List<Person>> contactPersonsList){
        AlertDialog dialog = new AlertDialog.Builder(application, R.style.MyAlertDialogStyle)
                .setMessage("Are you sure you want to delete?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        person.setStatus(Constants.CONTACT_STATUS.DELETED.getStatus());
                        MainActivityRepository.setPersonAsFavouriteOrDeleted(application,person,contactPersonsList);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
}
