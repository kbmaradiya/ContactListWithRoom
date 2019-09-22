package com.example.testapp.views.views.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testapp.R;
import com.example.testapp.databinding.FragmentContactsBinding;
import com.example.testapp.views.utils.Constants;
import com.example.testapp.views.views.activities.MainActivity;
import com.example.testapp.views.views.adapters.ContactsAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class DeletedFragment extends Fragment {

    private FragmentContactsBinding fragmentContactsBinding;

    private ContactsAdapter contactsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentContactsBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_contacts, container, false);
        Log.e("DeletedFragment", "onCreateView");

        return fragmentContactsBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerObserverForLiveData();


    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        if (menuVisible) {
            MainActivity.mainActivityViewModel.getDeletedPersonsFromDatabase(Constants.CONTACT_STATUS.DELETED.getStatus());
        }
    }

    private void registerObserverForLiveData() {

        MainActivity.mainActivityViewModel.getDeletedPersonsList().observe(this, personsList -> {

            contactsAdapter = new ContactsAdapter(getActivity(), personsList, MainActivity.mainActivityViewModel, Constants.CONTACT_STATUS.DELETED);
            fragmentContactsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            fragmentContactsBinding.recyclerView.setAdapter(contactsAdapter);

        });


    }
}
