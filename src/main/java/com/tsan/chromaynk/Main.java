package com.tsan.chromaynk;

import com.tsan.chromaynk.*;
import com.tsan.chromaynk.datatypes.*;
import com.tsan.chromaynk.exceptions.*;
//import com.tsan.chromaynk.executer.*;
import com.tsan.chromaynk.expressions.*;
import com.tsan.chromaynk.parser.*;
import com.tsan.chromaynk.tokenizer.*;

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

    }

}