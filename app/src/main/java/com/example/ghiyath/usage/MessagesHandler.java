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

    public MessagesHandler( Map<String, Contact> usageMap, ArrayList<Contact> topRanked, Context context) {
        this.usageMap = usageMap;
        this.topRanked = topRanked;

        Uri inboxUri = Uri.parse("content://sms/inbox");
        String[] columns = new String[] { "_id", "address", "body" };
        ContentResolver cr = context.getApplicationContext().getContentResolver();
        Cursor c = cr.query(inboxUri, columns, null, null, null);
        //populate HashMap usageMap with <double phone(phone nuber), Contact(phone)>
        while(c.moveToNext()) {
            String phone = c.getString(c.getColumnIndex("address"));
            Log.v("poop", phone);
            if(!usageMap.containsKey(phone))
                usageMap.put(phone, new Contact(String.valueOf(phone)));

            Contact updatedContact = usageMap.get(phone);
            updatedContact.incrementsmsReceived();
            usageMap.put(phone, updatedContact);
        }
        //TEST

        Contact testCon = new Contact("14083870968");
        testCon.incrementsmsReceived();
        testCon.incrementsmsReceived();
        usageMap.put("14083870968", testCon);

        Contact testCon2 = new Contact("14083870867");
        testCon2.incrementsmsReceived();
        testCon2.incrementsmsReceived();
        testCon2.incrementsmsReceived();
        usageMap.put("14083870867", testCon2);

        //sort and populate ArrayList topRanked with Contact objects
        for(String phoneNum : usageMap.keySet()) {
            int i = 0;
            for(; i < topRanked.size() && !usageMap.get(phoneNum).compareSMS(topRanked.get(i)); i++) {
            }
            topRanked.add(i, usageMap.get(phoneNum));
        }


    }

    public Map<String, Contact> getUsage() {
        return usageMap;
    }

    public ArrayList<Contact> getTopRanked(int ranks) {

        return topRanked;
    }




}