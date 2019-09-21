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

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    public static MainActivityViewModel mainActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        setupViewPager();

    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ContactFragment(), getString(R.string.contacts));
        adapter.addFragment(new FavouritesFragment(), getString(R.string.favourites));
        adapter.addFragment(new DeletedFragment(), getString(R.string.deleted));
        activityMainBinding.viewPager.setAdapter(adapter);
        activityMainBinding.viewPager.setOffscreenPageLimit(0);
        activityMainBinding.tabLayout.setupWithViewPager(activityMainBinding.viewPager);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

   }
