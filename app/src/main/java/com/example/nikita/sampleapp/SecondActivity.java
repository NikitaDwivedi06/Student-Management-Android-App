package com.example.nikita.sampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {


    private Button Insert;
    private Button Other;
    private Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Insert = (Button) findViewById(R.id.insertBtn);
        Other = (Button) findViewById(R.id.otherBtn);
        Logout = (Button) findViewById(R.id.logoutBtn);

        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,FormActivity.class));
            }
        });


        Other.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,FormActivityUDR.class));
            }
        });
        Logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
            }
        });
    }
}
