package org.hbrs.se1.ws22.uebung8;

public class ReiseAnbieterAdapter implements IntefHotelSuche {
    private Reiseanbieter ra = new Reiseanbieter();

    public Suchergebnis suche(Suchauftrag s){
        QueryObject oldData = transformIn(s);
        QueryResult ergebnisAlt = ra.execute(oldData);
        Suchergebnis ergebnisNeu = transformOut(ergebnisAlt);
        return ergebnisNeu;
    }

    private QueryObject transformIn(Suchauftrag a) {
       //Transformierungsvorgang ins alte Format (von der legacy Klasse)
        return new QueryObject();
    }
    private Suchergebnis transformOut(QueryResult q) {
        //Transformation ins neue Format
        return new Suchergebnis();
    }

}
