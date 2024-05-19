package main.java.com.tsan.chromaynk.expressions;

import main.java.com.tsan.chromaynk.Context;

public class CreateCursorExpression extends TerminalExpression{

    private Integer id;

    public CreateCursorExpression(Integer id)
    {
        this.id = id;
    }

    public void execute(Context context)
    {
        // catch exception ?
        context.addCursor(id);
    }
    
}
