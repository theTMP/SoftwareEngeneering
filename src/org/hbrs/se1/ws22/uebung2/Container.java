package org.hbrs.se1.ws22.uebung2;

import java.util.LinkedList;

public class Container {
     public class ContainerException extends Exception {
        public ContainerException(String message) {
            super(message);
        }
    }
    LinkedList<Member> list = new LinkedList<>(); //Linked List statt ArrayList, da wir häufig Member hinzufügen und löschen wollen. Bei ArrayList großer Aufwand durch kopieren von Werten in neues Array.

    public void addMember(Member member) throws ContainerException {

        for (Member m : list) {
            if (m.getID() == member.getID()) {
                throw new ContainerException("Das Member-Objekt mit der ID [" + member.getID() + "] ist breits vorhanden!");
            }
        }

        list.addLast(member);

    }
    public String deleteMember(Integer id) {        //Nachteile von Fehlerhandling ohne Exception: Der Fehler kann nicht im Programm behandelt werden und darauf reagiert werden.
        for (Member m : list) {
            if (m.getID() == id) {
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

    public static void main(String[] args) {
        Container c = new Container();
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        ConcreteMember m4 = new ConcreteMember();

        try {
            c.addMember(m1);
            c.addMember(m4);
        } catch (ContainerException e) {
            throw new RuntimeException(e);
        }

       c.dump();



    }

}
