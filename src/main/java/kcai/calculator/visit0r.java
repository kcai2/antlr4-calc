package kcai.calculator;

import java.util.*;

//import org.antlr.v4.runtime.*;


public class visit0r extends calculatorBaseVisitor<Double> {
    
    Map<String, Double> variable = new HashMap<String, Double>();
    
    @Override
    public Double visitPrintExpr(calculatorParser.PrintExprContext ctx) {
        return visitChildren(ctx);
    }
    
    @Override
    public Double visitAssign(calculatorParser.AssignContext ctx) {
        return visitChildren(ctx);
    }
    
    @Override 
    public Double visitSigned(calculatorParser.SignedContext ctx) {
        return visitChildren(ctx);
    }
    
    @Override 
    public Double visitAddSub(calculatorParser.AddSubContext ctx) {
        return 1.0;
    }
    
    @Override 
    public Double visitMulDiv(calculatorParser.MulDivContext ctx) {
        return 1.0;
    }
    
    @Override
    public Double visitPower(calculatorParser.PowerContext ctx) {
        return 1.0;
    }
    
    @Override
    public Double visitAtoms(calculatorParser.AtomsContext ctx) {
        return visitChildren(ctx);
    }
    
    @Override
    public Double visitBraces(calculatorParser.BracesContext ctx) {
        return visitChildren(ctx);
    }
    
    @Override 
    public Double visitLogarithm(calculatorParser.LogarithmContext ctx) {
        Double base = visit(ctx.expr(0));
        Double exp = visit(ctx.expr(1));
        Double result = Math.log(exp)/Math.log(base);
        return result;
    }
    
    @Override
    public Double visitNaturalLogarithm(calculatorParser.NaturalLogarithmContext ctx) {
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
    
    @Override
    public Double visitCosine(calculatorParser.CosineContext ctx) {
        Double result = Math.cos(visit(ctx.expr()));
        return result;
    }
    
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
        return 1.0;
    }
    
    @Override
    public Double visitDouble(calculatorParser.DoubleContext ctx) {
        return 1.0;
    }
    
    @Override
    public Double visitConstantPI(calculatorParser.ConstantPIContext ctx) {
        return Math.PI;
    }
    
    @Override 
    public Double visitConstantE(calculatorParser.ConstantEContext ctx) {
        return Math.E;
    }
    
    @Override
    public Double visitVariable(calculatorParser.VariableContext ctx) {
        return visitChildren(ctx);
    }
    
    

}