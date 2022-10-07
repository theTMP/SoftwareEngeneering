package org.hbrs.s1.ws22.uebung1.view;

import org.hbrs.s1.ws22.uebung1.control.GermanTranslator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    GermanTranslator g;

    @BeforeEach
    void init() {
        g = new GermanTranslator();
    }
    @AfterEach
    void teardown()  {
        g = null;
    }



    @Test
    void Test1_neg() {
        assertEquals("Übersetzung der Zahl -1123 nicht möglich, Version: 1.0",g.translateNumber(-1123));
    }
    @Test
    void Test2_neg() {
        assertEquals("Übersetzung der Zahl 0 nicht möglich, Version: 1.0",g.translateNumber(0));
    }
    @Test
    void Test3_pos() {
        assertEquals("vier",g.translateNumber(4));
    }
    @Test
    void Test4_pos() {
        assertEquals("zehn",g.translateNumber(10));
    }
    @Test
    void Test5_neg() {
        assertEquals("Übersetzung der Zahl -254 nicht möglich, Version: 1.0",g.translateNumber(-254));
    }



}