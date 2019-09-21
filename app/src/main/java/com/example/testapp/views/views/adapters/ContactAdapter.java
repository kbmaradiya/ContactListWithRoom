package com.example.testapp.views.views.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.views.room.Person;
import com.example.testapp.views.utils.Constants;
import com.example.testapp.views.viewModels.MainActivityViewModel;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Activity activity;
    private List<Person> personList;
    private MainActivityViewModel mainActivityViewModel;
    private Constants.CONTACT_STATUS status;

    public ContactAdapter(FragmentActivity activity, List<Person> personsList, MainActivityViewModel mainActivityViewModel, Constants.CONTACT_STATUS status) {
        this.activity = activity;
        this.personList = personsList;
        this.mainActivityViewModel = mainActivityViewModel;
        this.status = status;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.contact_adapter, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {

        holder.txtName.setText(""+personList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return personList == null ? 0 : personList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);

            setFavAndDeleteVisisbility(itemView);

            onClicks(itemView);
        }

        private void onClicks(View itemView) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        private void setFavAndDeleteVisisbility(View itemView) {
            switch (status) {
                case FAVOURITE:
//                    itemView.imgDelete.setVisibility(View.GONE);
                    break;

                case DELETED:
//                    contactAdapterBinding.imgDelete.setVisibility(View.GONE);
//                    contactAdapterBinding.imgFavourite.setVisibility(View.GONE);
                    break;
            }
        }
    }
}
