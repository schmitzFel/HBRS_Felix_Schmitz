package org.hbrs.se1.ws24.exercises.uebung1.control;

public class Fabrik {

    /**
     *
     * Design Pattern (dt.: Entwurfsmuster): Factory Method Pattern ([GoF])
     * Problem: Inkonsistente und verteilte Erzeugung von Objekten
     * Lösung: Erstelleung einer Factory Klasse zur konsistenten und zentralen Erzeugung von Objekten
     */

    public static Translator translator(){
        return new GermanTranslator();
    }
}
