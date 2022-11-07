package org.hbrs.se1.ws22.uebung4.contorller;

import org.hbrs.se1.ws22.uebung4.model.Exceptions.ContainerException;
import org.hbrs.se1.ws22.uebung4.model.Container;
import org.hbrs.se1.ws22.uebung4.model.Mitarbeiter;
import org.hbrs.se1.ws22.uebung4.model.Exceptions.PersistenceException;
import org.hbrs.se1.ws22.uebung4.model.PersistenceStrategyStream;

public class Main {


    public static void main(String[] args) throws PersistenceException, ContainerException {
        Container container = Container.getInstance();
        PersistenceStrategyStream<Mitarbeiter> persStrat = new PersistenceStrategyStream<>();
        container.setStrategy(persStrat);
        Shell shell = new Shell(container);
        shell.run();


    }
}
