package org.hbrs.se1.ws22.uebung4.model;

import java.util.HashMap;

public interface Mitarbeiter extends Comparable<Mitarbeiter> {
    int getId();
    String toString();
    String getVorname();
    String getNachname();
    String getRolle();
    String getAbteilung();
    HashMap<String,Integer> getExpertisen();

}
