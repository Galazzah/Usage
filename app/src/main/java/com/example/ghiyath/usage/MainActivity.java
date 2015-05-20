package com.example.ghiyath.usage;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity implements View.OnClickListener {
    //Changed phone type to Double in HashMap
    public Map<Double, Contact> usageMap = new HashMap<Double, Contact>();
    public ArrayList<Contact> topRanked = new ArrayList<Contact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

            Uri inboxUri = Uri.parse("content://sms/inbox");

            String[] columns = new String[] { "_id", "address", "body" };

            ContentResolver cr = getContentResolver();

            Cursor c = cr.query(inboxUri, columns, null, null, null);

            //populate HashMap usageMap with <double phone(phone nuber), Contact(phone)>
            while(c.moveToNext()) {

                double phone = Double.parseDouble(c.getString(c.getColumnIndex("address")));
                if(!usageMap.containsKey(phone))
                    usageMap.put(phone, new Contact(String.valueOf(phone)));

                Contact updatedContact = usageMap.get(phone);
                updatedContact.incrementsmsRecieved();
                usageMap.put(phone, updatedContact);
            }

            //sort and populate ArrayList topRanked with Contact objects
            for(double phoneNum : usageMap.keySet()) {
                int i = 0;
                for(; i < topRanked.size() && !usageMap.get(phoneNum).compareSMS(topRanked.get(i)); i++) {
                }
                topRanked.add(i, usageMap.get(phoneNum));
            }

            //ArrayAdapter to populate listView with entries from topRanked ArrayList
            ArrayAdapter<Contact> test = new ArrayAdapter<Contact>(this,android.R.layout.simple_list_item_1, topRanked);
            ListView lv = (ListView) findViewById(R.id.lvMsg);
            lv.setAdapter(test);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
