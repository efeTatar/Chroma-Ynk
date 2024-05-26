package com.tsan.chromaynk;

import com.tsan.chromaynk.tokenizer.Tokenizer;
import com.tsan.chromaynk.exceptions.ParsingFailedException;
import com.tsan.chromaynk.exceptions.SyntaxErrorException;
import com.tsan.chromaynk.expressions.Expression;
import com.tsan.chromaynk.parser.Parser;

public class Client {

    private Context context;

    private Tokenizer tokenizer;
    private Parser parser;

    private Expression main;

    public Client(){
        this.context = new Context();
        this.tokenizer = new Tokenizer();
        this.parser = new Parser();
    }

    public void tokenize(String file)
    {
        tokenizer.tokenize(file);   
    }

    public void test()
    {
        try{
            parser.parseOperation(tokenizer.getTokenList());
        }
        catch(ParsingFailedException e){
            e.display();
        }
        catch(SyntaxErrorException e){
            e.display();
        }
        
    }

    public void parse()
    {
        try{
            main = parser.parse(tokenizer.getTokenList());
        }
        catch(ParsingFailedException e){
            e.display();
        }
        
    }

    public void execute()
    {
        if(main == null)
        {
            System.out.println("Execution halted: main function missing");
            return;
        }
        main.execute(context);
        System.out.println("exit value: " + context.getReturnValue());
    }

    public void display()
    {
        tokenizer.display();
    }
    
}