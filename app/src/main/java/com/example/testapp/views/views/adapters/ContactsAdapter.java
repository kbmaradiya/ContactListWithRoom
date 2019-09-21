package com.example.testapp.views.views.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.databinding.ContactAdapterBinding;
import com.example.testapp.views.room.Person;
import com.example.testapp.views.utils.Constants;
import com.example.testapp.views.viewModels.MainActivityViewModel;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private Activity activity;
    private List<Person> personList;
    private MainActivityViewModel mainActivityViewModel;
    private Constants.CONTACT_STATUS status;

    public ContactsAdapter(FragmentActivity activity, List<Person> personsList, MainActivityViewModel mainActivityViewModel, Constants.CONTACT_STATUS status) {
        this.activity = activity;
        this.personList = personsList;
        this.mainActivityViewModel = mainActivityViewModel;
        this.status = status;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ContactAdapterBinding contactAdapterBinding = ContactAdapterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        contactAdapterBinding.setMainActivityViewmodel(mainActivityViewModel);
        return new ViewHolder(contactAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.e("ContactFragment","ContactAdapter : "+position);
        holder.contactAdapterBinding.setPerson(personList.get(position));

        if (personList.get(position).getStatus().equals(Constants.CONTACT_STATUS.FAVOURITE.getStatus())){
            holder.contactAdapterBinding.imgFavourite.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_favorite_black_24dp));
        }else {
            holder.contactAdapterBinding.imgFavourite.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_favorite_border_black_24dp));
        }
    }

    @Override
    public int getItemCount() {
        return personList == null ? 0 : personList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ContactAdapterBinding contactAdapterBinding;

        public ViewHolder(@NonNull ContactAdapterBinding contactAdapterBinding) {
            super(contactAdapterBinding.getRoot());
            this.contactAdapterBinding = contactAdapterBinding;

            setFavAndDeleteVisisbility();

            onClicks();
        }

        private void onClicks() {
            contactAdapterBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        private void setFavAndDeleteVisisbility() {
            switch (status) {
                case FAVOURITE:
                    contactAdapterBinding.imgDelete.setVisibility(View.GONE);
                    break;

                case DELETED:
                    contactAdapterBinding.imgDelete.setVisibility(View.GONE);
                    contactAdapterBinding.imgFavourite.setVisibility(View.GONE);
                    break;
            }
        }


    }
}
