package com.example.mentat.ite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Administrator on 17/10/2015.
 */
public class feeds extends AppCompatActivity {
    private static final String TAG = feeds.class.getSimpleName();

    ListView list;
    String[] itemname ={
            "Tirado",
            "Christian",
            "Sanchez",
            "Lourdez",
            "Padilla",
            "Rogelio",
            "Cosio",
            "Nodal"
    };

    Integer[] imgid={
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.feeds);
        ActionBar actionBar = getSupportActionBar(); //  getActionBar();
        getSupportActionBar().setTitle("Instituto Tecnológico"); // set the top title
        getSupportActionBar().setSubtitle("FEEDS");
        /*
            Generate the list of the feeds for the teachers
         * [CustomListAdapter Row array in list feed layout]
         * @param  {View} this [the current view]
         * @param  {Array} itemname [array of items (teachers)]
         * @param  {Array} imgid [array of imgid, the names of the images]
         * @return {layout}          []
         */
        CustomListAdapter adapter= new CustomListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        /**
            Set listener to each items of the list on feed
         **/
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Slecteditem= itemname[+position];
                /*
                    Intent intent = new Intent(this, FeedText.class);
                    intent.putExtra("FEED_ID", feedId);
                    startActivity(intent)
                */
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
            }
        });
        Log.d(TAG, "Feeds Option is loaded");

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.back_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        this.finish();
        return true;
    }

}