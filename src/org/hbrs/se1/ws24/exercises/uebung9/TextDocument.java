package org.hbrs.se1.ws24.exercises.uebung9;

import java.nio.charset.Charset;

public class TextDocument extends CoreDocument{

    public static Encoding Encoding;
    private String content;
    private String ID;


    public TextDocument(String content, Encoding encoding){
        this.content=content;
        Encoding=encoding;
    }

    public int getSize() {
        try {
            //Wandelt den String "content", basierend auf dem angegebenen Charsetobjekt, welches
            //in der Klammer aus einem Zeichenkodierungsnamen erstellt wird, in ein Byte-Array um
            return content.getBytes(Charset.forName(Encoding.getCharsetName())).length;
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Berechnen der Byte-Größe: " + e.getMessage());
        }
    }

    public void setID(String id){
        this.ID=id;
    }

    public String getID(){
        return this.ID;
    }

}
