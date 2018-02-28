package com.example.twigzcontacts.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.twigzcontacts.R;
import com.example.twigzcontacts.activities.ContactInfoActivity;
import com.example.twigzcontacts.models.Contact;

import java.util.ArrayList;

import static com.example.twigzcontacts.utils.Constants.EXTRA_NAME;
import static com.example.twigzcontacts.utils.Constants.EXTRA_PHONE_NUMBER;

/**
 * Created by darush on 2/26/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Contact> mContactList;

    public RecyclerViewAdapter(ArrayList contactList) {
        mContactList = contactList;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final String firstName = mContactList.get(position).getFirstName();
        final String lastName = mContactList.get(position).getLastName();

        holder.mTextContactName.setText(firstName + " " + lastName);
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ContactInfoActivity.class);
                intent.putExtra(EXTRA_NAME, firstName + " " + lastName);
                intent.putExtra(EXTRA_PHONE_NUMBER, mContactList.get(position).getPhoneNumber());
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return mContactList.size();

    }

    /**
     * Custom ViewHolder that describes an item in our recycler view
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextContactName;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextContactName = itemView.findViewById(R.id.text_contact_name);

        }

    }

}
