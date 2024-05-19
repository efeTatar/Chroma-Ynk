package main.java.com.tsan.chromaynk;

import main.java.com.tsan.chromaynk.datatypes.Num;
import main.java.com.tsan.chromaynk.expressions.AssignExpression;
import main.java.com.tsan.chromaynk.expressions.CreateVariableExpression;
import main.java.com.tsan.chromaynk.expressions.Expression;
import main.java.com.tsan.chromaynk.expressions.ListExpression;
import main.java.com.tsan.chromaynk.expressions.OperationExpression;
import main.java.com.tsan.chromaynk.expressions.PrintExpression;
import main.java.com.tsan.chromaynk.expressions.ValueExpression;
import main.java.com.tsan.chromaynk.expressions.VariableExpression;
import main.java.com.tsan.chromaynk.expressions.WhileExpression;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        
        /*Client client = new Client();
        client.tokenize("iftest.abbas");
        client.display();*/

        List<Expression> l = new ArrayList<Expression>();
        l.add( new CreateVariableExpression("i", CreateVariableExpression._type.num) );
        Expression assign = new AssignExpression("i", new ValueExpression(new Num(5)));
        l.add(assign);

        OperationExpression condition = new OperationExpression(new VariableExpression("i"), new ValueExpression(new Num(10)), Operation.INFEQ);
        OperationExpression addition = new OperationExpression(new VariableExpression("i"), new ValueExpression(new Num(1)), Operation.PLUS);
        Expression addi = new AssignExpression("i", addition);
        
        List<Expression> l2 = new ArrayList<Expression>();
        l2.add(new PrintExpression("i"));
        l2.add(addi);
        
        WhileExpression w = new WhileExpression(condition, new ListExpression(l2));
        l.add(w);

        Expression head = new ListExpression(l);
        head.execute(new Context());

    }

}