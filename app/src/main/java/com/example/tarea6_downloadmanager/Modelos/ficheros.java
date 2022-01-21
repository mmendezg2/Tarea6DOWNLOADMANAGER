package com.example.tarea6_downloadmanager.Modelos;

public class ficheros {

    String idfiche;
    String descripcionFich;
    String Fecha;
    String Tema;
    String url;
    String num;

    public ficheros(String idfiche, String descripcionFich, String fecha, String tema, String url, String num) {
        this.idfiche = idfiche;
        this.descripcionFich = descripcionFich;
        Fecha = fecha;
        Tema = tema;
        this.url = url;
        this.num = num;
    }

    public ficheros(String idfiche, String descripcionFich, String fecha, String tema, String url) {
        this.idfiche = idfiche;
        this.descripcionFich = descripcionFich;
        Fecha = fecha;
        Tema = tema;
        this.url = url;
    }

    public ficheros() {
    }

    public String getIdfiche() {
        return idfiche;
    }

    public String getDescripcionFich() {
        return descripcionFich;
    }

    public String getFecha() {
        return Fecha;
    }

    public String getTema() {
        return Tema;
    }

    public String getUrl() {
        return url;
    }

    public String getNum() {
        return num;
    }

    public void setIdfiche(String idfiche) {
        this.idfiche = idfiche;
    }

    public void setDescripcionFich(String descripcionFich) {
        this.descripcionFich = descripcionFich;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public void setTema(String tema) {
        Tema = tema;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
