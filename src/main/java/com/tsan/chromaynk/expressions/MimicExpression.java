package com.tsan.chromaynk.expressions;

import com.tsan.chromaynk.Context;
import com.tsan.chromaynk.datatypes.Cursor;
import com.tsan.chromaynk.datatypes.Num;

public class MimicExpression extends NonTerminalExpression{

    Expression body;
    
    public MimicExpression(Expression body)
    {
        this.body = body;
    }

    public void execute(Context context)
    {

        body.execute(context);
    }
    
}
