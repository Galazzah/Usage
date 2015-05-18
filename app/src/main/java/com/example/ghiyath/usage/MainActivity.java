package com.example.ghiyath.usage;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity implements View.OnClickListener {
    public HashMap<String, Contact> usageMap = new HashMap<String, Contact>();
    public ArrayList<Contact> topRanked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);
    }


        /*
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, TextRankActivity.class);
                startActivity(intent);
            }

            */



    @Override
    public void onClick(View v) {

            Uri inboxUri = Uri.parse("content://sms/inbox");

            String[] columns = new String[] { "_id", "address", "body" };

            ContentResolver cr = getContentResolver();

            Cursor c = cr.query(inboxUri, columns, null, null, null);


            c.moveToFirst();

            //Test instance of Contact object
            Contact test = new Contact();
            //Query Cursor c for phoneNum of test
            test.setPhoneNum(c.getString(c.getColumnIndex("address")));
            test.incrementsmsRecieved();
            //add entry to usageMap hashMap
            usageMap.put(test.getPhoneNum(), test);

            //entries in the hashMap
            Log.v("testing hashMap", usageMap.toString());
            //TODO: Populate hashMap with all items from cursor C and sort by (data)? and display in listView?



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
