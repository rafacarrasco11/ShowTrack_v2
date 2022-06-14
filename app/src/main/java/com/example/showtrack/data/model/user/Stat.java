package com.example.showtrack.data.model.user;

import java.io.Serializable;

/**
 * Clase POJO para la entidad de un Stat, que las almacena el usuario.
 */
public class Stat implements Serializable {

    String tittle;
    long stat;

    public Stat(String tittle, long stat) {
        this.tittle = tittle;
        this.stat = stat;
    }


    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public long getStat() {
        return stat;
    }

    public void setStat(long stat) {
        this.stat = stat;
    }
}
