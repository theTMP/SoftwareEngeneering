package org.hbrs.se1.ws22.uebung4;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MitarbeiterKonkret implements Mitarbeiter, Serializable {
    private int ID;
    private String vName;
    private String nName;
    private String rolle;
    private String abteilung;
    private HashMap<String,Integer> expertisen = new HashMap<>();

    public MitarbeiterKonkret(int ID, String nName, String vName, String rolle, String abteilung, HashMap<String,Integer> expertisen) {
        this.ID = ID;
        this.nName = nName;
        this.vName = vName;
        this.rolle = rolle;
        this.abteilung = abteilung;
        this.expertisen = expertisen;
    }

    @Override
    public int getId() {
        return ID;
    }

    @Override
    public int compareTo(Mitarbeiter o) {
        return this.getId() - o.getId();
    }


    public enum ExpertisenLevel {
        Beginner, Experte, TopPerformer;
    }


    public String toString() {
        return ("Vorname: "+vName + ", Nachname: " + nName + ", Rolle: " + rolle + ", Abteilung " + abteilung + ", Expertisen: " +expertisen);
    }

    @Override
    public String getVorname() {
        return vName;
    }

    @Override
    public String getNachname() {
        return nName;
    }

    @Override
    public String getRolle() {
        return rolle;
    }

    @Override
    public String getAbteilung() {
        return abteilung;
    }

    @Override
    public HashMap<String, Integer> getExpertisen() {
        return expertisen;
    }
}
