package org.hbrs.se1.ws22.uebung3;

import java.io.Serializable;

public class ConcreteMember implements Member, Serializable {
    private int id;
    public ConcreteMember(Integer id) {
        this.id = id;

    }
    public void setId(Integer idNeu) {
        id = idNeu;
    }

    @Override
    public Integer getID() {
        return id;
    }
    public String toString() {
        return "Member (ID = [" + id + "])";
    }
}
