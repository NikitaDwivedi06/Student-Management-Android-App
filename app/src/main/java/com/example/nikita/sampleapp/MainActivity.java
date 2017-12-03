package com.example.nikita.sampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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


public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;

    private Button Login;
    private int loginAttempts = 3;

    private RequestQueue requestQueue;
    private static final String URL = "http://hostname/directory_name/user_control.php";
    private StringRequest request;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign variables to their corr fields in the layout
        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        //Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);

        requestQueue = Volley.newRequestQueue(this);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyMessage","User clicked login");
                //String n = Name.toString();
                //Log.i("MyMessage",Name.getText().toString());
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            Log.i("MyMessage","Response received");
                            Log.i("MyMessage",response.substring(0,5));
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){
                                Toast.makeText(MainActivity.this,"SUCCESS "+jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(getApplicationContext(),SecondActivity.class));
                                startActivity(new Intent(MainActivity.this,SecondActivity.class));
                                Log.i("MyMessage","Successful");
                            }else {
                                Toast.makeText(MainActivity.this, "Error: " +jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                Log.i("MyMessage","Failure");
                            }
                        }
                        catch(JSONException e)
                        {
                            e.printStackTrace();
                            Log.i("MyMessage","JSON Exception");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("MyMessage","Erroneous Response");
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("username",Name.getText().toString());
                        hashMap.put("password",Password.getText().toString());
                        //Log.i("MyMessage","Name and Password are"+Name.getText().toString()+Password.getText().toString());
                        return hashMap;
                    }

                };
                requestQueue.add(request);
            }
        });


    }

    private void validate(String userName, String password) {



        /*if((userName.equals("admin")) && (password.equals("admin")))
        {
            //Now let the user enter the app, i.e. a new activity
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        }
        else
        {
            loginAttempts--;
            Info.setText("No of attempts remaining is "+ String.valueOf(loginAttempts));
            if(loginAttempts == 0)
            {
                Login.setEnabled(false);
            }
        }*/
    }
}
