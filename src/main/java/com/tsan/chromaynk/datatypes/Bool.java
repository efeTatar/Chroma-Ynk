package main.java.com.tsan.chromaynk.datatypes;

public class Bool extends Variable{
    
    private boolean value;

    public Bool(){}

    public Bool(boolean value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    private boolean getValue()
    {
        return this.value;
    }

    @Override
    public void setValue(Variable value)
    {
        if(value instanceof Num)
            this.value = ((Bool)value).getValue();
        
        // throw exception !!
    }

}
