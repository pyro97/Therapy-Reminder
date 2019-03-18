package com.example.simonepirozzi.therapyreminder;

public class Medico {
    private String nome,cognome,via;
    private String numero;

    public Medico(String n,String c,String t,String v){
        nome=n;
        cognome=c;
        numero=t;
        via=v;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
