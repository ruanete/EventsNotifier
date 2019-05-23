package com.example.eventsnotifier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eventsnotifier.Datos.Evento;
import com.example.eventsnotifier.Herramientas.GestorEventos;
import com.example.eventsnotifier.Herramientas.ListaEventosAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends WearableActivity {

    private ListView listaEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enables Always-on
        //setAmbientEnabled();

        listaEventos = findViewById(R.id.listaEventos);

        //PRUEBA AÑADIR EVENTO
        //Evento e = new Evento("Prueba evento 1", new Date());
        //GestorEventos.guardarEvento(e, this);

        //PRUEBA GET EVENTOS
        /*
        ArrayList<Evento> eventos = GestorEventos.getEventos(this);

        for(Evento e: eventos){
            System.out.println("ID: " + e.getId() + " Fecha: " + e.getFecha().toString() + " Evento: " + e.getEvento() );
        }*/

        Context contexto = this;

        listaEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                    abrirCreadorEvento();
                else{
                    Evento evento = (Evento) parent.getItemAtPosition(position);

                    Intent intent = new Intent(getApplicationContext(), BorrarEventoActivity.class);
                    intent.putExtra("id", evento.getId());

                    startActivity(intent);
                }
            }
        });

        inicializarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();

        inicializarLista();
    }

    public void inicializarLista(){
        ArrayList<Evento> eventos = GestorEventos.getEventos(this);
        eventos.add(0, new Evento("", new Date()));

        listaEventos.setAdapter(new ListaEventosAdapter(this, 0, eventos));
    }

    public void abrirCreadorEvento(){
        /*Intent intent = new Intent (this, FechaEventoActivity.class);
        startActivityForResult(intent, 0);*/
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        startActivityForResult(intent, 1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1001 && resultCode == RESULT_OK){
            List<String> resultado = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String mensaje = resultado.get(0);

            System.out.println("MENSAJE : " + mensaje);

            Evento evento = new Evento(mensaje, new Date());

            GestorEventos.guardarEvento(evento, this);

            GestorEventos.mostrarConfirmacion("Evento guardado", this);
            inicializarLista();
        }
    }
}
