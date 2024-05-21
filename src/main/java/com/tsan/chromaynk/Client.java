package main.java.com.tsan.chromaynk;

import main.java.com.tsan.chromaynk.tokenizer.Tokenizer;
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

    public void parse()
    {
        System.out.println("parsing");
        main = parser.parse(tokenizer.getTokenList());
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