package com.example.mentat.ite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by guillenvd on 16/10/2015.
 */
public class informacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion);
        ActionBar actionBar = getSupportActionBar(); //  getActionBar();
        getSupportActionBar().setTitle("Instituto Tecnológico"); // set the top title
        getSupportActionBar().setSubtitle("Informacióooon");
      /* actionBar.hide();  or even hide the actionbar
        */



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.back_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent =  new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        return true;
    }

}
