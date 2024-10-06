package org.hbrs.se1.ws24.exercises.uebung1.control;

/**
 * Das Translator Interface. Die Anzahl der Methoden ist fix
 * und darf NICHT erweitert werden. Sichtbarkeiten koennen
 * unter Umstaenden angepasst werden.
 *
 * Korrektur: Die Sichtbarkeit des Interface wurde auf public gesetzt,
 * damit sie auch außerhalb des control-Package aufgerufen werden kann
 *
 * @author saschaalda
 *
 */

public interface Translator {
	
	double version = 1.0; // Version des Interface
	
	/*
	 * Uebersetzt eine numerische Zahl in eine String-basierte
	 * Repraesentation gemaess der Spezifikation in der Aufgabe 1-2 
	 */
    String translateNumber(int number);

} 








