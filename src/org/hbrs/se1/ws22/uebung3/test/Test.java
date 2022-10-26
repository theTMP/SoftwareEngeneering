package org.hbrs.se1.ws22.uebung3.test;
import org.hbrs.se1.ws22.uebung3.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class Test {
    private Container container;

    private Member m1 = new ConcreteMember(0);
    private Member m2 = new ConcreteMember(1);
    private Member m3 = new ConcreteMember(2);

    @BeforeEach
    void intit() {
        container = Container.getInstance();

    }
    @AfterEach
    void teardown() {
        container = null;
    }

    @org.junit.jupiter.api.Test
    void test_Client() {
        Client c = new Client();
        c.addMember(m1);
        assertEquals(1,container.size());
        c.addMember(m2);
        assertEquals(2,container.size());
        c.addMember(m3);
        assertEquals(3,container.size());
        c.addMember(m1);
        assertEquals(3,container.size());
        c.deleteMember(1);
        c.dump();
        assertEquals(2,container.size());
        c.deleteMember(0);
        c.deleteMember(2);
        assertEquals(0, container.size());

    }
    @org.junit.jupiter.api.Test
    void test_ohne_PersStrat() throws Container.ContainerException, PersistenceException {
        container.setPersistanceStrategy(null);
        container.addMember(m1);
        assertThrows(PersistenceException.class, () -> container.store());


    }

    @org.junit.jupiter.api.Test
    void test_MonogDB_Strat() throws Container.ContainerException {
        container.setPersistanceStrategy(new PersistenceStrategyMongoDB<Member>());
        container.addMember(m2);
        PersistenceException s = assertThrows(PersistenceException.class, () -> container.store());
        assertEquals(s.getMessage(), "MongoDB is not available");

    }
    @org.junit.jupiter.api.Test
    void test_fehlerhafte_location() {
        PersistenceStrategyStream<Member> ps = new PersistenceStrategyStream<>();
        ps.setLocation("/project/testordner/objects.ser");
        container.setPersistanceStrategy(ps);
        assertThrows(PersistenceException.class,() -> container.store());
    }
    @org.junit.jupiter.api.Test
    void test_korrekt() throws Container.ContainerException, PersistenceException {
        container.setPersistanceStrategy(new PersistenceStrategyStream());
        container.addMember(m1);
        container.addMember(m2);
        container.addMember(m3);
        assertEquals(3,container.size());
        container.store();
        container.deleteMember(0);
        container.deleteMember(1);
        container.deleteMember(2);
        assertEquals(0,container.size());
        container.load();
        assertEquals(3,container.size());
        container.deleteMember(0);
        container.deleteMember(1);
        container.deleteMember(2);

    }
}
