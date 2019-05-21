package com.example.eventsnotifier.Datos;

import java.util.Date;

public class Evento {
    String id;
    String evento;
    Date fecha;

    public Evento(String evento){
        this.evento = evento;
    }

    public Evento(String id, String evento, Date fecha){
        this.id = id;
        this.evento = evento;
        this.fecha = fecha;
    }

    public Evento(String evento, Date fecha){
        this.evento = evento;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
