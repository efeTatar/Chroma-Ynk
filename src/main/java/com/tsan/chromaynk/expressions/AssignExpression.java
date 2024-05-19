package main.java.com.tsan.chromaynk.expressions;

import main.java.com.tsan.chromaynk.Context;
import main.java.com.tsan.chromaynk.datatypes.Variable;

public class AssignExpression extends NonTerminalExpression{
    
    /*
     * name of variable and value to be assigned
     */
    private String variableName;
    private Assignable value;

    public AssignExpression(String variableName, Assignable value)
    {
        this.variableName = variableName;
        this.value = value;
    }

    public void execute(Context context)
    {
        Variable var = context.getVariable(variableName);
        // exception ?
        if(var == null) return;
        
        var.setValue(value.getValue(context));
    }

}
