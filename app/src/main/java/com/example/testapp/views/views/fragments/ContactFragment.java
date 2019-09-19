package com.example.testapp.views.views.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.testapp.R;
import com.example.testapp.databinding.FragmentContactsBinding;
import com.example.testapp.views.room.Person;
import com.example.testapp.views.views.activities.MainActivity;
import com.example.testapp.views.views.adapters.ContactsAdapter;

import java.util.ArrayList;

public class ContactFragment extends Fragment {

    private FragmentContactsBinding fragmentContactsBinding;

    private ContactsAdapter contactsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         fragmentContactsBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_contacts, container, false);
        return fragmentContactsBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerObserverForLiveData();

        init();



    }

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MainActivity.mainActivityViewModel.getAllPersonsFromDatabase();
            }
        }).start();
    }

    private void registerObserverForLiveData() {

        MainActivity.mainActivityViewModel.getPersonsList().observe(this,personsList->{

            Log.e("MainActivity","size "+ personsList.size());
            if (contactsAdapter==null){
                contactsAdapter=new ContactsAdapter(getActivity(),personsList);
                fragmentContactsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                fragmentContactsBinding.recyclerView.setAdapter(contactsAdapter);
            }else {
                contactsAdapter.notifyDataSetChanged();
            }
        });
    }
}
