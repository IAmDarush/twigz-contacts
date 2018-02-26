package com.example.twigzcontacts.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twigzcontacts.R;
import com.example.twigzcontacts.adapters.RecyclerViewAdapter;

/**
 * Created by darush on 2/25/18.
 */

public class ContactsFragment extends Fragment {

    private RecyclerView mRecyclerContacts;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private String[] contactsList = {"John Doe", "Waylon Dalton", "Marcus Cruz", "Thalia Cobb"};

    public ContactsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View contactsView = inflater.inflate(R.layout.fragment_contacts, container, false);

        mRecyclerContacts = contactsView.findViewById(R.id.list_contacts);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerContacts.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerViewAdapter(contactsList);
        mRecyclerContacts.setAdapter(mAdapter);

        return contactsView;
    }
}
