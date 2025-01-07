package org.hbrs.se1.ws24.exercises.uebung9;

import java.util.ArrayList;
import java.util.List;

public class ComplexDocument implements Document{

    private String ID;
    private List<Document> documents = new ArrayList<>();

    public void addDocument(Document document){
        this.documents.add(document);
    }

    public void removeDocument(Document document) {
        this.documents.remove(document);
    }
    @Override
    public int getSize() {
        int totalSize = 0;
        for (Document doc : documents) {
            totalSize += doc.getSize();
        }
        return totalSize;
    }

    public void setID(String id) {
        this.ID=id;
    }

    public String getID(){
        return this.ID;
    }
}
