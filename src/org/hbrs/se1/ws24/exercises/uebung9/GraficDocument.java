package org.hbrs.se1.ws24.exercises.uebung9;

public class GraficDocument extends CoreDocument{
    private String url;
    private String ID;
    private byte aByte;

    public GraficDocument(String url){
        this.url=url;
    }

    public void setID(String id){
        this.ID=id;
    }

    public String getID(){
        return this.ID;
    }
    public int getSize() {
        return 1200;
    }
}
