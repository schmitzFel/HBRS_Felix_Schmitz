package org.hbrs.se1.ws24.exercises.uebung4;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "location";

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     * Look-up in Google for further help!
     */
    public void save(List<E> member) throws PersistenceException {

        try (FileOutputStream fos = new FileOutputStream(location);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(member);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Error saving objects");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            // Streams initialisieren
            fis = new FileInputStream(location);
            ois = new ObjectInputStream(fis);
            List<E> newListe = null;

            Object obj = ois.readObject();   // Lesen des Objekts

            if (obj instanceof List<?>) { // Prüfen, ob das geladene Objekt eine Liste ist
                newListe = (List<E>) obj;
                return newListe;
            } else {
                // Fehler werfen, wenn das Objekt keine Liste ist
                throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Data is not a valid List");
            }
        } catch (IOException | ClassNotFoundException e) {
            // Fehler beim Öffnen oder Lesen der Datei
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Error loading objects");
        } finally {
            // Manuelles Schließen der Streams
            try {
                if (ois != null) ois.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                // Fehler beim Schließen der Streams
                System.err.println("Error closing the streams: " + e.getMessage());
            }

        }
        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)

    }
}
