package com.example.ghiyath.usage;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ghiyath on 5/16/2015.
 */
public class MessagesHandler {
    private Map<String, Contact> usageMap;
    private ArrayList<Contact> topRanked;
    private Cursor cursor;
    private Context context;

    public MessagesHandler( Context context) {

        //set globals
        this.context = context;

        //set cursor
        this.cursor = buildCursor("content://sms/inbox", new String[] {"_id", "address", "body" });

        refresh();
        //c = buildCursor("content://sms/outbox");
    }

    private Cursor buildCursor(String uri, String[] properties){
        Uri uriMade = Uri.parse(uri);
        ContentResolver cr = context.getApplicationContext().getContentResolver();
        return cr.query(uriMade, properties, null, null, null);
    }

    public void gatherMessages() {
        usageMap = new HashMap<String, Contact>();
        cursor = buildCursor("content://sms/inbox", new String[] {"_id", "address", "body" });

        //populate HashMap usageMap with <double phone(phone nuber), Contact(phone)>
        while (cursor.moveToNext()) {
            String phone = this.cursor.getString(this.cursor.getColumnIndex("address"));

            if (!usageMap.containsKey(phone))
                usageMap.put(phone, new Contact(String.valueOf(phone)));

            Contact updatedContact = usageMap.get(phone);
            updatedContact.incrementsmsReceived();
            usageMap.put(phone, updatedContact);
        }
    }

    public void rankMessages( ) {
        topRanked = new ArrayList<Contact>();

        for(String phoneNum : usageMap.keySet()) {
            int i = 0;
            for(; i < topRanked.size() && !usageMap.get(phoneNum).compareSMS(topRanked.get(i)); i++) {
            }
            topRanked.add(i, usageMap.get(phoneNum));
        }

    }


        //TEST
        /*

        */
        //sort and populate ArrayList topRanked with Contact objects

    public void refresh () {
        gatherMessages();
        rankMessages();
    }



    public Map<String, Contact> getUsage() {
        return usageMap;
    }

    public ArrayList<Contact> getTopRanked(int ranks) {

        return topRanked;
    }




}