package com.example.twigzcontacts.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twigzcontacts.R;

import static com.example.twigzcontacts.utils.Constants.EXTRA_NAME;
import static com.example.twigzcontacts.utils.Constants.EXTRA_PHONE_NUMBER;

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


        if (getIntent().hasExtra(EXTRA_NAME) && getIntent().hasExtra(EXTRA_PHONE_NUMBER)) {
            name = getIntent().getStringExtra(EXTRA_NAME);
            phoneNumber = getIntent().getStringExtra(EXTRA_PHONE_NUMBER);
        }

        tvName = (TextView) findViewById(R.id.text_contactinfo_name);
        tvPhoneNumber = (TextView) findViewById(R.id.text_contactinfo_number);
        btnSendMessage = (Button) findViewById(R.id.button_contactinfo_send);

        tvName.setText(name);
        tvPhoneNumber.setText(phoneNumber);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!phoneNumber.isEmpty() && phoneNumber != null) {
                    Intent intent = new Intent(ContactInfoActivity.this, NewMessageActivity.class);
                    intent.putExtra(EXTRA_PHONE_NUMBER, phoneNumber);
                    intent.putExtra(EXTRA_NAME, name);
                    startActivity(intent);
                } else {
                    Toast.makeText(ContactInfoActivity.this, "No phone number...", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
