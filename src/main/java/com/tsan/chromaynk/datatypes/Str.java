package main.java.com.tsan.chromaynk.datatypes;

public class Str extends Variable{

    private String value;

    public Str(){}

    public Str(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return value;
    }

    @Override
    public void setValue(Variable value)
    {
        if(value instanceof Str)
            this.value = ((Str)value).getValue();
        
        // throw exception !!
    }
}
