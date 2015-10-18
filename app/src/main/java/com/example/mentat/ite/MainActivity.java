package com.example.mentat.ite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public void onClickMenu(View v){
       // v.getId();
       /*
        Switch - Case to catch onCliks of the menu, when the click is detected, onClickMenu catch the view clicked
        and in to the switch we take the id of then view.
        When the id enter in once case then we call the view (Layout) and load this.
       */
        switch (v.getId()) {
            case R.id.informacion:
                    Intent informacionView =  new Intent(getApplicationContext(), informacion.class);
                    startActivity(informacionView);
                break;
            case R.id.calificaciones:
                break;
            case R.id.horario:
                break;
            case R.id.feed:
                    Intent feedView =  new Intent(getApplicationContext(), feeds.class);
                    startActivity(feedView);
                break;
            case R.id.noticias:
                break;
            case R.id.mapa:
                break;
            case R.id.directorio:
                break;
            case R.id.actividades:
                break;

            default:
                break;
        }
    }
}
