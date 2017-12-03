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

public class FormActivityUDR extends AppCompatActivity {

    private Button Update;
    private Button Retrieve;
    private Button Delete;
    private Button Back;
    private EditText EnrollmentID;
    private EditText Name;
    private EditText Phone;
    private static final String URL = "http://hostname/directoy_name/student_control.php";
    private StringRequest request;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_udr);
        EnrollmentID = (EditText) findViewById(R.id.eID);
        Name = (EditText) findViewById(R.id.name);
        Phone = (EditText) findViewById(R.id.phone);
        Update = (Button) findViewById(R.id.updateBtn);
        Retrieve = (Button) findViewById(R.id.retrieveBtn);
        Delete = (Button) findViewById(R.id.deleteBtn);
        Back = (Button) findViewById(R.id.backBtn);

        requestQueue = Volley.newRequestQueue(this);


        Delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //see what params user has selected
                if(EnrollmentID.getText().toString().length() > 0) //i.e. user has entered something here
                {
                    request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.i("MyMessage", "Response received");
                                //Log.i("MyMessage", response.substring(0, 5));
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.names().get(0).equals("success")) {
                                    Toast.makeText(FormActivityUDR.this, "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(FormActivityUDR.this,SecondActivity.class));
                                    Log.i("MyMessage", "Successful");
                                } else {
                                    Toast.makeText(FormActivityUDR.this, "Error: " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                    Log.i("MyMessage", "Failure");
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
                            hashMap.put("enrollmentid", EnrollmentID.getText().toString());
                            hashMap.put("operation", "Delete");

                            //Log.i("MyMessage","Name and Password are"+Name.getText().toString()+Password.getText().toString());
                            return hashMap;
                        }

                    };
                    requestQueue.add(request);
                }
                else if(Name.getText().toString().length()>0 && Phone.getText().toString().length()>0)
                {
                    request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.i("MyMessage", "Response received");
                                //Log.i("MyMessage", response.substring(0, 5));
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.names().get(0).equals("success")) {
                                    Toast.makeText(FormActivityUDR.this, "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(FormActivityUDR.this,SecondActivity.class));
                                    Log.i("MyMessage", "Successful");
                                } else {
                                    Toast.makeText(FormActivityUDR.this, "Error: " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                    Log.i("MyMessage", "Failure");
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
                            hashMap.put("name", Name.getText().toString());
                            hashMap.put("phone",Phone.getText().toString());
                            hashMap.put("operation", "Delete");

                            //Log.i("MyMessage","Name and Password are"+Name.getText().toString()+Password.getText().toString());
                            return hashMap;
                        }

                    };
                    requestQueue.add(request);
                }
            }
        });

        Update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(EnrollmentID.getText().toString().length() > 0)
                {
                    Intent intent = new Intent(getBaseContext(), UpdateForm.class);
                    intent.putExtra("enrollmentID", EnrollmentID.getText().toString());
                    intent.putExtra("fieldIndicator", "1"); //indicates that update is to be done using enrollment id field
                    startActivity(intent);
                }
                else if(Name.getText().toString().length()>0 && Phone.getText().toString().length()>0)
                {
                    Intent intent = new Intent(getBaseContext(), UpdateForm.class);
                    intent.putExtra("name", Name.getText().toString());
                    intent.putExtra("phone", Phone.getText().toString());
                    intent.putExtra("fieldIndicator", "2");
                    startActivity(intent);
                }
            }
        });

        Retrieve.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(EnrollmentID.getText().toString().length() > 0) //i.e. user has entered something here
                {
                    request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.i("MyMessage", "Response received");
                                //Log.i("MyMessage", response.substring(0, 5));
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.names().get(0).equals("success")) {
                                    Toast.makeText(FormActivityUDR.this, "Successfully Retrieved", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(FormActivityUDR.this,RetrieveInfoDisplay.class);
                                    intent.putExtra("studentInfo",jsonObject.getString("success") );
                                    startActivity(intent);
                                    Log.i("MyMessage", "Successful");
                                } else {
                                    Toast.makeText(FormActivityUDR.this, "Error in Retrieval", Toast.LENGTH_SHORT).show();
                                    Log.i("MyMessage", "Failure");
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
                            hashMap.put("enrollmentid", EnrollmentID.getText().toString());

                            hashMap.put("operation", "Retrieve");

                            //Log.i("MyMessage","Name and Password are"+Name.getText().toString()+Password.getText().toString());
                            return hashMap;
                        }

                    };
                    requestQueue.add(request);
                }
                else if(Name.getText().toString().length()>0 && Phone.getText().toString().length()>0)
                {
                    request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.i("MyMessage", "Response received");
                                //Log.i("MyMessage", response.substring(0, 5));
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.names().get(0).equals("success")) {
                                    Toast.makeText(FormActivityUDR.this, "Successfully Retrieved", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(FormActivityUDR.this,RetrieveInfoDisplay.class);
                                    intent.putExtra("studentInfo",jsonObject.getString("success") );
                                    startActivity(intent);
                                    Log.i("MyMessage", "Successful");
                                } else {
                                    Toast.makeText(FormActivityUDR.this, "Error in Retrieval", Toast.LENGTH_SHORT).show();
                                    Log.i("MyMessage", "Failure");
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
                            hashMap.put("name", Name.getText().toString());
                            hashMap.put("phone",Phone.getText().toString());
                            hashMap.put("operation", "Retrieve");

                            //Log.i("MyMessage","Name and Password are"+Name.getText().toString()+Password.getText().toString());
                            return hashMap;
                        }

                    };
                    requestQueue.add(request);
                }
            }
        });

        Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FormActivityUDR.this,SecondActivity.class));
            }
        });
    }
}
