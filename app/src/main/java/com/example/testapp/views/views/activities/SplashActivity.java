package com.example.testapp.views.views.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.testapp.R;
import com.example.testapp.views.permissionutil.PermissionManager;
import com.example.testapp.views.permissionutil.Permissions;
import com.example.testapp.views.viewModels.MainActivityViewModel;
import com.example.testapp.views.viewModels.SplashActivityViewModel;

import java.util.List;

public class SplashActivity extends AppCompatActivity implements PermissionManager.PermissionListener {

    public static SplashActivityViewModel splashActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        splashActivityViewModel = ViewModelProviders.of(this).get(SplashActivityViewModel.class);

        if (!PermissionManager.hasPermissions(this, Permissions.CONTACT)) {
            PermissionManager.requestPermissions(this, this, "", Permissions.CONTACT);
        } else {

            Log.e("SplashActivity", "has Permission");
            splashActivityViewModel.fetchContactsFromDevice();
        }

        regesterLiveDataObserver();

    }

    private void regesterLiveDataObserver() {
        splashActivityViewModel.getIsDataAdded().observe(this, isDataAdded -> {
            if (isDataAdded) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(SplashActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPermissionsGranted(List<String> perms) {
        Log.e("MainActivity", "onPermissionsGranted " + perms);
        splashActivityViewModel.fetchContactsFromDevice();

    }

    @Override
    public void onPermissionsDenied(List<String> perms) {

        Log.e("MainActivity", "onPermissionsDenied " + perms);

        PermissionManager.requestPermissions(this, this, "", perms.toArray(new String[0]));
    }

    @Override
    public void onPermissionRequestRejected() {
        Log.e("MainActivity", "onPermissionRequestRejected");
    }

    @Override
    public void onPermissionNeverAsked(List<String> perms) {
        Log.e("MainActivity", "onPermissionNeverAsked" + perms.toString());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.e("MainActivity", "onRequestPermissionsResult");
        PermissionManager.onRequestPermissionsResult(this, this, requestCode, Permissions.CONTACT, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PermissionManager.RUNTIME_PERMISSION) {
            if (!PermissionManager.hasPermissions(this, Permissions.CONTACT)) {
                PermissionManager.requestPermissions(this, this, "", Permissions.CONTACT);
            } else {
                Log.e("MainActivity", "has Permission");
                splashActivityViewModel.fetchContactsFromDevice();
            }
        }
    }

}
