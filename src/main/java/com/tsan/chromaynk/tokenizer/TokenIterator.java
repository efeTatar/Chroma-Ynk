package com.tsan.chromaynk.tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * The token iterator is an essential tool for the parser<br>
 * 
 * It can return the previous, current and next tokens in the list<br>
 * While returning the previous and following tokens, it increments or decrements the index<br>
 * It returns a EOF token if the index is out of range
 */
public class TokenIterator {
    
    private List<Token> list;
    private int index;

    /**
     * 
     * Contructor for the TokenIterator class
     * 
     * @param list
     */
    public TokenIterator(List<Token> list)
    {
        this.index = 0;
        if(list == null) list = new ArrayList<Token>();
        this.list = list;
    }

    /**
     * Checks if the index is out of range<br>
     * i.e. if iterator has reached the end of the token list
     * @return
     */
    public boolean ended()
    {
        return(index >= list.size());
    }

    /**
     * Returns next token in list and increments index
     * @return
     */
    public Token next()
    {
        index++;
        try{
            return list.get(index);
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    /**
     * Returns current token in list
     * @return
     */
    public Token current()
    {
        try{
            return list.get(index);
        }
        catch(IndexOutOfBoundsException e){
            return new Token(Token.tokenType.EOF, "");
        }
    }

    /**
     * Returns previous token in list and decrements index
     * @return
     */
    public Token previous()
    {
        index--;
        try{
            return list.get(index);
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }

}
