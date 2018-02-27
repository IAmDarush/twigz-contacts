package com.example.twigzcontacts.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twigzcontacts.R;

public class ContactInfoActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvPhoneNumber;
    Button btnSendMessage;
    String name;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (getIntent().hasExtra("name") && getIntent().hasExtra("phoneNumber")) {
            name = getIntent().getStringExtra("name");
            phoneNumber = getIntent().getStringExtra("phoneNumber");
        }

        tvName = (TextView) findViewById(R.id.text_contactinfo_name);
        tvName.setText(name);
        tvPhoneNumber = (TextView) findViewById(R.id.text_contactinfo_number);
        tvPhoneNumber.setText(phoneNumber);
        btnSendMessage = (Button) findViewById(R.id.button_contactinfo_send);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ContactInfoActivity.this, NewMessageActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);

                //Toast.makeText(ContactInfoActivity.this, name + " " + phoneNumber, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
