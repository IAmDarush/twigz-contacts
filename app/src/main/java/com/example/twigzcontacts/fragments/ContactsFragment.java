package com.example.twigzcontacts.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twigzcontacts.R;
import com.example.twigzcontacts.adapters.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by darush on 2/25/18.
 */

public class ContactsFragment extends Fragment {

    private RecyclerView mRecyclerContacts;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private String[] contactsList = {"John Doe", "Waylon Dalton", "Marcus Cruz", "Thalia Cobb"};
    public static final String TAG = "mytag";

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

        mAdapter = new RecyclerViewAdapter(getArrayOfContacts());
        mRecyclerContacts.setAdapter(mAdapter);

        return contactsView;
    }

    public String loadJsonFromAsset() {
        String json = null;

        try {
            InputStream input = getActivity().getAssets().open("contacts.json");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }

    public ArrayList getArrayOfContacts() {

        ArrayList<HashMap<String, String>> contactsArray = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(loadJsonFromAsset());

            HashMap<String, String> contactsMap;

            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject contact = jsonArray.getJSONObject(i);

                /*Log.d(TAG, "first name: " + contact.getString("firstName"));
                Log.d(TAG, "last name: " + contact.getString("lastName"));
                Log.d(TAG, "phone numbber: " + contact.getString("phoneNumber"));*/

                contactsMap = new HashMap<>();
                contactsMap.put("firstName", contact.getString("firstName"));
                contactsMap.put("lastName", contact.getString("lastName"));
                contactsMap.put("phoneNumber", contact.getString("phoneNumber"));

                contactsArray.add(contactsMap);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return contactsArray;
    }

}
