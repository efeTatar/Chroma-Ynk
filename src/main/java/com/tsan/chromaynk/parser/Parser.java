package main.java.com.tsan.chromaynk.parser;

import main.java.com.tsan.chromaynk.datatypes.Num;
import main.java.com.tsan.chromaynk.datatypes.Variable;
import main.java.com.tsan.chromaynk.exceptions.SyntaxErrorException;
import main.java.com.tsan.chromaynk.expressions.*;
import main.java.com.tsan.chromaynk.Context;
import main.java.com.tsan.chromaynk.tokenizer.TokenIterator;

import java.util.List;
import java.util.ArrayList;
import main.java.com.tsan.chromaynk.tokenizer.Token;

public class Parser {

    public Parser(){}

    /*
     * 
     */
    public Expression parse(TokenIterator iterator)
    {
        System.out.println("main parse method");
        List<Expression> list = new ArrayList<Expression>(); 
        
        while(iterator.current() != null)
        {
            //System.out.println("switch");
            switch (iterator.current().getType()) {

                case WORD:
                    list.add(parseWord(iterator));
                    break;
                
                case NAME:
                    //System.out.println("name print ?");
                    if(iterator.current().getValue().equals("PRINT") )
                    {
                        list.add(parsePrint(iterator));
                    }
                    break;
            
                default:
                    break;
            }

        }

        return new ListExpression(list);
    }

    /*
     * 
     */
    public Expression parsePrint(TokenIterator iterator)
    {
        System.out.println("print parse method");
        iterator.next();
        Expression print = new PrintExpression(iterator.next().getValue());
        iterator.next();
        iterator.next();
        iterator.next();
        return print;
    }

    /*
     * 
     */
    public Expression parseWord(TokenIterator iterator)
    {
        System.out.println("word parse method");
        try{

            switch (iterator.current().getValue()) {

                case "NUM":
                case "STR":
                case "BOOL":
                    return parseVariableCreation(iterator);
                
                case "IF":
                    //

                default:
                    break;
            }

        }
        catch(SyntaxErrorException e){
            e.display();
        }

        return null;
        
    }

    /*
     * Method parses tokens related to variable creation
     * method parses initialisation process if any
     */
    private Expression parseVariableCreation(TokenIterator iterator) throws SyntaxErrorException
    {
        System.out.println("variable creation parse method");
        List<Expression> list = new ArrayList<Expression>();
        String VariableType = iterator.current().getValue();
        if(iterator.next().getType() != Token.tokenType.NAME) throw new SyntaxErrorException(VariableType + "statement must be followed by a variable name");
        String variableName = iterator.current().getValue();

        switch (VariableType) {
            case "STR":
                System.out.println("initialising STR");
                list.add(new CreateVariableExpression(iterator.current().getValue(), CreateVariableExpression._type.str));
                break;
            
            case "NUM":
                System.out.println("initialising NUM");
                list.add(new CreateVariableExpression(iterator.current().getValue(), CreateVariableExpression._type.num));
                break;
            
            case "BOOL":
                System.out.println("initialising BOOL");
                list.add(new CreateVariableExpression(iterator.current().getValue(), CreateVariableExpression._type.bool));
                break;
                
            default:
                break;
        }

        if(iterator.next().getType() == Token.tokenType.SEMICOL) return new ListExpression(list);
        
        if( iterator.current().getType() == Token.tokenType.OP & iterator.current().getValue().equals("=") )
        {
            System.out.println("initialising value");
            iterator.next();
            Assignable value = parseOperation(iterator);
            if(iterator.current().getType() != Token.tokenType.SEMICOL) throw new SyntaxErrorException("Variable declaration must be followed by ';' to end the statement");
            iterator.next();
            Expression assign = new AssignExpression(variableName, value);
            list.add(assign);
            System.out.println("variable value initialised");
            return new ListExpression(list);
        }

        throw new SyntaxErrorException("Variable declaration must be followed by ';' to end the statement\nor '=' to initialise the variable with a value");

    }

    /*
     * 
     */
    private Assignable parseOperation(TokenIterator iterator)
    {
        System.out.println("operation parse method");
        while(iterator.current().getType() == Token.tokenType.VALUE | iterator.current().getType() == Token.tokenType.OP | iterator.current().getType() == Token.tokenType.NAME)
        {
            iterator.next();
        }
        return new ValueExpression(new Num(1));
    }

}
