package com.example.coleccioncds;

import java.util.ArrayList;
import java.util.Collections;

public class GestoraDisco extends ArrayList<Disco> {
    public GestoraDisco() {
    this.add(new Disco("BISBAL", "AVE MARIA", 2002, false, Estilo.POP));
	this.add(new Disco("BACH", "CONCIERTO", 1980, true, Estilo.CLASICA));
	this.add(new Disco("ROLLING STONES", "Between the Buttons", 1970, true, Estilo.ROCK));
	this.add(new Disco("JOAQUIN DIAZ", "Del Cancionero Tradicional", 1971, true, Estilo.FOLK));
	this.add(new Disco("MALU", "AHORA TU", 2010, false, Estilo.POP));
    }

    public void ordenaEstilo() {
        Collections.sort(this, new ComparadorEstilo());
    }

    public boolean existeDisco(Disco d) {
        for (Disco uno : this) {
            if (d.equals(uno)) {
                return true;
            }
        }
        return false;
    }

    public void ordenaPublicacion() {
        Collections.sort(this, new ComparadorPublicacion());
    }
}
