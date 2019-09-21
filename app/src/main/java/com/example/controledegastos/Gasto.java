package com.example.controledegastos;

import java.io.Serializable;
import java.util.Date;

public class Gasto implements Serializable {

    private int _id;
    private float valor;
    private String data;
    private String detalhes;
    private int categoria;

    public Gasto(float valor, String data, String detalhes, int categoria) {
        this.valor = valor;
        this.data = data;
        this.detalhes = detalhes;
        this.categoria = categoria;
    }

    public Gasto(int id, float valor, String data, String detalhes, int categoria) {
        this._id = id;
        this.valor = valor;
        this.data = data;
        this.detalhes = detalhes;
        this.categoria = categoria;
    }

    public int get_id() {
        return _id;
    }

    public float getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public int getCategoria() {
        return categoria;
    }
}
