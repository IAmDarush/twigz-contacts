package com.example.twigzcontacts.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.twigzcontacts.R;

/**
 * Created by darush on 2/25/18.
 */

public class ContactsFragment extends Fragment {

    public ContactsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View contactsView = inflater.inflate(R.layout.fragment_contacts, container, false);
        TextView contactsText = contactsView.findViewById(R.id.text_contacts);

        return contactsView;
    }
}
