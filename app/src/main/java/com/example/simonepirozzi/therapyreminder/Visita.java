package com.example.simonepirozzi.therapyreminder;

public class Visita {
    private String titolo,giorno,ora,luogo,medico;


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }



    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public Visita(String t, String g, String o, String l, String me){
       titolo=t;
       giorno=g;
       ora=o;
       luogo=l;

       medico=me;
    }


}
