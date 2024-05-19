package main.java.com.tsan.chromaynk.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class TokenIterator {
    
    private List<Token> list;
    private int index;

    public TokenIterator(List<Token> list)
    {
        this.index = 0;
        if(list == null) list = new ArrayList<Token>();
        this.list = list;
    }

    public Token next()
    {
        if(index+1 >= list.size()) return null;
        return list.get(index+1);
    }

    public Token previous()
    {
        if(index-1 <= 0) return null;
        return list.get(index-1);
    }

    public Token current()
    {
        if(index >= list.size()) return null;
        return list.get(index);
    }

    public Token iterate()
    {
        index++;
        if(index >= list.size()) return null;
        return list.get(index);
    }
}
