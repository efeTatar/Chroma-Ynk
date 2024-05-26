package main.java.com.tsan.chromaynk.expressions;

import main.java.com.tsan.chromaynk.Context;

public class ReturnExpression extends TerminalExpression{
    
    private Assignable value;

    public ReturnExpression(Assignable value)
    {
        this.value = value;
    }

    public void execute(Context context)
    {
        context.setReturnValue(value.getValue(context));
        context.setReturned();
    }

}
