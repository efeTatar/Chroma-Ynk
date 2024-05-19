package main.java.com.tsan.chromaynk.expressions;

import main.java.com.tsan.chromaynk.Context;
import main.java.com.tsan.chromaynk.Operation;
import main.java.com.tsan.chromaynk.datatypes.Variable;

public class OperationExpression extends NonTerminalExpression implements Assignable{

    private Assignable x, y;
    private Operation op;
    
    public OperationExpression(Assignable x, Assignable y, Operation op){
        this.x = x;
        this.y = y;
        this. op = op;
    }

    @Override
    public Variable getValue(Context context)
    {
        return op.eval(x.getValue(context), y.getValue(context));
    }

    public void execute(Context context)
    {
        return;
    }

}
