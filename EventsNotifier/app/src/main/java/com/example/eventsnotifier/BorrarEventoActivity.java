package com.example.eventsnotifier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.wear.widget.CircularProgressLayout;
import android.view.View;

import com.example.eventsnotifier.Herramientas.GestorEventos;

public class BorrarEventoActivity extends AppCompatActivity implements
        CircularProgressLayout.OnTimerFinishedListener, View.OnClickListener {

    private CircularProgressLayout botonConfirmacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_evento);

        botonConfirmacion = findViewById(R.id.circular_progress);

        botonConfirmacion.setOnTimerFinishedListener(this);
        botonConfirmacion.setOnClickListener(this);

        botonConfirmacion.setTotalTime(3000);
        botonConfirmacion.startTimer();
    }

    @Override
    public void onTimerFinished(CircularProgressLayout circularProgressLayout) {
        GestorEventos.mostrarConfirmacion("Borrado!", this);

        String id = getIntent().getStringExtra("id");

        GestorEventos.eliminarEvento(id, this);

        finish();
    }

    @Override
    public void onClick(View v) {
        GestorEventos.mostrarConfirmacion("Cancelado!", this);
        botonConfirmacion.stopTimer();
        finish();
    }
}
