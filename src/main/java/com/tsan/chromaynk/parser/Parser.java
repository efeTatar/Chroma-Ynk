package main.java.com.tsan.chromaynk.parser;

import main.java.com.tsan.chromaynk.datatypes.Num;
import main.java.com.tsan.chromaynk.datatypes.Variable;
import main.java.com.tsan.chromaynk.exceptions.ParsingFailedException;
import main.java.com.tsan.chromaynk.exceptions.SyntaxErrorException;
import main.java.com.tsan.chromaynk.exceptions.UnexistingOperatorException;
import main.java.com.tsan.chromaynk.expressions.*;
import main.java.com.tsan.chromaynk.Context;
import main.java.com.tsan.chromaynk.tokenizer.TokenIterator;
import main.java.com.tsan.chromaynk.tokenizer.Token.tokenType;
import main.java.com.tsan.chromaynk.Operation;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import main.java.com.tsan.chromaynk.tokenizer.Token;


public class Parser {

    public Parser(){}

    /*
     * 
     */
    public Expression parse(TokenIterator iterator) throws ParsingFailedException
    {
        System.out.println("main parse method");
        List<Expression> list = new ArrayList<Expression>(); 
        
        try
        {
            while(iterator.current() != null)
            {
                //System.out.println("switch");
                switch (iterator.current().getType()) {

                    case WORD:
                        list.add(parseWord(iterator));
                        break;
                    
                    case NAME:
                        list.add(parseName(iterator));
                        break;
                
                    case RBRACK:
                        //iterator.next();
                        return new ListExpression(list);

                    default:
                        break;
                }

            }
        }
        catch(SyntaxErrorException e){
            e.display();
            throw new ParsingFailedException("parsing failed");
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
     * method parses tokens relating to name
     * such as variables, instructions and functions
     */
    public Expression parseName(TokenIterator iterator) throws SyntaxErrorException, ParsingFailedException
    {
        System.out.println("name parse method");
        String name = iterator.current().getValue();

        try
        {
            switch (name) {

                case "PRINT":
                    return parsePrint(iterator);
            
                // throw exception
                default:
                    if(iterator.next().getType() == Token.tokenType.LPAREN) return null;
                    iterator.previous();
                    if(iterator.next().getType() == Token.tokenType.OP & iterator.current().getValue().equals("="))
                    {
                        iterator.previous();
                        return parseAssignement(iterator);
                    }
                    throw new SyntaxErrorException("variable name must be followed by operation");
            }
        }
        catch(ParsingFailedException e){
            e.display();
            throw new ParsingFailedException("name");
        }
        

    }

    /*
     * method dictates the purpose of given word and redirects to corresponding method 
     */
    public Expression parseWord(TokenIterator iterator) throws ParsingFailedException
    {
        System.out.println("word parse method");
        try{

            switch (iterator.current().getValue()) {

                case "NUM":
                case "STR":
                case "BOOL":
                    return parseVariableCreation(iterator);
                
                case "IF":
                    return parseIfBlock(iterator);

                case "WHILE":
                    return parseWhileBlock(iterator);

                default:
                    break;
            }

        }
        catch(SyntaxErrorException e){
            e.display();
            throw new ParsingFailedException("a parsing error has occured during: parseWord");
        }
        catch(ParsingFailedException e){
            e.display();
            throw new ParsingFailedException("parsing error");
        }

        return null;
        
    }

    /*
     * Method parses tokens related to variable creation
     * method parses initialisation process if any
     */
    private Expression parseVariableCreation(TokenIterator iterator) throws SyntaxErrorException, ParsingFailedException
    {
        System.out.println("variable creation parse method");
        List<Expression> list = new ArrayList<Expression>();
        String VariableType = iterator.current().getValue();
        if(iterator.next().getType() != Token.tokenType.NAME) throw new SyntaxErrorException(VariableType + "statement must be followed by a variable name");
        String variableName = iterator.current().getValue();

        switch (VariableType) {
            case "STR":
                System.out.println("creating STR expression");
                list.add(new CreateVariableExpression(iterator.current().getValue(), CreateVariableExpression._type.str));
                break;
            
            case "NUM":
                System.out.println("creating NUM expression");
                list.add(new CreateVariableExpression(iterator.current().getValue(), CreateVariableExpression._type.num));
                break;
            
            case "BOOL":
                System.out.println("creating BOOL expression");
                list.add(new CreateVariableExpression(iterator.current().getValue(), CreateVariableExpression._type.bool));
                break;
                
            default:
                break;
        }

        if(iterator.next().getType() == Token.tokenType.SEMICOL)
        {
            iterator.next();
            return new ListExpression(list);
        }
        
        if( iterator.current().getType() == Token.tokenType.OP & iterator.current().getValue().equals("=") )
        {
            System.out.println("initialising value");
            iterator.next();
            Assignable value;
            try{ value = parseOperation(iterator); }
            catch(ParsingFailedException e){
                e.display();
                throw new ParsingFailedException("parsing failed in parseVariableCreation");
            }

            if(iterator.current().getType() != Token.tokenType.SEMICOL) throw new SyntaxErrorException("Variable declaration must be followed by ';' to end the statement");
            iterator.next();
            Expression assign = new AssignExpression(variableName, value);
            list.add(assign);
            System.out.println("variable value initialised");
            return new ListExpression(list);
        }

        throw new SyntaxErrorException("Variable declaration must be followed by ';' to end the statement\nor '=' to initialise the variable with a value");

    }

    private Expression parseAssignement(TokenIterator iterator) throws SyntaxErrorException, ParsingFailedException
    {
        System.out.println("assigement parse method");
        String name = iterator.current().getValue();
        iterator.next(); // token: = sign
        iterator.next(); // start of operation
        Assignable value;

        try{ value = parseOperation(iterator); }
        catch(ParsingFailedException e){
            e.display();
            throw new ParsingFailedException("parsing failed");
        }
        catch(SyntaxErrorException e)
        {
            e.display();
            throw new ParsingFailedException("parsing failed");
        }
        
        if(iterator.current().getType() != Token.tokenType.SEMICOL) throw new SyntaxErrorException("Value assignement must be followed by ';'");
        iterator.next();
        return new AssignExpression(name, value);
    }

    /*
     * rewrite method when operation parsing is done !!!
     */
    private Expression parseIfBlock(TokenIterator iterator) throws ParsingFailedException
    {
        try{
            System.out.println("if parse method");
            iterator.next();
            Assignable condition = parseOperation(iterator);
            if(iterator.current().getType() != Token.tokenType.LBRACK) throw new ParsingFailedException("if bug l brack");
            iterator.next();
            Expression body = parse(iterator);
            if(iterator.current().getType() != Token.tokenType.RBRACK) throw new ParsingFailedException("if bug r brack");
            iterator.next();
            return new IfExpression(condition, body);
        }
        catch(ParsingFailedException e){
            e.display();
            throw new ParsingFailedException("parsing failed in: parseIfBlock");
        }
        catch(SyntaxErrorException e){
            e.display();
            throw new ParsingFailedException("parsing failed in: parseWhileBlock");
        }
        
    }

    /*
     * rewrite method when operation parsing is done !!!
     */
    private Expression parseWhileBlock(TokenIterator iterator) throws ParsingFailedException
    {
        try{
            System.out.println("while parse method");
            iterator.next();
            Assignable condition = parseOperation(iterator);
            if(iterator.current().getType() != Token.tokenType.LBRACK) throw new ParsingFailedException("while bug l brack");
            iterator.next();
            Expression body = parse(iterator);
            if(iterator.current().getType() != Token.tokenType.RBRACK) throw new ParsingFailedException("while bug r brack");
            iterator.next();
            return new WhileExpression(condition, body);
        }
        catch(ParsingFailedException e){
            e.display();
            throw new ParsingFailedException("parsing failed in: parseWhileBlock");
        }
        catch(SyntaxErrorException e){
            e.display();
            throw new ParsingFailedException("parsing failed in: parseWhileBlock");
        }
        
    }

    /*
     * this method is an implementation of the Shunting yard algorithm
     */
    public Assignable parseOperation(TokenIterator iterator) throws ParsingFailedException, SyntaxErrorException
    {
        // list of token types relating to operations
        List<Token.tokenType> operationTokens = Arrays.asList(new Token.tokenType[]{
                Token.tokenType.OP,
                Token.tokenType.NAME,
                Token.tokenType.VALUE,
                Token.tokenType.LPAREN,
                Token.tokenType.RPAREN});
        
        // Map to store precedence(0) and associativity(1), 0 for left and 1 for right
        Map<String, Integer[]> properties = new HashMap<String, Integer[]>();
        Integer[] parentheses = {7,0};
        properties.put("(", parentheses); properties.put(")", parentheses);
        Integer[] not = {6, 1};
        properties.put("!", not);
        Integer[] mult = {5, 0};
        properties.put("*", mult); properties.put("/", mult);
        Integer[] plus = {4, 0};
        properties.put("+", plus); properties.put("-", plus);
        Integer[] ineq = {3, 0};
        properties.put("<", ineq); properties.put("<=", ineq); properties.put(">", ineq); properties.put(">=", ineq);
        Integer[] eq = {2, 0};
        properties.put("==", eq); properties.put("!=", eq);
        Integer[] and = {1, 0};
        properties.put("&", and); properties.put("|", and);

        // this initial part of the algorithm provides the expression in the RPN format
        
        List<Token> queue = new ArrayList<Token>();
        Stack<Token> stack = new Stack<Token>();

        // bug ???
        // and statements do not act lazy in while conditions

        while(!iterator.ended() & operationTokens.contains(iterator.current().getType()))
        {
            Token current = iterator.current();
            //System.out.println(current.getValue());
            if(current.getType() == Token.tokenType.VALUE)
                queue.add(current);
            
            else if(current.getType() == Token.tokenType.NAME)
                queue.add(current);
            
            else if(current.getType() == Token.tokenType.OP)
            {
                if(current.getValue().equals("="))
                    throw new SyntaxErrorException("an error has occured during operation parsing: '=' operator can't be parsed");
                
                if(!stack.isEmpty())
                while ( (!stack.isEmpty()) & (!stack.isEmpty() & stack.peek().getType() != Token.tokenType.LPAREN) &
                        ( properties.get(stack.peek().getValue())[0] > properties.get(current.getValue())[0] |
                        (properties.get(stack.peek().getValue())[0] == properties.get(current.getValue())[0] & properties.get(current.getValue())[0] == 0) ) )
                {
                    queue.add(stack.pop());
                    if(stack.isEmpty()) break;
                }
                stack.add(current);
            }

            else if(current.getType() == Token.tokenType.LPAREN)
                stack.add(current);
            
            else if(current.getType() == Token.tokenType.RPAREN)
            {
                if(stack.isEmpty()) throw new SyntaxErrorException("an error has occured during operation parsing: parentheses are not consistent");
                while(!stack.isEmpty() & stack.peek().getType() != Token.tokenType.LPAREN) {
                    queue.add(stack.pop());
                }
                if(stack.peek().getType() != Token.tokenType.LPAREN | stack.isEmpty())
                    throw new SyntaxErrorException("an error has occured during operation parsing: parentheses are not consistant");
                
                stack.pop();
            }

            iterator.next();
            if(iterator.ended()) break;
        }

        while(!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        // the second part of the algorithm builds the tree from the RPN format

        List<Assignable> assignableList = new ArrayList<Assignable>();

        try{
            for(Token t : queue)
            {
                System.out.println(t.getValue());
                if(t.getType() == Token.tokenType.VALUE)
                {
                    assignableList.add(new ValueExpression(new Num( Double.parseDouble(t.getValue()) )));
                    continue;
                }
                if(t.getType() == Token.tokenType.NAME)
                {
                    assignableList.add(new VariableExpression(t.getValue()));
                    continue;
                }
                if(t.getType() == Token.tokenType.OP){
                    assignableList.add(new OperationExpression(null, null, createOp(t.getValue())));
                    continue;
                }
                throw new SyntaxErrorException("Operation cannot be parsed: unrelated token");
            }
        }
        catch(UnexistingOperatorException e){
            e.display();
            throw new SyntaxErrorException("Operation cannot be parsed: unnkown operator");
        }
        
        int index = 0;
        while(assignableList.size() > 1)
        {
            if(index >=  assignableList.size()) return null;
            Assignable node = assignableList.get(index);
            if(node instanceof ValueExpression | node instanceof VariableExpression)
            {
                index++;
                continue;
            }
            if(node instanceof OperationExpression)
            {
                if( ((OperationExpression)node).isNotOp() )
                {
                    if(index-1 < 0) throw new SyntaxErrorException("Operation cannot be parsed: no operand for NOT operation");
                    ((OperationExpression)node).setParameter(assignableList.remove(index-1), null);
                    continue;
                }
                if(index-2 < 0) throw new SyntaxErrorException("Operation cannot be parsed: not enogh operands for operation with two parameters");
                ((OperationExpression)node).setParameter(assignableList.remove(index-2), assignableList.remove(index-2));
                index--;
                continue;
            }
            throw new SyntaxErrorException("Operation cannot be parsed");
        }

        return assignableList.get(0);
    }

    /*
     * 
     */
    public Operation createOp(String operator) throws UnexistingOperatorException
    {
        switch (operator) {
            case "+": return Operation.PLUS;
            case "-": return Operation.MINUS;
            case "*": return Operation.MULT;
            case "/": return Operation.DIV;
            case "&": return Operation.AND;
            case "|": return Operation.OR;
            case "==": return Operation.EQ;
            case "!=": return Operation.NEQ;
            case "!": return Operation.NOT;
            case "<": return Operation.INF;
            case "<=": return Operation.INFEQ;
            case ">": return Operation.SUP;
            case ">=": return Operation.SUPEQ;
            default: throw new UnexistingOperatorException("Operator cannot be read: " + operator);
        }
    }

}
