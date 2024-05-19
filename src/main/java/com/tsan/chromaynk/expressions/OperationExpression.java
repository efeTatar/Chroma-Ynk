package main.java.com.tsan.chromaynk.expressions;

import main.java.com.tsan.chromaynk.Context;
import main.java.com.tsan.chromaynk.datatypes.Num;
import main.java.com.tsan.chromaynk.datatypes.Variable;

public class OperationExpression extends NonTerminalExpression implements Assignable{

    public enum op{
        PLUS, MIN, MULT, DIV, EQ, NEQ, NOT, INF, INFEQ, SUP, SUPEQ, AND, OR
    }
    
    public OperationExpression(op type)
    {
        
    }

    @Override
    public Variable getValue(Context context)
    {
        return new Num();
    }

    public void execute(Context context)
    {
        return;
    }

    public Variable compute()
    {
        return new Num(2.5);
    }

}
