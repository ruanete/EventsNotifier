package com.example.eventsnotifier.Herramientas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.eventsnotifier.Datos.Evento;
import com.example.eventsnotifier.R;

import java.util.List;

public class ListaEventosAdapter extends ArrayAdapter<Evento> {
    public ListaEventosAdapter(Context context, int resource, List<Evento> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position == 0){
            return LayoutInflater.from(this.getContext()).inflate(R.layout.nueva_nota, parent, false);
        }

        convertView = LayoutInflater.from(this.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        Evento evento = getItem(position);

        TextView titulo = (TextView) convertView.findViewById(android.R.id.text1);

        titulo.setText(evento.getEvento());

        return convertView;
    }
}
