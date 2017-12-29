package com.example.coleccioncds;

public enum Estilo {
    POP(R.string.estilo_pop, R.drawable.pop),
    FOLK(R.string.estilo_folk, R.drawable.folk),
    ROCK(R.string.estilo_rock, R.drawable.rock),
    CLASICA(R.string.estilo_clasica, R.drawable.clasica);
    private final int nombre;
    private final int imagen;

    Estilo(int nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public int getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public static Integer[] nombres() {
        Integer[] nombres = new Integer[values().length];
        for (int x = 0; x < values().length; x++) {
            nombres[x] = values()[x].getNombre();
        }
        return nombres;
    }

    public static Estilo devuelveEstilo(int imagen) {
        for (int x = 0; x < values().length; x++) {
            Estilo uno = values()[x];
            if (uno.imagen == imagen) {
                return uno;
            }
        }
        return null;
    }
}

