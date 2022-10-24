package org.hbrs.se1.ws22.uebung3;

public class Client {

    private Container container = Container.getInstance();
    private MemberView memberView = new MemberView();

    public void addMember(Member m) {
        try {
            container.addMember(m);
        } catch (Container.ContainerException c) {
            System.out.println("Member konnte nicht hinzugefügt werden");
        }
    }
    public String deleteMember(Integer id) {
        try {
            return container.deleteMember(id);
        } catch (Container.ContainerException c) {
            return "Member-Objekt konnte nicht gelöscht werden";
        }
    }
    public void dump() {
        memberView.dump(container.getCurrentList());
    }


}
