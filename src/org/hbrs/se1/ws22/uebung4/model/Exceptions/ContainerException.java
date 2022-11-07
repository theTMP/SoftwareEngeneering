package org.hbrs.se1.ws22.uebung4.model.Exceptions;

public class ContainerException extends Exception {
    private Integer id;

    public ContainerException(  String id  ) {
        super ("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");
    }

    public ContainerException( ) {
        super ("NULL-Werte dürfen nicht aufgenommen werden!");
    }
}
