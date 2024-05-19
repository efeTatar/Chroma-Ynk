package main.java.com.tsan.chromaynk.expressions;

import main.java.com.tsan.chromaynk.Context;

public class WhileExpression extends NonTerminalExpression{
    
    private Assignable condition;
    private Expression body;

    public WhileExpression(Assignable condition, Expression body)
    {
        this.condition = condition;
        this.body = body;
    }

    public void execute(Context context)
    {
        while(condition.getValue(context).isTrue())
            body.execute(context);
    }

}
