    package main.java.com.tsan.chromaynk;

    import java.util.List;
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.util.Scanner;
    import java.util.ArrayList;

    public class Tokenizer {

        private List<Token> tokens;

        public Tokenizer()
        {
            this.tokens = new ArrayList<Token>(); 
        }
        
        public void tokenize(String file)
        {
            // ( ) { } " = + - / * == != < <= > >= !

            try
            {
                Scanner scanner = new Scanner(new File(file));
                int line = 0;
                while (scanner.hasNextLine())
                {
                    String s = scanner.nextLine();
                    line = line+1;
                    for(int i = 0 ; i < s.length() ; i++)
                    {
                        char c = s.charAt(i);

                        if(c == ' ' || c == '\t') continue;

                        if(Character.isDigit(c))
                        {
                            int j = i;
                            boolean pointCount = false;
                            while (j+1 < s.length()) {
                                if(Character.isDigit(s.charAt(j+1)))
                                {
                                    j++;
                                    continue;
                                }
                                else if(s.charAt(j+1) == '.')
                                {
                                    if(pointCount)
                                    {
                                        System.out.println("\nTokenization error on line " + line + " : numerical value not valid");
                                        // throw exception
                                        System.exit(-1);
                                    }
                                    pointCount = true;
                                    j++;
                                }
                                else
                                {
                                    tokens.add( new Token(Token._token.VALUE, s.substring(i, j+1)) );
                                    i = j;
                                    break;
                                }
                            }
                            if(j+1 == s.length())
                            {
                                tokens.add( new Token(Token._token.VALUE, s.substring(i, j+1)) );
                                i = j;
                            }
                        }

                        if(Character.isAlphabetic(c))
                        {
                            int j = i;
                            while(j+1 < s.length())
                            {
                                if(Character.isAlphabetic(s.charAt(j+1)))
                                {
                                    j++;
                                    continue;
                                }
                                else
                                {
                                    String sub = s.substring(i, j+1);
                                    if(isWord(sub)) tokens.add( new Token(Token._token.WORD, sub));
                                    else tokens.add( new Token(Token._token.NAME, sub));
                                    i = j;
                                    break;
                                }
                            }
                        }
                        
                        if(c == '=' || c == '!' || c == '<' || c == '>')
                        {
                            if(i+1 < s.length() && s.charAt(i+1) == '=')
                            {
                                this.tokens.add(new Token(Token._token.OP, String.valueOf(c) + "="));
                                i++;
                            }
                            else
                                this.tokens.add(new Token(Token._token.OP, String.valueOf(c) ));
                            
                            continue;
                        }

                        if(evalSymbol(c)) continue;

                    }
                        
                }

                for(Token t : tokens)
                    System.out.println(t);

                scanner.close();

            }
            catch(FileNotFoundException e)
            {
                System.out.println("Error: File not found");
            }
        }

        /*
        * Checks if string in parameter is a reserved word
        */
        private boolean isWord(String s)
        {
            if (s.equals("DEF") || s.equals("NUM") || s.equals("BOOl") || s.equals("STR") || s.equals("IF") || s.equals("FOR") || s.equals("WHILE") || s.equals("MIMIC") || s.equals("MIRROR")) {
                //tokens.add(new Token(Token._token.WORD, s));
                return true;
            }
            return false;
        }

        /*
        * Looks for syntaxic symbols and arithmetic operations
        * Creates Token in case of a match
        */
        private boolean evalSymbol(char c)
        {
            switch (c) {
                case '{':
                    this.tokens.add(new Token(Token._token.LBRACK));
                    return true;
                case '}':
                    this.tokens.add(new Token(Token._token.RBRACK));
                    return true;
                case '(':
                    this.tokens.add(new Token(Token._token.LPAREN));
                    return true;
                case ')':
                    this.tokens.add(new Token(Token._token.RPAREN));
                    return true;
                case '"':
                    this.tokens.add(new Token(Token._token.QUOTE));
                    return true;
                case ';':
                    this.tokens.add(new Token(Token._token.SEMICOL));
                    return true;
                case ',':
                    this.tokens.add(new Token(Token._token.COMA));
                    return true;
                case '+':
                case '-':
                case '*':
                case '/':
                    this.tokens.add(new Token(Token._token.OP, String.valueOf(c)));
                    return true;
            
                default:
                    return false;
            }
        }

    }
