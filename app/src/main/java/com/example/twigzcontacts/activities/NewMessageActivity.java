package com.example.twigzcontacts.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import static com.example.twigzcontacts.utils.Constants.BASE_URL;
import static com.example.twigzcontacts.utils.Constants.FROM;
import static com.example.twigzcontacts.utils.Utils.getIndianTime;

public class NewMessageActivity extends AppCompatActivity {

    private EditText mFieldMessage;
    private Button mButtonSend;
    private String name;
    private String phoneNumber;
    private ProgressDialog progress;
    public static final String TAG = "mytag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra(Constants.EXTRA_PHONE_NUMBER)) {
            phoneNumber = getIntent().getStringExtra(Constants.EXTRA_PHONE_NUMBER);
            name = getIntent().getStringExtra(Constants.EXTRA_NAME);
        }

        final int otp = Utils.getRandomOtp();
        final String message = "Hi. Your OTP is: " + otp;

        mFieldMessage = (EditText) findViewById(R.id.field_newmessage_message);
        mFieldMessage.setText(message);

        mButtonSend = (Button) findViewById(R.id.button_newmessage_send);
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();
                sendMessage(name, message, phoneNumber, otp);
            }
        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void sendMessage(final String name, String message, String phoneNumber, final int otp) {

        final String body = message;
        String to = phoneNumber;
        String AUTH_TOKEN = getResources().getString(R.string.twilio_auth_token);
        String ACCOUNT_SID = getResources().getString(R.string.twilio_account_sid);
        String base64EncodedCredentials = Utils.getBase64EncodedCredentials(ACCOUNT_SID, AUTH_TOKEN);
        Map<String, String> data = composeMessage(FROM, to, body);

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
                    try {
                        // Retrieve the time from the response body
                        String time = getTimeFromBody(response.body().string());

                        // Construct a new message object with the specified data
                        Message message = new Message(name, getIndianTime(time), String.valueOf(otp));

                        // Add the message object into the databbase
                        DatabaseHandler db = new DatabaseHandler(NewMessageActivity.this);
                        db.addMessage(message);

                        Toast.makeText(NewMessageActivity.this, "Message sent successfully...", Toast.LENGTH_SHORT).show();
                        hideProgressDialog();
                        goToMainActivity();

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(NewMessageActivity.this, "Failed to send the message...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NewMessageActivity.this, "Error! Response was not successful", Toast.LENGTH_SHORT).show();
                    //Log.d(TAG, response.toString());
                }

                hideProgressDialog();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    /**
     * Parse the body string and return the message creation time
     *
     * @param body body of the HTTP response
     * @return message creation time
     */
    private String getTimeFromBody(String body) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(body);
        String time = jsonObject.get("date_created").getAsString();
        return time;
    }

    /**
     * Close the top activities from the activity stack and go back to MainActivity
     */
    private void goToMainActivity() {
        Intent intent = new Intent(NewMessageActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private Map<String, String> composeMessage(String from, String to, String body) {
        Map<String, String> data = new HashMap<>();
        data.put("From", from);
        data.put("To", to);
        data.put("Body", body);
        return data;
    }

    private void showProgressDialog() {
        progress = new ProgressDialog(this);
        progress.setTitle("Sending...");
        progress.setMessage("Please wait while sending the message.");
        progress.setCancelable(false);
        progress.show();
    }

    private void hideProgressDialog() {
        if(progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }

}
