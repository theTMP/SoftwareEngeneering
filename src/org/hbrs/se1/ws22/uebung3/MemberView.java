package org.hbrs.se1.ws22.uebung3;
import java.util.List;

public class MemberView {
    public void dump(List<Member> liste) {
        for (Member m: liste) {
            System.out.println(m);
        }

    }
}
