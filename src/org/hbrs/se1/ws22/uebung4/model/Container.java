package org.hbrs.se1.ws22.uebung4.model;



import org.hbrs.se1.ws22.uebung4.model.Exceptions.ContainerException;
import org.hbrs.se1.ws22.uebung4.model.Exceptions.PersistenceException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Container {
    private static Container instance;
    private List<Mitarbeiter> storage = new LinkedList<>();

    private PersistenceStrategy<Mitarbeiter> strategy;

    private Container() {

    }

    public synchronized static Container getInstance(){
        if(instance == null){
            instance = new Container();
        }
        return instance;
    }
    public void addEmployee(Mitarbeiter employee) throws ContainerException {
        if(employee == null){
            throw new ContainerException("Das Employee-Objekt ist null und kann daher nicht hizugefügt werden");
        }
        for (Mitarbeiter currentEmployee: storage) {
            if (Objects.equals(currentEmployee.getId(), employee.getId())){
                throw new ContainerException("Das Employee-Objekt mit der ID "+ employee.getId() + " ist bereits vorhanden.");
            }
        }
        storage.add(employee);
    }

    public void deleteEmployee(int employeeId) throws ContainerException {
        int counter = 0;
        for (Mitarbeiter currentEmpoyee: storage) {
            if (Objects.equals(currentEmpoyee.getId(), employeeId)){
                storage.remove(counter);
                return;
            }
        }
        throw new ContainerException("Das Employee-Objekt mit der ID " + employeeId + " konnte nicht gefunden werden.");
    }

    public int size(){
        return storage.size();
    }

    public void setStrategy(PersistenceStrategy<Mitarbeiter> strategy) {
        this.strategy = strategy;
    }

    public void store() throws PersistenceException {
        if(strategy == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy is set.");
        }
        strategy.save(storage);
    }

    public void load() throws PersistenceException {
        if(strategy == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy is set.");
        }
        storage = strategy.load();
    }
    public void loadMerge() throws PersistenceException {
        if (strategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy is set.");
        }
        List<Mitarbeiter> newList = strategy.load();
        for (Mitarbeiter m : newList) {
            storage.add(m);
        }

    }

    public List<Mitarbeiter> getCurrentList(){
        return storage;
    }
    public void dump() {
        for (Mitarbeiter m : storage) {
            System.out.println(m);
        }
    }
}

