package com.example.simonepirozzi.therapyreminder;

public class Profilo {

    private String nome,cognome,compleanno,mail,username,password;
    public  Profilo(String n,String c,String com,String m,String u,String p){
        nome=n;
        cognome=c;
        compleanno=com;
        mail=m;
        username=u;
        password=p;

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

    public String getCompleanno() {
        return compleanno;
    }

    public void setCompleanno(String compleanno) {
        this.compleanno = compleanno;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
