package com.example.ghiyath.usage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity implements View.OnClickListener {
    //Changed phone type to Double in HashMap
    private Map<String, Contact> usageMap = new HashMap<String, Contact>();
    private ArrayList<Contact> topRanked = new ArrayList<Contact>();
    private ListView lv;
    private ArrayAdapter<Contact> ranked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int amtRanked = 1;

        Button refresh = (Button) findViewById(R.id.refresh);
        final MessagesHandler m = new MessagesHandler(this);
        m.refresh();

        usageMap = m.getUsage();
        topRanked = m.getTopRanked(amtRanked);

        ranked = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, topRanked);
        lv = (ListView) findViewById(R.id.lvMsg);
        lv.setAdapter(ranked);

        final Context c = this;
        refresh.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        m.refresh();

                        usageMap = m.getUsage();
                        topRanked = m.getTopRanked(amtRanked);

                        ranked = new ArrayAdapter<Contact>(c, android.R.layout.simple_list_item_1, topRanked);
                        ListView lv = (ListView) findViewById(R.id.lvMsg);
                        lv.setAdapter(ranked);
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {






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
