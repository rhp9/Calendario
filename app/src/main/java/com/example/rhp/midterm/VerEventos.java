package com.example.rhp.midterm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VerEventos extends AppCompatActivity
{
    private static String TAG = MainActivity.class.getSimpleName();
    private String jsonResponse;
    private TextView txtResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        final  VerEventos that=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_eventos);

        Button bAtras = (Button) findViewById(R.id.bBack);
        Button button2 = (Button) findViewById(R.id.button2);
        final TextView txtResponse=(TextView) findViewById(R.id.textView);

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(that, MainActivity.class);
                startActivity(actividad);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

           // String url= "https//quiet-tundra-44981.herokuapp.com/select.php";
                String url="https://jsonplaceholder.typicode.com/posts/";

            //Instantiate the RequestQueue
            RequestQueue queue= Volley.newRequestQueue(that);


            //Request a string response from a private URL
            JsonArrayRequest jsonRequest= new JsonArrayRequest(Request.Method.GET,url,null,
                    new Response.Listener<JSONArray>() {
                        public void onResponse(JSONArray response) {
                            //Display the first 500 characters of the response String

                            Toast.makeText(getBaseContext(),"Response is: " + response.toString(),Toast.LENGTH_SHORT).show();
                            try
                            {
                                jsonResponse = "";
                                for (int i = 0; i < response.length(); i++) {

                                    JSONObject person = (JSONObject) response
                                            .get(i);

                                    String name = person.getString("id");
                                    String email = person.getString("title");


                                    jsonResponse += "Name: " + name + "\n\n";
                                    jsonResponse += "Email: " + email + "\n\n\n";



                                }

                                txtResponse.setText(jsonResponse);
                            }
                            catch(JSONException e)
                            {
                                Toast.makeText(getBaseContext(),"Fallo en la interpretacion del JSON",Toast.LENGTH_SHORT).show();
                            }
                        }
                    },new Response.ErrorListener(){
                public void onErrorResponse(VolleyError error){
                    Log.d("Response","That didn't work");
                    error.printStackTrace();
                    Toast.makeText(getBaseContext(),"that didnt work",Toast.LENGTH_SHORT).show();
                }
            });
            //Add the request to the RequestQueue
        queue.add(jsonRequest);

            }
        });
    }

}
