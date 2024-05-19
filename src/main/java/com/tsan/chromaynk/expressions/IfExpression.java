package main.java.com.tsan.chromaynk.expressions;

import main.java.com.tsan.chromaynk.Context;

public class IfExpression extends NonTerminalExpression{
    
    /*
     * condition must be met for the body to be executed
     * if the condition cannot be checked due to a parsing error,
     * a run time error is met
     */
    private Assignable condition;
    private Expression body;

    public IfExpression(Assignable condition, Expression body)
    {
        this.condition = condition;
        this.body = body;
    }

    public void execute(Context context)
    {
        if(condition.getValue(context).isTrue())
            body.execute(context);
    }

}
