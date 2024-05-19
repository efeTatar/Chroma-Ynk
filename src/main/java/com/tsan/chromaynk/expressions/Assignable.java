package main.java.com.tsan.chromaynk.expressions;

import main.java.com.tsan.chromaynk.Context;
import main.java.com.tsan.chromaynk.datatypes.Variable;

public interface Assignable {
    
    abstract public Variable getValue(Context context);

}
