package com.example.twigzcontacts.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.twigzcontacts.R;

/**
 * Created by darush on 2/26/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private String[] mContactData;

    public RecyclerViewAdapter(String[] contactData) {
        mContactData = contactData;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextContactName.setText(mContactData[position]);

    }

    @Override
    public int getItemCount() {

        return mContactData.length;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextContactName;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextContactName = itemView.findViewById(R.id.text_contact_name);

        }

    }

}
