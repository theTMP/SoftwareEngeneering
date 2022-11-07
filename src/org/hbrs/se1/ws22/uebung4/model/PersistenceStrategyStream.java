package org.hbrs.se1.ws22.uebung4.model;


import org.hbrs.se1.ws22.uebung4.model.Exceptions.PersistenceException;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "mitarbeiter.ser";

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save
     */
    private FileInputStream fis;
    private ObjectInputStream ois;
    private FileOutputStream fos;
    private ObjectOutputStream oos;
    public void openConnection() throws PersistenceException {
        try {
            fis = new FileInputStream(location);
            ois = new ObjectInputStream(fis);
            fos = new FileOutputStream(location);
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,"Die Verbindung konnte nicht hergestellt werden");
        }
    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {
        try {
            fis.close();
            ois.close();
            fos.close();
            oos.close();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,"Die Verbindung konnte nicht geschlossen werden");
        }

    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> member) throws PersistenceException  {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(location);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        }
        catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "No File found.");
        }
        catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Fail to connect");
        }

        try {
            objectOutputStream.writeObject(member);
        } catch(IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,"Data could not be transferred");
        }

        try {
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)

     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException  {
        List<E> newListe = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(location);
            objectInputStream = new ObjectInputStream(fileInputStream);
        }
        catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "No File found.");
        }
        catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Fail to connect");
        }
        try{
            Object obj = objectInputStream.readObject();
            if (obj instanceof List<?>) {
                newListe = (List) obj;
            }

        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,"Die Daten konnten nicht geladen werden");
        }
        try {
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newListe;

        // Some Coding hints ;-)

        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );
        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
        // ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe

        // and finally close the streams (guess where this could be...?)

    }
}
