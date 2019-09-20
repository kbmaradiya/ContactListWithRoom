package com.example.testapp.views.views.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.testapp.R;
import com.example.testapp.databinding.ActivityMainBinding;
import com.example.testapp.views.permissionutil.PermissionManager;
import com.example.testapp.views.permissionutil.Permissions;
import com.example.testapp.views.utils.Constants;
import com.example.testapp.views.viewModels.MainActivityViewModel;
import com.example.testapp.views.views.adapters.ViewPagerAdapter;
import com.example.testapp.views.views.fragments.ContactFragment;
import com.example.testapp.views.views.fragments.DeletedFragment;
import com.example.testapp.views.views.fragments.FavouritesFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PermissionManager.PermissionListener {

    private ActivityMainBinding activityMainBinding;

    public static MainActivityViewModel mainActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);


        if (!PermissionManager.hasPermissions(this, Permissions.CONTACT)) {
            PermissionManager.requestPermissions(this, this, "", Permissions.CONTACT);
        } else {

            Log.e("MainActivity", "has Permission");
            mainActivityViewModel.fetchContactsFromDevice();
        }

//        setupViewPager();

        setUpTabs();

    }

    private void setUpTabs() {
        activityMainBinding.tabLayout.addTab(activityMainBinding.tabLayout.newTab().setText(getString(R.string.contacts)),true);
        activityMainBinding.tabLayout.addTab(activityMainBinding.tabLayout.newTab().setText(getString(R.string.favourites)));
        activityMainBinding.tabLayout.addTab(activityMainBinding.tabLayout.newTab().setText(getString(R.string.deleted)));

    }


    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ContactFragment(), getString(R.string.contacts));
        adapter.addFragment(new FavouritesFragment(), getString(R.string.favourites));
        adapter.addFragment(new DeletedFragment(), getString(R.string.deleted));
        activityMainBinding.viewPager.setAdapter(adapter);
        activityMainBinding.tabLayout.setupWithViewPager(activityMainBinding.viewPager);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onPermissionsGranted(List<String> perms) {
        Log.e("MainActivity", "onPermissionsGranted " + perms);
        mainActivityViewModel.fetchContactsFromDevice();

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
                mainActivityViewModel.fetchContactsFromDevice();
            }
        }
    }
}
