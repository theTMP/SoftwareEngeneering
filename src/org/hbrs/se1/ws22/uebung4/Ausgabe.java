package org.hbrs.se1.ws22.uebung4;

import java.util.List;
import java.util.stream.Collectors;

public class Ausgabe {

    public void dump(List<Mitarbeiter> list) {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%25s %25s %25s %25s %25s","ID", "Vorname", "Nachname", "Rolle", "Abteilung");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        list.sort(null);
      /*  liste.stream()

                .forEach(element -> System.out.format("%15s %15s %15s %15s",element.getId(),element.getVorname(),element.getNachname(),element.getRolle(),element.getAbteilung()));
        */
        for (Mitarbeiter m :list) {
            System.out.format("%25s %25s %25s %25s %25s",m.getId(),m.getVorname(),m.getNachname(),m.getRolle(),m.getAbteilung());
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }
    public void dumpAbteilung(String abteilung, List<Mitarbeiter> list) {
        // List<Mitarbeiter> newListe = list.stream()
        list.stream()
                .filter(mitarbeiter -> mitarbeiter.getAbteilung().equals(abteilung))
                .forEach(mitarbeiter -> System.out.println(mitarbeiter));
               // .collect(Collectors.toList());

     //   for (Mitarbeiter m: newListe) {
         //   System.out.println(m);
         //   System.out.println();
      //  }
    }

}
