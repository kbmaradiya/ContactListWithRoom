package com.example.testapp.views.views.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.testapp.R;
import com.example.testapp.databinding.ContactAdapterBinding;
import com.example.testapp.views.room.Person;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private Activity activity;
    private List<Person> personList;

    public ContactsAdapter(FragmentActivity activity, List<Person> personsList) {
        this.activity = activity;
        this.personList = personsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ContactAdapterBinding contactAdapterBinding = ContactAdapterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(contactAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.contactAdapterBinding.setPerson(personList.get(position));

//        Glide.with(activity)
//                .load("http://pngimg.com/uploads/google/google_PNG19640.png")
//                .apply( new RequestOptions()
//                        .placeholder(R.drawable.ic_launcher_background)
//                        )
//                .into(holder.contactAdapterBinding.imgProfilePic);
    }

    @Override
    public int getItemCount() {
        return personList == null ? 0 : personList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         ContactAdapterBinding contactAdapterBinding;

        public ViewHolder(@NonNull ContactAdapterBinding contactAdapterBinding) {
            super(contactAdapterBinding.getRoot());
            this.contactAdapterBinding=contactAdapterBinding;
        }


    }
}
