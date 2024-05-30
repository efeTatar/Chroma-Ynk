package com.tsan.chromaynk.datatypes;

/**
 * The Variable abstract class<br>
 * 
 * The subclasses belonging to Variable class are:<br>
 * Num, Str and Bool<br> 
 */
public abstract class Variable{

    public enum type{num, bool, str}

    @Override
    abstract public String toString();

    @Override
    abstract public boolean equals(Object o);

    abstract public void setValue(Variable value);

    // throws error
    abstract public boolean isTrue();

}
