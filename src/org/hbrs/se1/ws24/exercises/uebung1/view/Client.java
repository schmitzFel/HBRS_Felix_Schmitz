package org.hbrs.se1.ws24.exercises.uebung1.view;

import static org.hbrs.se1.ws24.exercises.uebung1.control.Fabrik.translator;

public class Client {

	public static void main(String[] args) {
		Client client=new Client();
		client.display(0);
	}

		/**
		 * Methode zur Ausgabe einer Zahl auf der Console
		 * (auch bezeichnet als CLI, Terminal)
		 *
		 */
		 void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung (nur) gegen das Interface Translator gewuenscht!

			 System.out.println("Das Ergebnis der Berechnung: " + translator().translateNumber(aNumber));

		 }



}





