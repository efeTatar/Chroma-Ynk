package main.java.com.tsan.chromaynk.expressions;

import main.java.com.tsan.chromaynk.Context;

public class IfExpression extends NonTerminalExpression{
    
    /*
     * condition must be met for the body to be executed
     * if the condition cannot be checked due to a parsing error,
     * a run time error is met
     */
    private Expression condition;
    private Expression body;

    public void execute(Context context)
    {
        //if(condition.isTrue())
    }

}
