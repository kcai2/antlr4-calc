package kcai.calculator;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class App 
{
    public static void main (String[] args) throws IOException {
        
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        calculatorLexer lexer = new calculatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        calculatorParser parser = new calculatorParser(tokens);
        ParseTree ptree = parser.expr();
        
        visit0r visit = new visit0r();
        Double result = visit.visit(ptree);
        System.out.println(result);
    }
}
