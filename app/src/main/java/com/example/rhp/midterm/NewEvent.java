package com.example.rhp.midterm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class NewEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        TextView textView = (TextView) findViewById(R.id.tvDia);


        if (getIntent().hasExtra("dia")) {
            // Toast.makeText(getApplicationContext(),getIntent().getStringExtra("dia"),Toast.LENGTH_LONG).show();
            textView.setText(getIntent().getStringExtra("dia") + "/" + getIntent().getStringExtra("mes") + "/" + getIntent().getStringExtra("año"));
        } else {
            Toast.makeText(getApplicationContext(), "no funciono", Toast.LENGTH_LONG).show();
        }

        final NewEvent that = this;
        final EditText hourEt = (EditText) findViewById(R.id.etHour);
        final EditText bodyEt = (EditText) findViewById(R.id.et_body);
        Button submitBtn = (Button) findViewById(R.id.btn_submit);
        final String date = getIntent().getStringExtra("dia") + "/" + getIntent().getStringExtra("mes") + "/" + getIntent().getStringExtra("año");
        final NewEvent context = this;


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hourEt == null && bodyEt == null) {
                    Toast.makeText(getApplicationContext(), "Llena todos los campos ", Toast.LENGTH_LONG).show();
                }


                    String hour = hourEt.getText().toString();
                    String body = bodyEt.getText().toString();
                    String fecha2 = date + " " + hour;
                    Toast.makeText(getApplicationContext(), fecha2, Toast.LENGTH_LONG).show();

                   /* Notificacion
                   Date date1 = new SimpleDateFormat("dd MM yyyy HH:mm").parse(fecha2);

                    Calendar fecha = Calendar.getInstance();
                    fecha.setTime(date1);

                    Intent intent = new Intent(context, Receiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 001, intent, 0);

                    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                    am.set(AlarmManager.RTC_WAKEUP, fecha.getTimeInMillis(), pendingIntent);*/

                    postJSON(date,hour,body);
                Toast.makeText(getApplicationContext(), "Event Created Successfully", Toast.LENGTH_LONG).show();
                Intent actividad = new Intent(that, MainActivity.class);
                startActivity(actividad);




            }
        });

        Button bAtras = (Button) findViewById(R.id.bBack);


        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(that, MainActivity.class);
                startActivity(actividad);

            }
        });
    }


    public void postJSON(final String fecha, final String hora, final String body)
    {
        final NewEvent that = this;
        final RequestQueue queue=Volley.newRequestQueue(this);

        String url ="https://quiet-tundra-44981.herokuapp.com/index.php";
        JSONObject params = new JSONObject();

        try {
            params.put("Fecha", fecha);
            params.put("Hora", hora);
            params.put("Body", body);

        } catch (Exception e) {

        }

// Instantiate the RequestQueue.


        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject result = new JSONObject(response);

                    Log.d("Response", result.toString());
                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Fecha", fecha);
                params.put("Hora", hora);
                params.put("Body", body);

                return params;
            }
        };
        queue.add(sr);
    }

}