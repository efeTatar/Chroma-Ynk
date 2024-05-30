package com.tsan.chromaynk.expressions;

import com.tsan.chromaynk.Context;
import com.tsan.chromaynk.datatypes.Variable;

/**
 * The assignable interface<br>
 * 
 * This interface plays a role in computing operations<br>
 * An expression has to implement this interface to be considered as an operand.
 * 
 * Following classes implement this interface:<br>
 * VariableExpression, ValueExpression and FunctionExpression
 */
public interface Assignable {
    
    abstract public Variable getValue(Context context);

}
