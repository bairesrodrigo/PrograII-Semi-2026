package com.example.miprimeraapp;

import android.util.Base64;

public class utilidades {
    static String url_consulta = "http://192.168.1.231:5984/dbamigos/_design/dbamigos/_view/dbamigos";
    static String url_mto = "http://192.168.1.231:5984/dbamigos"; //CRUD, Insertar, Actualizar, Borrar, y Buscar
    static String user = "MysticGhost";
    static String passwd = "Gho$t_26#";
    static String credencialesCodificadas = Base64.encodeToString(
            (user + ":" + passwd).getBytes(),
            Base64.NO_WRAP
    );
    public String generarUnicoId(){
        return java.util.UUID.randomUUID().toString();
    }
}
