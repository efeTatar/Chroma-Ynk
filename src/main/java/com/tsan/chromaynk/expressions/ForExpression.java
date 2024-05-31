package com.tsan.chromaynk.expressions;

import java.util.ArrayList;
import java.util.List;

import com.tsan.chromaynk.Context;
import com.tsan.chromaynk.Operation;
import com.tsan.chromaynk.expressions.CreateVariableExpression._type;

public class ForExpression extends NonTerminalExpression{

    String variableName;

    Expression initialiseVariable;

    Assignable condition;

    Expression body;


    public ForExpression(String variableName, Assignable initialValue, Assignable finalValue, Assignable stepValue, Expression body)
    {
        this.variableName = variableName;

        // initialise variable
        List<Expression> initList = new ArrayList<Expression>();
        initList.add( new CreateVariableExpression(variableName, _type.num) );
        initList.add( new AssignExpression(variableName, initialValue) );
        this.initialiseVariable = new ListExpression(initList);
        
        // condition
        this.condition = new OperationExpression(new VariableExpression(variableName), finalValue, Operation.INF);

        // merge body and step
        List<Expression> bodyList = new ArrayList<Expression>();
        // incrementer
        Expression step = new AssignExpression(variableName, new OperationExpression(new VariableExpression(variableName), stepValue, Operation.PLUS) );
        // fusion
        bodyList.add(body);
        bodyList.add(step);
        this.body = new ListExpression(bodyList);
    }

    public void execute(Context context)
    {
        initialiseVariable.execute(context);
        
        while (condition.getValue(context).isTrue()) {
            body.execute(context);
        }
    }

}
