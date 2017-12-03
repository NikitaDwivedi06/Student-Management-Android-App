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

public class UpdateForm extends AppCompatActivity {

    private EditText Email;
    private EditText Address;
    private Button Update;
    private static final String URL = "http://hostname/directoy_name/student_control.php";
    private StringRequest request;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_form);
        Email = (EditText) findViewById(R.id.email);
        Address = (EditText) findViewById(R.id.address);
        Update  = (Button) findViewById(R.id.updateBtn);
        //final String enrollmentID = getIntent().getStringExtra("enrollmentID");
        final String field = getIntent().getStringExtra("fieldIndicator");
        requestQueue = Volley.newRequestQueue(this);

        Update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("MyMessage", "Response received");
                            //Log.i("MyMessage", response.substring(0, 5));
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {
                                Toast.makeText(UpdateForm.this, "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();

                                Log.i("MyMessage", "Successful");
                                startActivity(new Intent(UpdateForm.this,SecondActivity.class));
                            } else {
                                Toast.makeText(UpdateForm.this, "Error: " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                Log.i("MyMessage", "Failure");
                                startActivity(new Intent(UpdateForm.this,FormActivityUDR.class));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("MyMessage", "JSON Exception");
                            Log.i("MyMessage", response.substring(0, 5));
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
                        if(field.equals("1"))
                        {
                            hashMap.put("enrollmentid", getIntent().getStringExtra("enrollmentID"));
                        }
                        else if(field.equals("2"))
                        {
                            hashMap.put("name", getIntent().getStringExtra("name"));
                            hashMap.put("phone", getIntent().getStringExtra("phone"));
                        }
                        hashMap.put("email", Email.getText().toString());
                        hashMap.put("address", Address.getText().toString());
                        hashMap.put("update_operation", "Update");

                        //Log.i("MyMessage","Name and Password are"+Name.getText().toString()+Password.getText().toString());
                        return hashMap;
                    }

                };
                requestQueue.add(request);
            }
        });
    }
}
