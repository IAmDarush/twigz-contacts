package com.example.twigzcontacts.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.twigzcontacts.models.Message;

import java.util.ArrayList;

/**
 * Created by darush on 2/28/18.
 */

public class MessageRecyclerAdapter extends RecyclerView.Adapter{

    private ArrayList<Message> mMessageList;

    public MessageRecyclerAdapter(ArrayList<Message> messageList) {
        mMessageList = messageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }


}
