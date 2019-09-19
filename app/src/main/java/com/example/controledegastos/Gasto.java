package com.example.controledegastos;

import java.util.Date;

public class Gasto {

    private float valor;
    private Date data;
    private String detalhes;
    private int categoria;

    public Gasto(float valor, Date data, String detalhes, int categoria) {
        this.valor = valor;
        this.data = data;
        this.detalhes = detalhes;
        this.categoria = categoria;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
