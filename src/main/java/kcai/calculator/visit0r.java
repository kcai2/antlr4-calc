package kcai.calculator;

import java.util.*;

public class visit0r extends calculatorBaseVisitor<Double> {
    
    HashMap<String, Double> variable = new HashMap<String, Double>();
    
    @Override
    public Double visitPrintExpr(calculatorParser.PrintExprContext ctx) {
        //same thing as expressoin in other gramamr?
//        return visitChildren(ctx);
        System.out.println("expression called");

//        if (ctx.getText().equals("VARIABLE")) {
//            
//        }
        
        Double value = visit(ctx.expr());
        System.out.println(value);
        return value;
    }
    
    /****************************************************************************************88
     * problem here
     ******************************************************************************************/
    @Override
    public Double visitAssign(calculatorParser.AssignContext ctx) {
        System.out.println("assign variable values");
        //assigns value to variable
        
        if (ctx.getText().equals("EQ")) {
            String key = ctx.VARIABLE().getText();
            Double value = visit(ctx.expr());
            System.out.println("VALUE: " + value);
            if (value != null) {
                variable.put(key, value);
                return null;
            } else {
                return null;
            }
        } else {
            return visit(ctx.expr());
        }
        
//        String key = ctx.getText();
//        Double value = visit(ctx.expr());
//        variable.put(key, value);
//        return value;
       
//        String key = ctx.VARIABLE().getText();
//        System.out.println("key asdfj;laksd;flk: " + key);
//        
//        //Double value = Double.parseDouble(visit(ctx.getText()));
//        Double value = visit(ctx.expr());
//        System.out.println("value asdfadsfasdf: " + value);
//        
//        variable.put(key, value);
//        
//        
//        // TESTING
//        if (variable.containsKey(key)) {
//            System.out.println("containskey: " + variable.get(key));
//        } else {
//            System.out.println("not storing");
//        }
//        
//        if (variable.containsValue(value)) {
//            System.out.println("containskey: " + variable.get(key));
//        } else {
//            System.out.println("nothing working");
//        }
        
        // TESTING
        
        //return value;
    }
    
// done
    @Override 
    public Double visitSigned(calculatorParser.SignedContext ctx) {
        // if minus symbol exists, return the negative value (number * -1)
        //if (ctx.op.getType() == calculatorParser.MINUS) 
        if (ctx.op.getText().equals("-"))
            return (visit(ctx.atom()) * -1);
        else return visit(ctx.atom()); // positive number
    }
    
// done    
    @Override 
    public Double visitAddSub(calculatorParser.AddSubContext ctx) {
        Double value1 = visit(ctx.expr(0));
        Double value2 = visit(ctx.expr(1));
        
        if (ctx.op.getText().equals("-"))
            return value1 - value2;
        else
            return value1 + value2;
        //return result;
    }
    
// done    
    @Override 
    public Double visitMulDiv(calculatorParser.MulDivContext ctx) {
        Double value1 = visit(ctx.expr(0));
        Double value2 = visit(ctx.expr(1));
        
        if (ctx.op.getText().equals("*")) 
            return value1 * value2;
        else 
            return value1 / value2;
    }
    
// done    
    @Override
    public Double visitPower(calculatorParser.PowerContext ctx) {
        Double num = visit(ctx.expr(0));
        Double exp = visit(ctx.expr(1));
        Double result = Math.pow(num, exp);
        return result;
    }
    
/*********************************************************************************************
* ???? is this needed?
*********************************************************************************************/
//    @Override
//    public Double visitAtoms(calculatorParser.AtomsContext ctx) {
//        System.out.println("atom called?");
//        if (ctx.getText().equals("SCIENTIFIC_NUMBER")) {
//            System.out.println("TesTEstsetsetsSCIFI" + ctx.getText());
//            return Double.valueOf(ctx.getText());
//            
//        } else if (ctx.getText().equals("VARIABLE")) {
//            System.out.println("TesTEstsetsVARIABLE" + ctx.getText());
//            return Double.valueOf(ctx.getText());
//            
//        } else if (ctx.getText().equals("INT")) {
//            System.out.println("TesTEstsetsetsINT" + ctx.getText());
//            return visit(ctx.atom());
//        } else {
//            System.out.println("shalom");
//            return visitChildren(ctx);
//        }
//        
//    }
    
// done i think    
    @Override 
    public Double visitLogarithm(calculatorParser.LogarithmContext ctx) {
        if (ctx.expr().size() > 1) {
            Double base = visit(ctx.expr(0));
            Double exp = visit(ctx.expr(1));
            //Double result = Math.log10(exp); //log base 10 def, but if user specify base ..            
            Double result = Math.log(exp)/Math.log(base); //https://stackoverflow.com/questions/13831150/logarithm-algorithm - used this for equation
            return result;
        } else {
            Double exp = visit(ctx.expr(0));
            //default base 10 if no more args
            Double result = Math.log10(exp);
            return result;
        }
    }
    
// done    
    @Override
    public Double visitNaturalLogarithm(calculatorParser.NaturalLogarithmContext ctx) {
        //java ln is just log..?
        double result = Math.log(visit(ctx.expr()));
        return result;
    }
    
// done    
    @Override
    public Double visitSquareRoot(calculatorParser.SquareRootContext ctx) {
        double result = Math.sqrt(visit(ctx.expr()));
        return result;
    }
    
// done    
    @Override
    public Double visitSine(calculatorParser.SineContext ctx) {
        Double result = Math.sin(visit(ctx.expr()));
        return result;
    }
    
// done    
    @Override
    public Double visitASine(calculatorParser.ASineContext ctx) {
        Double result = Math.asin(visit(ctx.expr()));
        return result;
    }
    
// acos(pi/2) returns Nan..?
// google calculator returns error, so assuming this works for now..
    @Override
    public Double visitCosine(calculatorParser.CosineContext ctx) {
        Double result = Math.cos(visit(ctx.expr()));
        return result;
    }
    
// acos(pi/2) returns Nan..?
// google calculator returns error, so assuming this works for now..
    @Override
    public Double visitACosine(calculatorParser.ACosineContext ctx) {
        Double result = Math.acos(visit(ctx.expr()));
        return result;
    }
    
// done    
    @Override 
    public Double visitTangent(calculatorParser.TangentContext ctx) {
        Double result = Math.tan(visit(ctx.expr()));
        return result;
    }
    
// same error as acos/asin but assuming finished    
    @Override
    public Double visitATangent(calculatorParser.ATangentContext ctx) {
        Double result = Math.atan(visit(ctx.expr()));
        return result;
    }
    
// works fine    
    @Override
    public Double visitInteger(calculatorParser.IntegerContext ctx) {
        return Double.valueOf(ctx.getText());
    }
    
// works fine    
    @Override
    public Double visitDouble(calculatorParser.DoubleContext ctx) {
        return Double.valueOf(ctx.getText());
    }
    
// done    
    @Override
    public Double visitConstantPI(calculatorParser.ConstantPIContext ctx) {
        return Math.PI;
    }
    
/*******************************************************************************************
 * need to finish, only returns e?
 **************************************************************************************/
    @Override 
    public Double visitConstantE(calculatorParser.ConstantEContext ctx) {
        System.out.println("E1? E2? figure out returning each");
        //returns 2.718281828459045
        return Math.E;
    }
    
    /***************************************************************************************************8
     * not done. grab data from variable HM and return?
     * very messy need to clean code also
     ************************************************************************************************/
    @Override
    public Double visitVariable(calculatorParser.VariableContext ctx) {
        System.out.println("valid id char?");
        System.out.println("var: " + ctx.VARIABLE().getText());
        System.out.println("child?: " + ctx.VARIABLE().getChild(0));
        
      if (ctx.VARIABLE().getChild(0).getText().equals("=")) {
      String key = ctx.VARIABLE().getText();
      Double value = visit(ctx.VARIABLE().getChild(1));
      System.out.println("VALUE: " + value);
      if (value != null) {
          variable.put(key, value);
          return null;
      } else {
          return null;
      }
  } else {
      return 0.0;
  }

        
//        String id = ctx.VARIABLE().getText();
//        if (!variable.containsKey(id)) {
//            System.err.println("using variable without assignment at Line " + ctx.getStart().getLine() + ", Position " + ctx.getStart().getCharPositionInLine());
//            System.exit(0);
//        }
//        return variable.get(id);
        
//        String key = ctx.VARIABLE().getText();
//        System.out.println("VVkey: " + key);
//        Double value = variable.get(key);
//        System.out.println("VVvalue: " + value);
//        
//        variable.put(key, value);
//        
//        if (variable.containsKey(key)) {
//            System.out.println("VVcontainskey: " + key);
//        } else {
//            System.out.println("VVnot storing");
//        }
//        
//
//        
//        if (variable.containsKey(key)) {
//            return variable.get(key);
//        } else {
//            System.out.println("is it this one");
//            return null;
//        }
        
//        String id = ctx.VARIABLE().getText();
//        if (! variable.containsKey(id) ) return variable.get(id);
//        return 0.0;
        
        
    }
    
// done i think    
    @Override
    public Double visitBraces(calculatorParser.BracesContext ctx) {
        // skips to evaluate the expression
        return visit(ctx.expr());
    }   
    
    /***********************************************************************************************
     * unsure what to do with this
     * something with visitConstantE()
     **********************************************************************************************/
    @Override
    public Double visitScientific(calculatorParser.ScientificContext ctx) {
        System.out.println("Scientific");
        
        return 1.0;
    }
    
    
    

}