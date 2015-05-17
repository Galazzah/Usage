package com.example.ghiyath.usage;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by z on 5/16/2015.
 */
public class TextRankActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_ranks);

        Uri inboxUri = Uri.parse("content://sms/inbox");
        String[] columns = new String[] { "_id", "address", "body" };
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(inboxUri, columns, null, null, null);

        c.moveToFirst();
        while(c.moveToNext()) {
            Log.v("id", c.getString(c.getColumnIndex("_id")));
        }

        //MessagesHandler smsHandler = new MessagesHandler();
    }



}
