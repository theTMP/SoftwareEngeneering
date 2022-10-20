package org.hbrs.se1.ws22.uebung2.test;

import org.hbrs.s1.ws22.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws22.uebung2.ConcreteMember;
import org.hbrs.se1.ws22.uebung2.Container;
import org.hbrs.se1.ws22.uebung2.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContainerTest {
    Container container;
    Member m1 = new ConcreteMember();
    Member m2 = new ConcreteMember();
    Member m3 = new ConcreteMember();
    @BeforeEach
    void init() {
        container = new Container();
    }
    @AfterEach
    void teardown() {
        container = null;
    }
    @Test
    void test_Add() throws Container.ContainerException {
        container.addMember(m1); //neuen Member hinzufügen
        assertEquals(1,container.size());
        container.addMember(m2);
        assertEquals(2,container.size());
        assertThrows(Container.ContainerException.class,() -> container.addMember(m1));  //bereits vorhandenen Memeber hinzufügen
        assertEquals(2,container.size());
        container.addMember(m3);
        assertEquals(3,container.size());
    }
    @Test
    void test_Remove() throws Container.ContainerException {
        container.addMember(m1);
        container.addMember(m2);
        container.addMember(m3);
        assertEquals(3,container.size());
        //removing
        container.deleteMember(0);
        assertEquals(2,container.size());
        container.deleteMember(10);            //nicht existierender Member wird gelöscht
        assertEquals(2,container.size());
        container.deleteMember(1);
        container.deleteMember(2);
        assertEquals(0,container.size());

    }
    @Test
    void test_Size_and_Dump() throws Container.ContainerException {
        container.addMember(m1);
        container.addMember(m2);
        container.addMember(m3);
        container.dump();
        assertEquals(3,container.size());    //Die dump() und size() Methoden sollten die Member Objekte nicht verändern
        container.size();
        assertEquals(3,container.size());

    }



}
