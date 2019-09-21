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
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.databinding.FragmentContactsBinding;
import com.example.testapp.views.room.Person;
import com.example.testapp.views.utils.CommonUtils;
import com.example.testapp.views.utils.Constants;
import com.example.testapp.views.views.activities.MainActivity;
import com.example.testapp.views.views.adapters.ContactsAdapter;

import java.util.List;


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
        registerObserverForContactLiveData();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        Log.e("ContactAdapter",  "visibility : " +menuVisible);

        if (menuVisible) {
            registerDeletedLiveData();
            MainActivity.mainActivityViewModel.getAllPersonsFromDatabase();
        }else {
            MainActivity.mainActivityViewModel.getDeleted().removeObservers(this);
        }

    }

    private void registerObserverForContactLiveData() {

        MainActivity.mainActivityViewModel.getContactPersonsList().observe(this, personsList -> {

            Log.e("ContactAdapter", personsList.hashCode() + "  " + personsList.size());
            if (contactsAdapter != null) {
                contactsAdapter.notifyDataSetChanged();
            } else {
                contactsAdapter = new ContactsAdapter(getActivity(), personsList, MainActivity.mainActivityViewModel, Constants.CONTACT_STATUS.CONTACT);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                fragmentContactsBinding.recyclerView.setLayoutManager(mLayoutManager);
                fragmentContactsBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
                fragmentContactsBinding.recyclerView.setAdapter(contactsAdapter);
            }
        });



    }


    private void registerDeletedLiveData(){
        MainActivity.mainActivityViewModel.getDeleted().observe(this, person -> {
            CommonUtils.deleteAlertDialog(getContext(), person, MainActivity.mainActivityViewModel.getContactPersonsList());

        });
    }
}
