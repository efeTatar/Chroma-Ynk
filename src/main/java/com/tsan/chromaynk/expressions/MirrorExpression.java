package com.tsan.chromaynk.expressions;

import com.tsan.chromaynk.Context;
import com.tsan.chromaynk.datatypes.Num;

public class MirrorExpression extends NonTerminalExpression{

    Assignable x, y;

    boolean p1, p2;

    Expression body;

    public MirrorExpression(Expression body, Assignable x, Assignable y, boolean p1, boolean p2)
    {
        this.body = body;
        this.x = x;
        this.y = y;
        this.p1 = p1;
        this.p2 = p2;
    }

    public void execute(Context context)
    {
        if(x.getValue(context) == null || !(x.getValue(context) instanceof Num) )
        {
            System.out.println("Execution error in MIRROR block: unable to obtain x coordinate");
            return;
        }
        if(y.getValue(context) == null || !(y.getValue(context) instanceof Num) )
        {
            System.out.println("Execution error in MIRROR block: unable to obtain y coordinate");
            return;
        }

        double xCoord = ((Num)(x.getValue(context))).getValue();
        xCoord = p1 ? context.getController().getWidth() * xCoord / 100 : xCoord;
        double yCoord = ((Num)(y.getValue(context))).getValue();
        yCoord = p2 ? context.getController().getHeight() * yCoord / 100 : yCoord;

        System.out.println("mirror coord: " + xCoord + " and " + yCoord);

        body.execute(context);

    }
    
}
