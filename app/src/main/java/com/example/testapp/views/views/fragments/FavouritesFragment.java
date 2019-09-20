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
import com.example.testapp.views.utils.Constants;
import com.example.testapp.views.views.activities.MainActivity;
import com.example.testapp.views.views.adapters.ContactsAdapter;

public class FavouritesFragment extends Fragment {

    private FragmentContactsBinding fragmentContactsBinding;

    private ContactsAdapter contactsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentContactsBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_contacts, container, false);
        Log.e("FavouritesFragment","onCreateView");

        return fragmentContactsBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerObserverForLiveData();


    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e("FavouritesFragment","onResume");
        MainActivity.mainActivityViewModel.getFavouritePersonsFromDatabase(Constants.CONTACT_STATUS.FAVOURITE.getStatus());

    }


    private void registerObserverForLiveData() {

        MainActivity.mainActivityViewModel.getFavouritePersonsList().observe(this, personsList -> {

            Log.e("FavouritesFragment", "size " + personsList.size());
            if (contactsAdapter == null) {
                contactsAdapter = new ContactsAdapter(getActivity(), personsList, MainActivity.mainActivityViewModel, Constants.CONTACT_STATUS.FAVOURITE);
                fragmentContactsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                fragmentContactsBinding.recyclerView.setAdapter(contactsAdapter);
            } else {
                contactsAdapter.notifyDataSetChanged();
            }
        });


    }
}
