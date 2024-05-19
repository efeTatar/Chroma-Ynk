package main.java.com.tsan.chromaynk.expressions;

import main.java.com.tsan.chromaynk.Context;
import main.java.com.tsan.chromaynk.datatypes.Bool;
import main.java.com.tsan.chromaynk.datatypes.Num;
import main.java.com.tsan.chromaynk.datatypes.Str;

public class CreateVariableExpression extends TerminalExpression{

    private String name;
    private _type type;

    public enum _type{
        bool, num, str
    };

    public CreateVariableExpression(String name, _type type)
    {
        this.name = name;
        this.type = type;
    }

    public void execute(Context context)
    {
        switch (this.type) {
            case bool:
                context.addVariable(name, new Bool());
                break;
            
            case num:
                context.addVariable(name, new Num());
                break;
            
            case str:
                context.addVariable(name, new Str());
                break;
            
            default:
                break;
        }

        return;
    }
    
}
