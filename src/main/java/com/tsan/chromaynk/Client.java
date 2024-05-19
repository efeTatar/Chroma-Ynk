package main.java.com.tsan.chromaynk;

import main.java.com.tsan.chromaynk.tokenizer.Tokenizer;
import main.java.com.tsan.chromaynk.parser.Parser;

public class Client {

    private Context context;

    private Tokenizer tokenizer;
    private Parser parser;

    public Client(){}

    public void tokenize(String file)
    {
        this.tokenizer = new Tokenizer();
        tokenizer.tokenize(file);   
    }

    public void display()
    {
        tokenizer.display();
    }
    
}