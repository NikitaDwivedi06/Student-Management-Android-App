package com.example.nikita.sampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RetrieveInfoDisplay extends AppCompatActivity {

    private TextView tv;
    private Button Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_info_display);
        tv = (TextView) findViewById(R.id.textView);
        String studentInfo = getIntent().getStringExtra("studentInfo");
        String info[] = studentInfo.split("\n");
        String completeInfo="";
        String fieldnames[] = {"EnrollmentID: ","Name: ","Course: ","Phone: ","Email: ","Address: ","Gender: ","DOB: "};
        for(int i=0;i<info.length;i++)
        {
            completeInfo = completeInfo +"\n"+ fieldnames[i]+info[i];
        }
        tv.setText(completeInfo);
        Back = (Button) findViewById(R.id.backBtn);

        Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RetrieveInfoDisplay.this,SecondActivity.class));
            }
        });
    }
}
