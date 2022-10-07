package org.hbrs.s1.ws22.uebung1.control;
/*
The idea behind the Factory class is the separation of concerns. If there is logic or configuration involved
in the instantiation of an Object, the Factory class can "hide" this.
Factory classes also provide reusability of the code.
Factory methods are known as a "Virtual Constructor", because the constructor itslef cannot be polymorph.

*/

public class Factory {

    public static Translator createTranslator() {
        return new GermanTranslator();
    }
}
