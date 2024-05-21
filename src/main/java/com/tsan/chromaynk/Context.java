package main.java.com.tsan.chromaynk;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.com.tsan.chromaynk.datatypes.Variable;
import main.java.com.tsan.chromaynk.expressions.Expression;
import main.java.com.tsan.chromaynk.datatypes.Cursor;

public class Context {

    private Map<String, Variable> variable;
    private Map<Integer, Cursor> cursor;

    private Map<String, Expression> function;

    private Variable returnValue = null;
    private boolean returned = false;

    public Context()
    {
        this.returnValue = null;
        this.returned = false;
        this.variable = new HashMap<String, Variable>();
        this.cursor = new HashMap<Integer, Cursor>();
        this.function = new HashMap<String, Expression>();
    }

    public Context(Map<String, Variable> variable)
    {
        this.returnValue = null;
        this.returned = false;
        this.variable = variable;
        this.cursor = new HashMap<Integer, Cursor>();
        this.function = new HashMap<String, Expression>();
    }

    public Context deriveContext(Map<String, Variable> variable)
    {
        Context c = new Context();
        c.returnValue = null;
        c.returned = false;
        c.variable = variable;
        c.cursor = this.cursor;
        c.function = this.function;
        return c;
    }

    public Expression getFunction(String name)
    {
        if(!function.keySet().contains(name)) return null;
        return function.get(name);
    }

    public boolean returned()
    {
        return returned;
    }

    public void setReturned()
    {
        returned = true;
    }

    public void setReturnValue(Variable value)
    {
        returnValue = value;
    }

    public Variable getReturnValue()
    {
        return returnValue;
    }

    public boolean addFunction(String name, Expression body)
    {
        if(function.keySet().contains(name))
            return false;

        function.put(name, body);
        return true;
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
