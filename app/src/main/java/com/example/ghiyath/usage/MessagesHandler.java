package com.example.ghiyath.usage;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ghiyath on 5/16/2015.
 */
public class MessagesHandler extends Activity{
    private HashMap<String, Contact> usage;
    private ArrayList<Contact> topRanked;


    public MessagesHandler() {
        updateUsage();
    }

    public void updateUsage() {
        //Read through SMS to:
            //udpate usage
            //update topRanked
        /*
        Uri inboxUri = Uri.parse("content://sms/inbox");

        String[] columns = new String[] { "_id", "address", "body" };

        ContentResolver cr = getContentResolver();

        Cursor c = cr.query(inboxUri, columns, null, null, null);


        c.moveToFirst();
        while(c.moveToNext()) {
            Log.v("id", c.getString(c.getColumnIndex("_id")));

        } */

    }

    public HashMap<String, Contact> getUsage() {
        return usage;
    }

    public ArrayList<Contact> getTopRanked(int ranks) {

        return topRanked;
    }




}
