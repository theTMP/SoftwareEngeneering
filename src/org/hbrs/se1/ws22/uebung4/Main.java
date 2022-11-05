package org.hbrs.se1.ws22.uebung4;

public class Main {


    public static void main(String[] args) throws PersistenceException, ContainerException {
        Container container = Container.getInstance();
        PersistenceStrategyStream<Mitarbeiter> persStrat = new PersistenceStrategyStream<>();
        container.setStrategy(persStrat);
        Shell shell = new Shell(container);
        shell.run();


    }
}
