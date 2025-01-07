package org.hbrs.se1.ws24.exercises.uebung9;

public class TestClient {
    public static void main(String[] args) {
        // Erzeugen von Dokumenten
        TextDocument textDoc1 = new TextDocument("Hallo Welt", TextDocument.Encoding.UTF8);
        textDoc1.setID("1");

        TextDocument textDoc2 = new TextDocument("Lorem Ipsum", TextDocument.Encoding.UTF16);
        textDoc2.setID("2");

        GraficDocument graphicDoc = new GraficDocument("localhost:8080");
        graphicDoc.setID("3");

        // ComplexDocument erstellen und Dokumente hinzufügen
        ComplexDocument complexDoc1 = new ComplexDocument();
        complexDoc1.setID("4");
        complexDoc1.addDocument(textDoc1);
        complexDoc1.addDocument(graphicDoc);

        // Weiteres ComplexDocument erstellen
        ComplexDocument complexDoc2 = new ComplexDocument();
        complexDoc2.setID("5");
        complexDoc2.addDocument(textDoc2);
        complexDoc2.addDocument(complexDoc1);

        // Berechnung der gesamten Größe in Bytes
        int totalSize = complexDoc2.getSize();

        // Ausgabe
        System.out.println("Gesamtgröße des Text-Dokuments: " + totalSize + " Bytes");
        System.out.println("Gesamtgröße des Grafik-Dokuments: " + graphicDoc.getSize() + " Bytes");
    }
}

