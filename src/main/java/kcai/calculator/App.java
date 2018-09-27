package kcai.calculator;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

//import org.jline.reader.LineReader;
//import org.jline.reader.LineReaderBuilder;
//import org.jline.reader.UserInterruptException;
//import org.jline.terminal.TerminalBuilder;
//import org.jline.terminal.Terminal;
//import org.jline.reader.EndOfFileException;

// for GUI astree visual
//import javax.swing.JFrame;
//import org.antlr.v4.gui.TreeViewer;


public class App 
{
    public static void main (String[] args) throws Exception {
        
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        calculatorLexer lexer = new calculatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        calculatorParser parser = new calculatorParser(tokens);
        ParseTree ptree = parser.prog();
        
        visit0r visit = new visit0r();
        Double result = visit.visit(ptree);
        System.out.println(result);
    
        
        /***************************************************************************************************
         //*  Code used to draw out the astree, helped me to visualize where errors were coming from
         //***************************************************************************************************
        
        String expression = "2 + 2 * 5 * 4 / 2";
        
        CharStream stream = CharStreams.fromString(expression);
        calculatorLexer lex = new calculatorLexer(stream);
        TokenStream tks = new CommonTokenStream(lex);
        
        calculatorParser prsr = new calculatorParser(tks);
        ParseTree tree = prsr.expr();
        System.out.println("String tree = " + tree.toStringTree());

        JFrame frame = new JFrame("AST for expression: " + expression);
        TreeViewer treeViewer = new TreeViewer(Arrays.asList(prsr.getRuleNames()), tree);
        treeViewer.setScale(1.5);
        frame.add(treeViewer);
        frame.setSize(640, 480);
        frame.setVisible(true);
        //************************************************************************************/
    }
    
}
