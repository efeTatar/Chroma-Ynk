package main.java.com.tsan.chromaynk.expressions;

import main.java.com.tsan.chromaynk.Context;
import main.java.com.tsan.chromaynk.datatypes.Bool;
import main.java.com.tsan.chromaynk.datatypes.Num;
import main.java.com.tsan.chromaynk.datatypes.Str;
import main.java.com.tsan.chromaynk.datatypes.Variable;

public class SelectCursorExpression extends TerminalExpression{
    
    private Assignable id;

    public SelectCursorExpression(Assignable id)
    {
        this.id = id;
    }

    public void execute(Context context)
    {
        Variable var = id.getValue(context);
        Integer id;
        if(var instanceof Str)
        {
            System.out.println("Failed to select cursor: String cannot be converted to integer");
            return;
        }
        if(var instanceof Bool)
        {
            System.out.println("Failed to select cursor: cannot initialise cursor id from bool");
            return;
        }
        if(var instanceof Num)
        {
            id = ((Num)var).getValue().intValue();
            if(context.selectCursor(id))
            {
                System.out.println("Cursor selected: "+id);
            }
            return;
        }
        System.out.println("ERROR in cursor selection expression");
    }

}
