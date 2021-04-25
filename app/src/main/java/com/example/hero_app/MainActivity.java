package com.example.hero_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    Internet_status internet_status;
    ImageView refresh_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb=findViewById(R.id.pb);
        internet_status=new Internet_status(this);
        refresh_icon = findViewById(R.id.refresh_icon);

        try {
            refresh_icon.setVisibility(View.GONE);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    operation();
                }
            }, 2000);

            refresh_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refresh_icon.setVisibility(View.GONE);
                    pb.setVisibility(View.VISIBLE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            operation();

                        }
                    }, 2000);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void operation(){

        try {
            if(internet_status.internet_status()){
                Intent i1=new Intent(MainActivity.this,HomeScreen.class);
                startActivity(i1);
            }
            else{
                Toast.makeText(MainActivity.this, "Check Internet Connectivity", Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.GONE);
                refresh_icon.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}