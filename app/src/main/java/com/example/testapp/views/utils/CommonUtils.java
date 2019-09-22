package com.example.testapp.views.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.example.testapp.R;
import com.example.testapp.views.repositories.MainActivityRepository;
import com.example.testapp.views.room.Person;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;

public class CommonUtils {

    public static void deleteAlertDialog(Context application, Person person, MutableLiveData<List<Person>> contactPersonsList) {
        AlertDialog dialog = new AlertDialog.Builder(application, R.style.MyAlertDialogStyle)
                .setMessage("Are you sure you want to delete?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        person.setStatus(Constants.CONTACT_STATUS.DELETED.getStatus());
                        MainActivityRepository.setPersonAsFavouriteOrDeleted(application, person, contactPersonsList);
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


    public static void callOrMessage(Context context, Person person) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);

        TextView txtCall = dialog.findViewById(R.id.txtCall);
        TextView txtMessage = dialog.findViewById(R.id.txtMessage);

        txtCall.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + person.getContactNumber()));
                context.startActivity(intent);
            }
        });

        txtMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("vnd.android-dir/mms-sms");
                sendIntent.putExtra("address", person.getContactNumber());
                context.startActivity(sendIntent);
            }
        });

        dialog.show();
    }
}
