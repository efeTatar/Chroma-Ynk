package main.java.com.tsan.chromaynk.datatypes;

public class Num extends Variable{

    private Double value = 0.0;

    public Num(){}

    public Num(double value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o)
    {
        if( o == null | !(o instanceof Num) ) return false;
        return (this.value == ((Num)o).value);
    }

    public Double getValue()
    {
        return this.value;
    }

    @Override
    public void setValue(Variable value)
    {
        if(value instanceof Num)
            this.value = ((Num)value).getValue();
        
        // throw exception !!
    }

    @Override
    public boolean isTrue()
    {
        return (value != 0);
    }
    
}
