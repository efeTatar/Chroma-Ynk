package main.java.com.tsan.chromaynk.datatypes;

public abstract class Variable{

    @Override
    abstract public String toString();

    @Override
    abstract public boolean equals(Object o);

    abstract public void setValue(Variable value);

    // throws error
    abstract public boolean isTrue();

}
