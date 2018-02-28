package com.example.twigzcontacts.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.twigzcontacts.models.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darush on 2/28/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "twigzMessages";
    private static final String TABLE_MESSAGES = "messages";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TIME = "time";
    private static final String KEY_OTP = "otp";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_MESSAGES_TABLE = "CREATE TABLE " + TABLE_MESSAGES + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_TIME + " TEXT,"
                + KEY_OTP + " INTEGER"
                + ")";
        sqLiteDatabase.execSQL(CREATE_MESSAGES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);

        // Create the new table
        onCreate(sqLiteDatabase);
    }

    /**
     * Add a new message into the database
     * @param message the message object to be added into the database
     */
    public void addMessage(Message message) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, message.getName());
        values.put(KEY_TIME, message.getTime());
        values.put(KEY_OTP, Integer.valueOf(message.getOtp()));

        db.insert(TABLE_MESSAGES, null, values);

        db.close();
    }

    public Message getMessage(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(
                TABLE_MESSAGES,
                new String[]{KEY_NAME, KEY_TIME, KEY_OTP},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        Message message = new Message(
                cursor.getString(0),
                cursor.getString(1),
                Integer.toString(cursor.getInt(3))
        );

        return message;

    }

    /**
     * Get all the messages from the database
     * @return list of all the messages in the database
     */
    public List<Message> getAllMessages() {

        List<Message> messageList = new ArrayList<>();

        String selectQuery = "SELECT " + KEY_NAME + "," + KEY_TIME + "," + KEY_OTP
                + " FROM " + TABLE_MESSAGES + " ORDER BY " + KEY_TIME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                Message message = new Message();
                message.setName(cursor.getString(0));
                message.setTime(cursor.getString(1));
                message.setOtp(String.valueOf(cursor.getInt(2)));

                messageList.add(message);

            } while (cursor.moveToNext());
        }

        return messageList;
    }

}
