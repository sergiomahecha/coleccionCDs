package com.example.coleccioncds;

public enum ClavesDisco {
    comprado,estilo,autor,titulo,publicacion;
    public static String[] claves(){
        ClavesDisco[] claves=ClavesDisco.values();
        int numero=claves.length;
        String[] devolver=new String[numero];
        for (int i=0;i<numero;i++){
            devolver[i]=claves[i].toString();
        }
        return devolver;
    }
}

