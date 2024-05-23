package main.java.com.tsan.chromaynk.expressions;

import main.java.com.tsan.chromaynk.Context;
import main.java.com.tsan.chromaynk.datatypes.Bool;
import main.java.com.tsan.chromaynk.datatypes.Str;
import main.java.com.tsan.chromaynk.datatypes.Num;
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
        if(var == null)
        {
            System.out.println("Warning: failed to retrieve variable in assign expression");
            System.out.println("Warning: assignment aborted");
            return;
        }

        Variable assign = value.getValue(context);
        if(assign == null)
        {
            System.out.println("Warning: failed to retrieve value in assign expression");
            System.out.println("Warning: assignment aborted");
            return;
        }

        if(var instanceof Bool)
        {
            if(assign instanceof Str){
                System.out.println("Warning: BOOL <- STR");
                System.out.println("Warning: assignment aborted");
                return;
            }
            var.setValue( new Bool(assign.isTrue()) );
        }

        if(var instanceof Num)
        {
            if(assign instanceof Str){
                System.out.println("Warning: BOOL <- STR");
                System.out.println("Warning: assignment aborted");
                return;
            }
            var.setValue(value.getValue(context));
        }
    }

}
