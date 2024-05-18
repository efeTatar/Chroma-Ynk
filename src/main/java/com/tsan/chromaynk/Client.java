package main.java.com.tsan.chromaynk;

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
    
}