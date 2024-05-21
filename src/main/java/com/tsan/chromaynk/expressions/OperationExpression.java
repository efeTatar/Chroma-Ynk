package main.java.com.tsan.chromaynk.expressions;

import main.java.com.tsan.chromaynk.Context;
import main.java.com.tsan.chromaynk.Operation;
import main.java.com.tsan.chromaynk.datatypes.Variable;
import main.java.com.tsan.chromaynk.exceptions.DoubleDivisionByZeroException;
import main.java.com.tsan.chromaynk.exceptions.VariableMissingException;
import main.java.com.tsan.chromaynk.exceptions.VariableTypeMismatchException;

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
        try 
        {
            Variable ret = op.eval(x.getValue(context), y.getValue(context));
            return ret;
        }
        catch(DoubleDivisionByZeroException e)
        {
            System.out.println("return null");
            e.display();
        }
        catch(VariableMissingException e)
        {
            System.out.println("return null");
            e.display();
        }
        catch(VariableTypeMismatchException e)
        {
            System.out.println("return null");
            e.display();
        }
        
        return null;
    }

    public void execute(Context context)
    {
        return;
    }

}
