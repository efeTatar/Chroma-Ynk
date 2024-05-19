package main.java.com.tsan.chromaynk;

import java.util.Map;
import java.util.HashMap;
import main.java.com.tsan.chromaynk.datatypes.Variable;
import main.java.com.tsan.chromaynk.datatypes.Cursor;

public class Context {

    private Map<String, Variable> variable;
    private Map<Integer, Cursor> cursor;

    public Context()
    {
        this.variable = new HashMap<String, Variable>();
        this.cursor = new HashMap<Integer, Cursor>();
    }

    // throw var exists exception ?
    public boolean addVariable(String name, Variable var)
    {
        if(variable.keySet().contains(name))
            return false;

        variable.put(name, var);
        return true;
    }

    public Variable getVariable(String variableName)
    {
        if(!variable.keySet().contains(variableName)) return null;
        return variable.get(variableName);
    }

    public boolean addCursor(Integer id)
    {
        cursor.put(id, new Cursor());
        return true;
    }

    // private



}
