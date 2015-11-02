package com.example.mentat.ite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Directory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directory_activity);

        // Enable back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = (ImageView) findViewById(R.id.contact_picture);

        String uri = "https://scontent-dfw1-1.xx.fbcdn.net/hphotos-xap1/v/t1.0-9/p720x720" +
                "/10523739_926868507341941_5737060999669530960_n.jpg?" +
                "oh=d68fed20178af2da6a92f61e6c42d8cc&oe=56D240C3";

        Picasso.with(this).load(uri).into(imageView);
    }
}
