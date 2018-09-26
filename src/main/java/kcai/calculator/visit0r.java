package kcai.calculator;

import java.util.*;

public class visit0r extends calculatorBaseVisitor<Double> {
    
    HashMap<String, Double> variable = new HashMap<String, Double>();
    
    @Override
    public Double visitPrintExpr(calculatorParser.PrintExprContext ctx) {
        return visit(ctx.expr());
    }
    
    @Override
    public Double visitAssign(calculatorParser.AssignContext ctx) {
        //assigns value to variable
        String key = ctx.VARIABLE().getText();
        Double value = visit(ctx.expr());
        //System.out.println("key: " + key + ", value: " + value);
        variable.put(key, value);
        return value;
    }

    @Override 
    public Double visitSigned(calculatorParser.SignedContext ctx) {
        // if minus symbol exists, return the negative value (number * -1)
        if (ctx.op.getText().equals("-"))
            return (visit(ctx.atom()) * -1);
        else return visit(ctx.atom()); // positive number
    }
    
    @Override 
    public Double visitAddSub(calculatorParser.AddSubContext ctx) {
        Double value1 = visit(ctx.expr(0)); // first value
        Double value2 = visit(ctx.expr(1)); // second value
        
        if (ctx.op.getText().equals("-"))
            return value1 - value2; // subtract
        else
            return value1 + value2; // add
        //return result;
    }

    @Override 
    public Double visitMulDiv(calculatorParser.MulDivContext ctx) {
        Double value1 = visit(ctx.expr(0)); // first value
        Double value2 = visit(ctx.expr(1)); // second value
        
        if (ctx.op.getText().equals("*")) 
            return value1 * value2; // multiply
        else 
            return value1 / value2; // divide
    }
    
    @Override
    public Double visitPower(calculatorParser.PowerContext ctx) {
        //expr0 = num, expr1 = exponent
        return Math.pow(visit(ctx.expr(0)), visit(ctx.expr(1)));
    }
    
    @Override 
    public Double visitLogarithm(calculatorParser.LogarithmContext ctx) {  
        //expr0 = base, expr1 = number
        return (Math.log(visit(ctx.expr(1)))/Math.log(visit(ctx.expr(0))));
    }
    
    @Override
    public Double visitNaturalLogarithm(calculatorParser.NaturalLogarithmContext ctx) {
        return Math.log(visit(ctx.expr()));
    }
    
    @Override
    public Double visitSquareRoot(calculatorParser.SquareRootContext ctx) {
        return Math.sqrt(visit(ctx.expr()));
    }
    
    @Override
    public Double visitSine(calculatorParser.SineContext ctx) {
        return Math.sin(visit(ctx.expr()));
    }
    
    @Override
    public Double visitASine(calculatorParser.ASineContext ctx) {
        return Math.asin(visit(ctx.expr()));
    }
    
    @Override
    public Double visitCosine(calculatorParser.CosineContext ctx) {
        return Math.cos(visit(ctx.expr()));
    }
    
    @Override
    public Double visitACosine(calculatorParser.ACosineContext ctx) {
        return Math.acos(visit(ctx.expr()));
    }
    

    @Override 
    public Double visitTangent(calculatorParser.TangentContext ctx) {
        return Math.tan(visit(ctx.expr()));
    }
    
    @Override
    public Double visitATangent(calculatorParser.ATangentContext ctx) {
        return Math.atan(visit(ctx.expr()));       
    }
    

    @Override
    public Double visitInteger(calculatorParser.IntegerContext ctx) {
        return Double.valueOf(ctx.getText());
    }
      
    @Override
    public Double visitDouble(calculatorParser.DoubleContext ctx) {
        return Double.valueOf(ctx.getText());
    }
     
    @Override
    public Double visitConstantPI(calculatorParser.ConstantPIContext ctx) {
        return Math.PI;
    }
    
    @Override 
    public Double visitConstantE(calculatorParser.ConstantEContext ctx) {
        System.out.println("E1? E2? figure out returning each");
        //returns 2.718281828459045
        return Math.E;
    }
    
    @Override
    public Double visitVariable(calculatorParser.VariableContext ctx) {        
        String key = ctx.VARIABLE().getText();
        if (variable.containsKey(key)) 
            return variable.get(key);
        else 
            return 0.0;
    }
     
    @Override
    public Double visitBraces(calculatorParser.BracesContext ctx) {
        // skips to evaluate the expression
        return visit(ctx.expr());
    }   
    
    @Override
    public Double visitScientific(calculatorParser.ScientificContext ctx) {
        return Double.valueOf(ctx.getText());
    }
    
    
}