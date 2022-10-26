package org.hbrs.se1.ws22.uebung3;

import java.util.List;
import java.util.LinkedList;

public class Container {
     public class ContainerException extends Exception {
        public ContainerException(String message) {
            super(message);
        }
    }
    private PersistenceStrategy persStrat = null;
    private List<Member> list = new LinkedList<>(); //Linked List statt ArrayList, da wir häufig Member hinzufügen und löschen wollen. Bei ArrayList großer Aufwand durch kopieren von Werten in neues Array.
    private static Container container;        //Anwendung des Singleton Prinzips, so dass immer nur ein Container-Objekt gleichzeitig existiert

    private Container() {}

    public static Container getInstance() {
        if (container == null) {
            container = new Container();
        }
        return container;
    }
    public void addMember(Member member) throws ContainerException {
        if (member == null) {
            throw new ContainerException("Null wurde übergebn");
        }

        for (Member m : list) {
            if (m.getID().intValue() == member.getID().intValue()) {
                throw new ContainerException("Das Member-Objekt mit der ID [" + member.getID() + "] ist breits vorhanden!");
            }
        }

        list.add(member);

    }
    public String deleteMember(Integer id) throws ContainerException {        //Nachteile von Fehlerhandling ohne Exception: Der Fehler kann nicht im Programm behandelt werden und darauf reagiert werden.

        if (id == null) {
            throw new ContainerException("Null wurde übergeben");
        }
        for (Member m : list) {
            if (m.getID().intValue() == id.intValue()) {
                list.remove(m);
                return "Member erfolgreich gelöscht";
            }
        }
        return "Der gesuchte Member existiert nicht";

    }
    public void dump() {
        for (Member m : list) {
            System.out.println(m);
        }
    }

    public int size() {
        return list.size();
    }

    public void store() throws PersistenceException {
        if (persStrat == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"No strategy is set");
        }

        if (persStrat instanceof PersistenceStrategyMongoDB<?>) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,"MongoDB is not available");
        }
      //  persStrat.openConnection();
        persStrat.save(list);
       // persStrat.closeConnection();


    }
    public void load() throws PersistenceException {
        if (persStrat == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"No strategy is set");
        }
       // persStrat.openConnection();
        list = persStrat.load();
       // persStrat.closeConnection();

    }
    public List<Member> getCurrentList() {
        return list;
    }
    public void setPersistanceStrategy(PersistenceStrategy p) {
        persStrat = p;
    }



}
