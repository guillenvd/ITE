package com.example.mentat.ite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Administrator on 31/10/2015.
 */
public class calificaciones extends AppCompatActivity {
    private static final String TAG = feeds.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calificaciones);
        getSupportActionBar().setTitle("Instituto Tecnológico"); // set the top title
        getSupportActionBar().setSubtitle("Calificaciones");
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
