package com.tsan.chromaynk;

import java.util.Map;
import java.util.HashMap;

import com.tsan.chromaynk.datatypes.Variable;
import com.tsan.chromaynk.expressions.Expression;
import com.example.demo1.DrawingAppController;
import com.tsan.chromaynk.datatypes.Cursor;

/**
 * The Context class acts like memory for the Abbas language interpreter<br>
 * 
 * It contains variables, cursors, functions and the return value.<br>
 * It also has access to the canvas if the interpreter has to execute instructions
 */
public class Context {

    private Map<String, Variable> variable;
    private Map<Integer, Cursor> cursor;
    private Map<String, Expression> function;

    private Variable returnValue = null;
    private boolean returned = false;

    private Cursor mainCursor;

    private DrawingAppController controller;

    public int speed;

    /**
     * 
     * Context constructor
     * 
     * must fetch in null as controller if interpreter is being<br>
     * used independently of the interface
     * 
     * @param controller
     */
    public Context(DrawingAppController controller)
    {
        this.returnValue = null;
        this.returned = false;
        this.controller = controller;
        this.variable = new HashMap<String, Variable>();
        this.cursor = new HashMap<Integer, Cursor>();
        this.function = new HashMap<String, Expression>();
    }

    /**
     * 
     * Derives new context from another
     *
     * method intended to be used for function calls
     * 
     * @param variable
     * @return
     */
    public Context deriveContext(Map<String, Variable> variable)
    {
        Context c = new Context(controller);
        c.returnValue = null;
        c.returned = false;
        c.variable = variable;
        c.cursor = this.cursor;
        c.function = this.function;
        c.controller = this.controller;
        return c;
    }

    /**
     * 
     * Returns controller
     * 
     * @return
     */
    public DrawingAppController getController()
    {
        return controller;
    }

    /**
     * 
     * Fetches main cursor
     * 
     * @return
     */
    public Cursor getMainCursor()
    {
        return mainCursor;
    }

    /**
     * 
     * Sets main cursor
     * 
     * returns false if fails
     * 
     * @param id
     * @return
     */
    public boolean selectCursor(int id)
    {
        Cursor c = getCursor(id);
        if(c == null) return false;
        mainCursor = c;
        return true;
    }

    /**
     * 
     * Adds cursor
     * 
     * returns false if cursor already exists
     * 
     * @param id
     * @param c
     * @return
     */
    public boolean addCursor(int id, Cursor c)
    {
        if(cursor.keySet().contains(id)) return false;
        cursor.put(id, c);
        return true;
    }

    /**
     * 
     * Fetches cursor
     * 
     * returns null if cursor is missing
     * 
     * @param id
     * @return
     */
    public Cursor getCursor(int id)
    {
        if(!cursor.keySet().contains(id)) return null;
        return cursor.get(id);
    }

    /**
     * 
     * removes cursor
     * 
     * returns false if removal failed
     * 
     * @param id
     * @return
     */
    public boolean removeCursor(Integer id)
    {
        if(!cursor.keySet().contains(id)) return false;
        Cursor c = cursor.remove(id);
        if(c.equals(mainCursor)) mainCursor = null;
        return true;
    }

    /**
     * 
     * Returns function
     * 
     * returns null if function does not exist
     * 
     * @param name
     * @return
    */
    public Expression getFunction(String name)
    {
        if(!function.keySet().contains(name)) return null;
        return function.get(name);
    }

    /**
     * Return function state<br>
     * 
     * @return
     */
    public boolean returned()
    {
        return returned;
    }

    /**
     * sets function state to returned
     */
    public void setReturned()
    {
        returned = true;
    }

    /**
     * 
     * @param value
     */
    public void setReturnValue(Variable value)
    {
        returnValue = value;
    }

    /**
     * 
     * @return
     */
    public Variable getReturnValue()
    {
        return returnValue;
    }

    /**
     * 
     * @param name
     * @param body
     * @return
     */
    public boolean addFunction(String name, Expression body)
    {
        if(function.keySet().contains(name))
            return false;

        function.put(name, body);
        return true;
    }

    /**
     * 
     * @param name
     * @param var
     * @return
     */
    public boolean addVariable(String name, Variable var)
    {
        if(variable.keySet().contains(name))
            return false;

        variable.put(name, var);
        return true;
    }

    /**
     * 
     * @param name
     * @return
     */
    public boolean deleteVariable(String name)
    {
        if(!variable.keySet().contains(name)) return false;
        
        variable.remove(name);
        return true;
    }

    /**
     * 
     * @param variableName
     * @return
     */
    public Variable getVariable(String variableName)
    {
        if(!variable.keySet().contains(variableName)) return null;
        return variable.get(variableName);
    }

    /**
     * 
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
