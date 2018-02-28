package com.example.twigzcontacts.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.twigzcontacts.R;
import com.example.twigzcontacts.adapters.MessageRecyclerAdapter;
import com.example.twigzcontacts.models.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darush on 2/25/18.
 */

public class MessagesFragment extends Fragment {

    private RecyclerView mRecyclerMessages;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;



    public MessagesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View messagesView = inflater.inflate(R.layout.fragment_messages, container, false);

        mRecyclerMessages = messagesView.findViewById(R.id.list_messages);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerMessages.setLayoutManager(mLayoutManager);

        mAdapter = new MessageRecyclerAdapter(getMessagesList());
        mRecyclerMessages.setAdapter(mAdapter);

        return messagesView;
    }

    public ArrayList<Message> getMessagesList() {

        ArrayList<Message> messageList = new ArrayList<>();
        for(int i=0; i<5; i++) {
            Message message = new Message("John " + i, "2018-02-28 19:23:4" + i, "12345" + i);
            messageList.add(message);
        }

        return messageList;
    }
}
