package com.example.coleccioncds;

import java.util.HashMap;

public class Disco extends HashMap<String, Object> implements Comparable<Disco> {

    public Disco(CharSequence autor, CharSequence titulo, int publicacion,
                 boolean comprado, Estilo estilo) {
        super();
        this.put(ClavesDisco.autor.toString(), autor);
        this.put(ClavesDisco.titulo.toString(), titulo);
        this.put(ClavesDisco.publicacion.toString(), publicacion);
        this.put(ClavesDisco.comprado.toString(), comprado);
        this.put(ClavesDisco.estilo.toString(), estilo);
    }

    @Override
    public int compareTo(Disco another) {
        Object[] disco1 = {(Boolean) this.get(ClavesDisco.comprado.toString()), (Estilo) this.get(ClavesDisco.estilo.toString()),
                ((String) this.get(ClavesDisco.titulo.toString())).toLowerCase(), ((String) this.get(ClavesDisco.autor.toString())).toLowerCase(), (Integer) this.get(ClavesDisco.publicacion.toString())};
        Object[] disco2 = {(Boolean) another.get(ClavesDisco.comprado.toString()), (Estilo) another.get(ClavesDisco.estilo.toString()),
                ((String) another.get(ClavesDisco.titulo.toString())).toLowerCase(), ((String) another.get(ClavesDisco.autor.toString())).toLowerCase(), (Integer) another.get(ClavesDisco.publicacion.toString())};
        int campos = ClavesDisco.values().length;
        for (int x = 0; x < campos; x++) {
            boolean iguales = disco1[x].equals(disco2[x]);
            if (!iguales) {
                return disco1[x].toString().compareTo(disco2[x].toString());
            }
        }
        return 0;
    }
    @Override
    public boolean equals(Object object){
        if (object instanceof Disco){
            Disco otro=(Disco) object;
            return this.compareTo(otro)==0?true:false;
        }
        return super.equals(object);
    }

}
