package main.java.com.tsan.chromaynk;

import java.util.Map;
import java.util.HashMap;
import main.java.com.tsan.chromaynk.datatypes.Variable;

public class Context {

    private Map<String, Variable> variable;
    
    private Map<Integer, Cursor> cursor;

    public Context()
    {
        this.variable = new HashMap<String, Variable>();
        this.cursor = new HashMap<Integer, Cursor>();
        
    }

    // private



}
