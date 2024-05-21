package main.java.com.tsan.chromaynk.tokenizer;

public class Token {

    private String value;
    private tokenType token;

    public Token(tokenType token, String value)
    {
        this.token = token;
        this.value = value;
    }

    public Token(tokenType token)
    {
        this.token = token;
        this.value = null;
    }

    public String getValue()
    {
        return this.value;
    }

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
        
            default:
                break;
        }

        if(value == null) return s;

        return s + " \t" + value;
    }

    public static enum tokenType
    {
        WORD, NAME, VALUE, OP, LPAREN, RPAREN, LBRACK, RBRACK, SEMICOL, QUOTE, COMA
    }
    
}
