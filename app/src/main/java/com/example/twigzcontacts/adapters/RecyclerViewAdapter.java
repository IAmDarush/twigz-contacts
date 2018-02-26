package com.example.twigzcontacts.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twigzcontacts.R;
import com.example.twigzcontacts.activities.ContactInfoActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by darush on 2/26/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<HashMap<String, String>> mContactData;

    public RecyclerViewAdapter(ArrayList contactData) {
        mContactData = contactData;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final String firstName = mContactData.get(position).get("firstName");
        final String lastName = mContactData.get(position).get("lastName");

        holder.mTextContactName.setText(firstName + " " + lastName);
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ContactInfoActivity.class);
                intent.putExtra("name", firstName + " " + lastName);
                intent.putExtra("phoneNumber", mContactData.get(position).get("phoneNumber"));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return mContactData.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextContactName;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextContactName = itemView.findViewById(R.id.text_contact_name);

        }

    }

}
