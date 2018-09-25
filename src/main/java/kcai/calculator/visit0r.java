package kcai.calculator;

import java.util.*;

//import org.antlr.v4.runtime.*;


public class visit0r extends calculatorBaseVisitor<Double> {
    
    HashMap<String, Double> variable = new HashMap<String, Double>();
    
    @Override
    public Double visitPrintExpr(calculatorParser.PrintExprContext ctx) {
        //same thing as expressoin in other gramamr?
//        return visitChildren(ctx);
        System.out.println("expression called");
        
        Double value = visit(ctx.expr());
        System.out.println(value);
        return value;
    }
    
    @Override
    public Double visitAssign(calculatorParser.AssignContext ctx) {
        System.out.println("assign variable values");
        //assign value to variable
        String key = ctx.VARIABLE().getText();
        double value = visit(ctx.expr());
        variable.put(key, value);
        return value;
    }
    
    /**
     * need to do signed..
     */
    @Override 
    public Double visitSigned(calculatorParser.SignedContext ctx) {
        return visitChildren(ctx);
    }
    
    @Override 
    public Double visitAddSub(calculatorParser.AddSubContext ctx) {
        System.out.println("add called");
        Double value1 = visit(ctx.expr(0));
        Double value2 = visit(ctx.expr(1));
        if (ctx.op.getType() == calculatorParser.MINUS) 
            return value1 - value2;
        else
            return value1 + value2;
        //return result;
    }
    
    @Override 
    public Double visitMulDiv(calculatorParser.MulDivContext ctx) {
        System.out.println("mult/div called");
        Double value1 = visit(ctx.expr(0));
        Double value2 = visit(ctx.expr(1));
        if (ctx.op.getType() == calculatorParser.TIMES) 
            return value1 * value2;
        else 
            return value1 / value2;
    }
    
    @Override
    public Double visitPower(calculatorParser.PowerContext ctx) {
        Double num = visit(ctx.expr(0));
        Double exp = visit(ctx.expr(1));
        Double result = Math.pow(num, exp);
        System.out.println("POWWW " + result);
        return result;
    }
    
    /*
     * not sure if this is correct?
     * works for now 
     */
    @Override
    public Double visitAtoms(calculatorParser.AtomsContext ctx) {
        System.out.println("atom called?");
        return visitChildren(ctx);
    }
    
    @Override 
    public Double visitLogarithm(calculatorParser.LogarithmContext ctx) {
        if (ctx.expr().size() > 1) {
            Double base = visit(ctx.expr(0));
            Double exp = visit(ctx.expr(1));
            //Double result = Math.log10(exp); //log base 10 def, but if user specify base ..
            //https://stackoverflow.com/questions/13831150/logarithm-algorithm - used this for equation
            Double result = Math.log(exp)/Math.log(base);
            return result;
        } else {
            Double exp = visit(ctx.expr(0));
            //default base 10 if no more args
            Double result = Math.log10(exp);
            return result;
        }
    }
    
    @Override
    public Double visitNaturalLogarithm(calculatorParser.NaturalLogarithmContext ctx) {
        //java ln is just log..?
        double result = Math.log(visit(ctx.expr()));
        return result;
    }
    
    @Override
    public Double visitSquareRoot(calculatorParser.SquareRootContext ctx) {
        double result = Math.sqrt(visit(ctx.expr()));
        return result;
    }
    
    @Override
    public Double visitSine(calculatorParser.SineContext ctx) {
        Double result = Math.sin(visit(ctx.expr()));
        return result;
    }
    
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
    
    @Override 
    public Double visitTangent(calculatorParser.TangentContext ctx) {
        Double result = Math.tan(visit(ctx.expr()));
        return result;
    }
    
    @Override
    public Double visitATangent(calculatorParser.ATangentContext ctx) {
        Double result = Math.atan(visit(ctx.expr()));
        return result;
    }
    
    @Override
    public Double visitInteger(calculatorParser.IntegerContext ctx) {
        System.out.println("INt");
        return Double.valueOf(ctx.getText());
    }
    
    @Override
    public Double visitDouble(calculatorParser.DoubleContext ctx) {
        System.out.println("Double");
        return Double.valueOf(ctx.getText());
    }
    
    @Override
    public Double visitConstantPI(calculatorParser.ConstantPIContext ctx) {
        System.out.println("3.14159");
        return Math.PI;
    }
    
    @Override 
    public Double visitConstantE(calculatorParser.ConstantEContext ctx) {
        System.out.println("E1? E2? figure out returning each");
        return Math.E;
    }
    
    @Override
    public Double visitVariable(calculatorParser.VariableContext ctx) {
        System.out.println("valid id char?");
        return visitChildren(ctx);
    }
    
    /*****************************************************************************************************8
     * requires fix!!!! returns null when parenthesis are involved...
     * UPDATE: WORKS FOR NOW... MAY CAUSE PROBLEMS LATER???
     ****************************************************************************************************/
    @Override
    public Double visitBraces(calculatorParser.BracesContext ctx) {
        System.out.println("parenthesis calle?");
        // returns null...
        //return visitChildren(ctx);
        
        // skips to expression..? but seemingly works
        return visit(ctx.expr());
    }   
    
    /***********************************************************************************************
     * unsure what to do with this
     **********************************************************************************************/
    @Override
    public Double visitScientific(calculatorParser.ScientificContext ctx) {
        System.out.println("Scientific");
        
        return 1.0;
    }
    
    
    

}