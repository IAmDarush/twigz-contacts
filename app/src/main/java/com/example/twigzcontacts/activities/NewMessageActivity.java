package com.example.twigzcontacts.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.twigzcontacts.R;

public class NewMessageActivity extends AppCompatActivity {

    EditText field_message;
    Button button_send;
    String message;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        message = "Hi. Your OTP is: " + getRandomOtp();

        if(getIntent().hasExtra("phoneNumber")){
            phoneNumber = getIntent().getStringExtra("phoneNumber");
        }

        field_message = (EditText) findViewById(R.id.field_newmessage_message);
        field_message.setText(message);

        button_send = (Button) findViewById(R.id.button_newmessage_send);

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewMessageActivity.this, "Sending...", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public int getRandomOtp() {
        return 123456;
    }

}
