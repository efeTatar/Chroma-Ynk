package com.tsan.chromaynk.tokenizer;

/**
 * 
 * Token class<br>
 * 
 * A token contains a value and a type<br>
 * The value is a string to be interpreted by the parser<br>
 * Types are defined in the tokenType enumeration<br>
 * 
 * The tokenizer does not handle negative values<br>
 * The parser handles theem during operation parsing.
 * 
 */
public class Token {

    private String value;
    private tokenType token;

    /**
     * Constructor for Token class
     * 
     * @param token
     * @param value
     */
    public Token(tokenType token, String value)
    {
        this.token = token;
        this.value = value;
    }

    /**
     * Constructor for Token class
     * 
     * @param token
     */
    public Token(tokenType token)
    {
        this.token = token;
        if(token == tokenType.LPAREN) this.value = "(";
        else if(token == tokenType.RPAREN) this.value = ")";
        else this.value = null;
    }

    /**
     * Sets value of token<br>
     * 
     * This method shall only be called during operation parsing 
     * 
     * @param value
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    /**
     * Returns value of Token
     * 
     * @return
     */
    public String getValue()
    {
        return this.value;
    }

    /**
     * Returns type of Token
     * 
     * @return
     */
    public tokenType getType()
    {
        return this.token;
    }

    @Override
    public String toString()
    {
        String s = "";

        switch (token) {
            case WORD:
                s+="WORD";
                break;
            case NAME:
                s+="NAME";
                break;
            case VALUE:
                s+="VALUE";
                break;
            case OP:
                s+="OP";
                break;
            case LPAREN:
                s+="LPAREN";
                break;
            case RPAREN:
                s+="RPAREN";
                break;
            case LBRACK:
                s+="LBRACK";
                break;
            case RBRACK:
                s+="RBRACK";
                break;
            case SEMICOL:
                s+="SEMICOL";
                break;
            case QUOTE:
                s+="QUOTE";
                break;
            case COMA:
                s+="COMA";
                break;
            case EOF:
                s+="EOF";
                break;
            case PERC:
                s+="PERC";
                break;
        
            default:
                break;
        }

        if(value == null) return s;

        return s + " \t" + value;
    }

    /**
     * Types of tokens
     */
    public static enum tokenType
    {
        WORD, NAME, VALUE, OP, LPAREN, RPAREN, LBRACK, RBRACK, SEMICOL, QUOTE, COMA, EOF, PERC
    }
    
}
