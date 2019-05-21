package com.example.eventsnotifier.Herramientas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.wear.activity.ConfirmationActivity;

import com.example.eventsnotifier.Datos.Evento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GestorEventos {
    public static String guardarEvento(Evento evento, Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String id = String.valueOf(System.currentTimeMillis());
        System.out.println("ID: " + id);

        editor.putString(id, evento.getEvento() + ";" + new SimpleDateFormat("dd-MM-yyyy HH:mm").format(evento.getFecha()));

        editor.commit();

        return id;
    }

    public static ArrayList<Evento> getEventos(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        ArrayList<Evento> eventosUsuario = new ArrayList<>();

        Map<String, ?> datosGuardados = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : datosGuardados.entrySet()) {
            String e = (String) entry.getValue();

            if(e!=null){
                List<String> valoresEvento = Arrays.asList(e.split(";"));
                Evento evento = null;

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                try {
                    Date date = format.parse(valoresEvento.get(1));
                    evento = new Evento(entry.getKey(), valoresEvento.get(0), date);

                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                eventosUsuario.add(evento);
            }
        }

        return eventosUsuario;
    }

    public static void eliminarEvento(String id, Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove(id);

        editor.commit();
    }

    public static void mostrarConfirmacion(String mensaje, Context context){
        Intent intent = new Intent(context, ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.SUCCESS_ANIMATION);

        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, mensaje);
        context.startActivity(intent);
    }
}
