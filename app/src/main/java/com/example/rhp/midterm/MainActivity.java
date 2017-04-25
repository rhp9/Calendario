package com.example.rhp.midterm;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;


public class MainActivity extends Activity
{
    CalendarView calendar;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainActivity that=this;
        calendar = (CalendarView) findViewById(R.id.calendar);
        Button bEvento = (Button) findViewById(R.id.bEvento);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override

            public void onSelectedDayChange(CalendarView view,int year, int month, int day)
            {
                Toast.makeText(getBaseContext(),"Selected date "+day+"/"+(month+1)+"/"+year,Toast.LENGTH_LONG).show();

                Intent actividad= new Intent(that,NewEvent.class);

                actividad.putExtra("dia",""+day);
                actividad.putExtra("mes",""+(month+1));
                actividad.putExtra("año",""+year);
                startActivity(actividad);
            }
        });

        bEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(that, VerEventos.class);
                startActivity(actividad);

            }
        });
    }

}