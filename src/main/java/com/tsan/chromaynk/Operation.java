package main.java.com.tsan.chromaynk;

import main.java.com.tsan.chromaynk.datatypes.Bool;
import main.java.com.tsan.chromaynk.datatypes.Num;
import main.java.com.tsan.chromaynk.datatypes.Variable;

public enum Operation {

    PLUS("+"){
        @Override
        public Variable eval(Variable x, Variable y)
        {
            if(x == null | y == null) return null;
            if(!(x instanceof Num & y instanceof Num)) return null;
            return new Num( ((Num)x).getValue() + ((Num)y).getValue() );
        }
    },
    MINUS("-"){
        @Override
        public Variable eval(Variable x, Variable y)
        {
            if(x == null | y == null) return null;
            if(!(x instanceof Num & y instanceof Num)) return null;
            return new Num( ((Num)x).getValue() - ((Num)y).getValue() );
        }
    },
    MULT("*"){
        @Override
        public Variable eval(Variable x, Variable y)
        {
            if(x == null | y == null) return null;
            if(!(x instanceof Num & y instanceof Num)) return null;
            return new Num( ((Num)x).getValue() * ((Num)y).getValue() );
        }
    },
    DIV("/"){
        @Override
        public Variable eval(Variable x, Variable y)
        {
            if(x == null | y == null) return null;
            if(!(x instanceof Num & y instanceof Num)) return null;
            Double val = ((Num)y).getValue();
            //if(val == 0) // exception
            return new Num( ((Num)x).getValue() / val );
        }
    },
    EQ("=="){
        @Override
        public Variable eval(Variable x, Variable y)
        {
            if(x == null | y == null) return null;
            return new Bool( x.equals(y) );
        }
    },
    NEQ("!="){
        @Override
        public Variable eval(Variable x, Variable y)
        {
            if(x == null | y == null) return null;
            return new Bool( !x.equals(y) );
        }
    },
    INF("<"){
        @Override
        public Variable eval(Variable x, Variable y)
        {
            if(x == null | y == null) return null;
            if(!(x instanceof Num & y instanceof Num)) return null;
            return new Bool( ((Num)x).getValue() < ((Num)y).getValue() );
        }
    },
    INFEQ("<="){
        @Override
        public Variable eval(Variable x, Variable y)
        {
            if(x == null | y == null) return null;
            if(!(x instanceof Num & y instanceof Num)) return null;
            return new Bool( ((Num)x).getValue() <= ((Num)y).getValue() );
        }
    },
    SUP(">"){
        @Override
        public Variable eval(Variable x, Variable y)
        {
            if(x == null | y == null) return null;
            if(!(x instanceof Num & y instanceof Num)) return null;
            return new Bool( ((Num)x).getValue() > ((Num)y).getValue() );
        }
    },
    SUPEQ(">="){
        @Override
        public Variable eval(Variable x, Variable y)
        {
            if(x == null | y == null) return null;
            if(!(x instanceof Num & y instanceof Num)) return null;
            return new Bool( ((Num)x).getValue() >= ((Num)y).getValue() );
        }
    },
    AND("&"){
        @Override
        public Variable eval(Variable x, Variable y)
        {
            if(x == null | y == null) return null;
            if(!(x instanceof Num & y instanceof Num)) return null;
            return new Bool( x.isTrue() & y.isTrue() );
        }
    },
    OR("|"){
        @Override
        public Variable eval(Variable x, Variable y)
        {
            if(x == null | y == null) return null;
            if(!(x instanceof Num & y instanceof Num)) return null;
            return new Bool( x.isTrue() | y.isTrue() );
        }
    },
    NOT("!"){
        @Override
        public Variable eval(Variable x, Variable y)
        {
            if(x == null | y == null) return null;
            if(!(x instanceof Num & y instanceof Num)) return null;
            return new Bool( !x.isTrue() );
        }
    };

    private String op;

    private Operation(String op)
    {
        this.op = op;
    }

    public abstract Variable eval(Variable x, Variable y);
    
}
