package com.tsan.chromaynk;

import com.tsan.chromaynk.tokenizer.Tokenizer;
import com.example.demo1.DrawingAppController;
import com.tsan.chromaynk.exceptions.ParsingFailedException;
import com.tsan.chromaynk.expressions.Expression;
import com.tsan.chromaynk.parser.Parser;

/**
 * The Client class moderates the interpratation process<br>
 * 
 * It encompasses a tokenizer, a parser and a context for the main function.<br>
 * The context remains so each interpretation requires a new client.
 */
public class Client {

    private Context context;

    private Tokenizer tokenizer;
    private Parser parser;

    // main function
    private Expression main;

    /**
     * 
     * Constructor for Client class
     * Feed in null if interpreter used with no interface
     * 
     * @param controller
     */
    public Client(DrawingAppController controller){
        this.context = new Context(controller);
        this.tokenizer = new Tokenizer();
        this.parser = new Parser();
    }

    /**
     * 
     * Tokenizez given file
     * 
     * @param file
     */
    public void tokenize(String file)
    {
        tokenizer.tokenize(file);   
    }

    /**
     * Parses tokens<br>
     * 
     * cannot parse before tokenizing
     */
    public void parse()
    {
        try{
            main = parser.parse(tokenizer.getTokenList());
        }
        catch(ParsingFailedException e){
            e.display();
        }
        
    }

    /**
     * Executes the main function
     */
    public void execute(int speed) throws InterruptedException
    {
        if(main == null)
        {
            System.out.println("Execution halted: main function missing");
            return;
        }

        context.setSpeed(speed);

        Thread drawingThread = new Thread(() -> {
            this.main.execute(context);
        });
        drawingThread.setDaemon(true);
        drawingThread.start();
        System.out.println("exit value: " + context.getReturnValue());
    }

    /**
     * Displays tokens<br>
     * debugging function
     */
    public void display()
    {
        tokenizer.display();
    }
    
}