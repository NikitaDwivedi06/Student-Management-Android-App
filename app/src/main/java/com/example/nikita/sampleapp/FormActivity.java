package com.example.nikita.sampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity {

    private EditText EnrollmentID;
    private EditText Name;
    private EditText Course;
    private EditText Phone;
    private EditText Email;
    private EditText Address;
    private EditText Gender;
    private EditText DOB;
    private Button Clear;
    private Button Submit;
    private static final String URL = "http://10.0.2.2:80/dir1/student_insert.php";
    private StringRequest request;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        EnrollmentID = (EditText) findViewById(R.id.eID);
        Name = (EditText) findViewById(R.id.name);
        Course = (EditText) findViewById(R.id.course);
        Phone = (EditText) findViewById(R.id.phone);
        Email = (EditText) findViewById(R.id.email);
        Address = (EditText) findViewById(R.id.address);
        Gender = (EditText) findViewById(R.id.gender);
        DOB = (EditText) findViewById(R.id.dob);
       /// Clear = (Button) findViewById(R.id.clearBtn);
        Submit = (Button) findViewById(R.id.submitBtn);

        requestQueue = Volley.newRequestQueue(this);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("MyMessage", "Response received");
                            Log.i("MyMessage", response.substring(0, 5));
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {
                                Toast.makeText(FormActivity.this, "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(getApplicationContext(),SecondActivity.class));
                                startActivity(new Intent(FormActivity.this, SecondActivity.class));
                                Log.i("MyMessage", "Successful");
                            } else {
                                Toast.makeText(FormActivity.this, "Error: " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                Log.i("MyMessage", "Failure");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("MyMessage", "JSON Exception");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("MyMessage", "Erroneous Response");
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("enrollmentid", EnrollmentID.getText().toString());
                        hashMap.put("name", Name.getText().toString());
                        hashMap.put("course", Course.getText().toString());
                        hashMap.put("phone", Phone.getText().toString());
                        hashMap.put("email", Email.getText().toString());
                        hashMap.put("address", Address.getText().toString());
                        hashMap.put("gender", Gender.getText().toString());
                        hashMap.put("dob", DOB.getText().toString());
                        hashMap.put("insert_operation","Yes");
                        //Log.i("MyMessage","Name and Password are"+Name.getText().toString()+Password.getText().toString());
                        return hashMap;
                    }

                };
                requestQueue.add(request);
            }
        });

    }
}

