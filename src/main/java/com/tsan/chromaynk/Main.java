package main.java.com.tsan.chromaynk;

import main.java.com.tsan.chromaynk.*;
import main.java.com.tsan.chromaynk.datatypes.*;
import main.java.com.tsan.chromaynk.exceptions.*;
//import main.java.com.tsan.chromaynk.executer.*;
import main.java.com.tsan.chromaynk.expressions.*;
import main.java.com.tsan.chromaynk.parser.*;
import main.java.com.tsan.chromaynk.tokenizer.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        
        Client client = new Client();
        client.tokenize("iftest.abbas");
        client.display();
        System.out.println();
        client.parse();
        System.out.println();
        client.execute();

        //client.test();

    }

}