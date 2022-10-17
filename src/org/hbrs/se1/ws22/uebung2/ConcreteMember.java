package org.hbrs.se1.ws22.uebung2;

public class ConcreteMember implements Member{
    private final int ID;
    private static int counter = 0;
    public ConcreteMember() {
        ID = counter;
        counter = counter + 1;
    }

    @Override
    public Integer getID() {
        return ID;
    }
    public String toString() {
        return "ID = [" + ID + "]";
    }
}
