package com.example.twigzcontacts.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.twigzcontacts.R;
import com.example.twigzcontacts.db.DatabaseHandler;
import com.example.twigzcontacts.models.Message;
import com.example.twigzcontacts.utils.Constants;
import com.example.twigzcontacts.utils.Utils;
import com.example.twigzcontacts.webservice.TwilioClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewMessageActivity extends AppCompatActivity {

    EditText field_message;
    Button button_send;
    String message;
    String phoneNumber;
    String name;
    int otp;
    public static final String BASE_URL = "https://api.twilio.com";
    public static final String TAG = "mytag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        otp = Utils.getRandomOtp();
        message = "Hi. Your OTP is: " + otp;

        if (getIntent().hasExtra(Constants.EXTRA_PHONE_NUMBER)) {
            phoneNumber = getIntent().getStringExtra(Constants.EXTRA_PHONE_NUMBER);
            name = getIntent().getStringExtra(Constants.EXTRA_NAME);
        }

        field_message = (EditText) findViewById(R.id.field_newmessage_message);
        field_message.setText(message);

        button_send = (Button) findViewById(R.id.button_newmessage_send);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendMessage(message, phoneNumber);
                //Toast.makeText(NewMessageActivity.this, "Sending...", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void sendMessage(String message, String phoneNumber) {

        String body = message;
        String to = phoneNumber;
        String from = "+14243533758";
        String AUTH_TOKEN = getResources().getString(R.string.twilio_auth_token);
        String ACCOUNT_SID = getResources().getString(R.string.twilio_account_sid);

        String base64EncodedCredentials = "Basic " + Base64.encodeToString(
                (ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(), Base64.NO_WRAP
        );

        Map<String, String> data = new HashMap<>();
        data.put("From", from);
        data.put("To", to);
        data.put("Body", body);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TwilioClient twilioClient = retrofit.create(TwilioClient.class);
        Call call = twilioClient.sendMessage(ACCOUNT_SID, base64EncodedCredentials, data);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    //Log.d(TAG, "onResponse: Successful");
                    try {
                        String responseBody = response.body().string();
                        JsonParser jsonParser = new JsonParser();
                        JsonObject jsonObject = (JsonObject) jsonParser.parse(responseBody);
                        String time = jsonObject.get("date_created").getAsString();
                        Message message1 = new Message();
                        message1.setName(name);
                        message1.setTime(time);
                        message1.setOtp(String.valueOf(otp));

                        DatabaseHandler db = new DatabaseHandler(NewMessageActivity.this);
                        db.addMessage(message1);

                        Log.d(TAG, response.body().string());

                        Toast.makeText(NewMessageActivity.this, "Message sent successfully...", Toast.LENGTH_SHORT).show();

                        goToMainActivity();

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(NewMessageActivity.this, "Failed to send the message...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NewMessageActivity.this, "Error! Response was not successful", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, response.toString());
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void goToMainActivity() {
        Intent intent = new Intent(NewMessageActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
