package com.example.simonepirozzi.therapyreminder;

import java.util.ArrayList;
import java.util.Date;

public class Attività  {

    private String attivita,ora,note;
    private ArrayList<String> listaGiorni;
    private String durata;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getRimanenti() {
        return rimanenti;
    }

    public void setRimanenti(int rimanenti) {
        this.rimanenti = rimanenti;
    }

    private Date data;
    private  int rimanenti;

    public Attività(String a, String d, ArrayList<String> list, String orario, String note,Date date){
        attivita=a;
        durata=d;
        data=date;
        listaGiorni=list;
        ora=orario;
        this.note=note;
        rimanenti=0;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }



    public String getAttivita() {
        return attivita;
    }

    public void setAttivita(String attivita) {
        this.attivita = attivita;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public ArrayList<String> getListaGiorni() {
        return listaGiorni;
    }

    public void setListaGiorni(ArrayList<String> listaGiorni) {
        this.listaGiorni = listaGiorni;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }
}
