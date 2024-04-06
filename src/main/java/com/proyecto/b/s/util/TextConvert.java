package com.proyecto.b.s.util;

public class TextConvert {
    public static Double replaceCommaToPoint(String textoConComas) {

        String textoConPuntos = textoConComas.replace(",", ".");
        textoConPuntos = textoConPuntos.replace(".", "");
        Double remuneration = Double.parseDouble(textoConPuntos);

        return remuneration ;
    }
}
