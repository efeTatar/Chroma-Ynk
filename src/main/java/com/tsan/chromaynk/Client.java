package main.java.com.tsan.chromaynk;

import main.java.com.tsan.chromaynk.tokenizer.Tokenizer;
import main.java.com.tsan.chromaynk.exceptions.ParsingFailedException;
import main.java.com.tsan.chromaynk.exceptions.SyntaxErrorException;
import main.java.com.tsan.chromaynk.expressions.Expression;
import main.java.com.tsan.chromaynk.parser.Parser;

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
        System.out.println("tokenizing");
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
        System.out.println("parsing");
        try{
            main = parser.parse(tokenizer.getTokenList());
        }
        catch(ParsingFailedException e){
            e.display();
        }
        
    }

    public void execute()
    {
        System.out.println("executing");
        main.execute(context);
    }

    public void display()
    {
        tokenizer.display();
    }
    
}