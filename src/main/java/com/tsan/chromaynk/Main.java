package main.java.com.tsan.chromaynk;

import main.java.com.tsan.chromaynk.datatypes.Num;
import main.java.com.tsan.chromaynk.expressions.AssignExpression;
import main.java.com.tsan.chromaynk.expressions.CreateVariableExpression;
import main.java.com.tsan.chromaynk.expressions.Expression;
import main.java.com.tsan.chromaynk.expressions.ListExpression;
import main.java.com.tsan.chromaynk.expressions.PrintExpression;
import main.java.com.tsan.chromaynk.expressions.ValueExpression;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        
        /*Client client = new Client();
        client.tokenize("iftest.abbas");
        client.display();*/

        List<Expression> l = new ArrayList<Expression>();
        l.add( new CreateVariableExpression("testvar", CreateVariableExpression._type.num) );
        Expression assign = new AssignExpression("testvar", new ValueExpression(new Num(55)));
        l.add(assign);
        l.add(new PrintExpression("testvar"));

        Expression head = new ListExpression(l);
        head.execute(new Context());
    }

}