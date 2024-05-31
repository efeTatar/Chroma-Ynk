package com.tsan.chromaynk.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsan.chromaynk.Context;
import com.tsan.chromaynk.datatypes.Num;
import com.tsan.chromaynk.datatypes.Variable;

import javafx.application.Platform;

public class InstructionExpression extends TerminalExpression{

    private List<Assignable> arguments;
    private String name;
    private boolean percent1, percent2;

    public InstructionExpression(String name, List<Assignable> arguments, boolean p1, boolean p2)
    {
        this.name = name;
        this.arguments = arguments;
        this.percent1 = p1;
        this.percent2 = p2;
    }

    public void execute(Context context)
    {
        if(context.getController() == null)
        {
            System.out.println("Warning: the interpreter was not presented with an InterfaceController");
            return;
        }

        List<Variable> variable = new ArrayList<Variable>();

        for(int i = 0 ; i < arguments.size() ; i++)
        {
            variable.add(arguments.get(i).getValue(context));
        }

        

        try {
            Thread.sleep(context.speed);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Platform.runLater(() -> {
            
            Num var, var2;
            double value, value2;

            switch (name) {
		    case "FWD":
		        if(variable.size() < 1) return;
		        var = ((Num)variable.get(0));
		        if(var == null) return;
		        value = percent1 ? context.getController().getWidth() * var.getValue() / 100 : var.getValue();
		        context.getController().FWD(context, value);
		        break;
		    
		    case "BWD":
		        if(variable.size() < 1) return;
		        var = ((Num)variable.get(0));
		        if(var == null) return;
		        value = percent1 ? context.getController().getWidth() * var.getValue() / 100 : var.getValue();
		        context.getController().FWD(context, -value);
		        break;
		    
		    case "MOV":
		        if(variable.size() < 2) return;
		        var = ((Num)variable.get(0));
		        var2 = ((Num)variable.get(0));
		        if(var == null || var2 == null) return;
		        value = percent1 ? context.getController().getWidth() * var.getValue() / 100 : var.getValue();
		        value2 = percent2 ? context.getController().getHeight() * var2.getValue() / 100 : var2.getValue();
		        context.getController().MOV(context, value, value2);
		        break;
		    
		    case "TURN":
		        if(variable.size() < 1) return;
		        context.getController().TURN(context, ((Num)variable.get(0)).getValue());
		        break;
		    
		    case "POS":
		        if(variable.size() < 2) return;
		        var = ((Num)variable.get(0));
		        var2 = ((Num)variable.get(1));
		        if(var == null || var2 == null) return;
		        value = percent1 ? context.getController().getWidth() * var.getValue() / 100 : var.getValue();
		        value2 = percent2 ? context.getController().getHeight() * var2.getValue() / 100 : var2.getValue();
		        context.getController().POS(context, value, value2);
		        break;
		    
		    case "HIDE":
		        break;
		    
		    case "SHOW":
		        break;
		    
		    case "PRESS":
		        if(variable.size() < 1) return;
		        context.getController().PRESS(((Num)variable.get(0)).getValue());
		        break;
		    
		    case "COLOR":
		        if(variable.size() < 3) return;
		        context.getController().COLORRGB(((Num)variable.get(0)).getValue(), ((Num)variable.get(1)).getValue(), ((Num)variable.get(2)).getValue());
		        break;
		    
		    case "THICK":
		        if(variable.size() < 1) return;
		        context.getController().THICK(((Num)variable.get(0)).getValue());
		        break;
		    
		    case "LOOKAT":
		        if(variable.size() < 2) return;
		        var = ((Num)variable.get(0));
		        var2 = ((Num)variable.get(0));
		        if(var == null || var2 == null) return;
		        value = percent1 ? context.getController().getWidth() * var.getValue() / 100 : var.getValue();
		        value2 = percent2 ? context.getController().getHeight() * var2.getValue() / 100 : var2.getValue();
		        context.getController().LOOKAT(context, value, value2);
		        break;

		    default:
		        System.out.println("Unvalid instruction: " + name);
		        return;
		}
        });
    }
    
}
