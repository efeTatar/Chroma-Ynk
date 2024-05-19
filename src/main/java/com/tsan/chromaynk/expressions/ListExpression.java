package main.java.com.tsan.chromaynk.expressions;

import java.util.ArrayList;
import java.util.List;
import main.java.com.tsan.chromaynk.Context;

public class ListExpression extends NonTerminalExpression{

    private List<Expression> list;

    public ListExpression(){
        list = new ArrayList<Expression>();
    }

    public ListExpression(List<Expression> l)
    {
        list = l;
    }

    public void execute(Context context)
    {
        for(Expression exp : list) exp.execute(context);
    }
    
}
