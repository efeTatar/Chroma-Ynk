package com.tsan.chromaynk.expressions;

import com.tsan.chromaynk.Context;

/**
 * The Expression abstract class<br>
 * 
 * This class is the backbone of the architecture for this language<br>
 * The syntax tree of the Abbas language programs is made up of this class<br>
 * 
 * An expression can either be terminal or non-terminal meaning that it<br>
 * links to other expressions or not
 */
public abstract class Expression {

    abstract public void execute(Context context);
    
}
