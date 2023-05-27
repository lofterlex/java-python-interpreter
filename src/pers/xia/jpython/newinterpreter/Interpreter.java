package pers.xia.jpython.newinterpreter;

import pers.xia.jpython.ast.Module;
import pers.xia.jpython.ast.*;
import pers.xia.jpython.grammar.GramInit;
import pers.xia.jpython.newinterpreter.expression.EvaluateVisitor;
import pers.xia.jpython.newinterpreter.expression.ExpVisitor;
import pers.xia.jpython.newinterpreter.expression.Expression;
import pers.xia.jpython.newinterpreter.statement.*;
import pers.xia.jpython.object.PyExceptions;
import pers.xia.jpython.parser.Ast;
import pers.xia.jpython.parser.Node;
import pers.xia.jpython.parser.ParseToken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Interpreter {
    private final List<Statement> statements = new ArrayList<>();
    public static ProgramState programState = new ProgramState();
    /** initialize the print function */
    static {
        List<String> parNames = new ArrayList<>();
        parNames.add("arg");
        List<Statement> statementList = new ArrayList<>();
        statementList.add(new PrintStatement());
        programState.registerFunction("print",parNames,statementList);
    }
    public Interpreter() {
    }
    public Interpreter(List<stmtType> nodes){
        for (stmtType node : nodes) {
            Statement statement = parseAstNode(node);
            statements.add(statement);
        }
    }
    /** running the program */
    public void runProgram() {
        ExpVisitor visitor = new EvaluateVisitor();
        for (Statement statement: statements) {
            statement.run(programState, visitor);
        }
    }

    private Statement parseAstNode(stmtType node){
        Parser parser = new Parser();
        if(node instanceof Assign){
            String variableName;
            exprType target = ((Assign) node).targets.get(0);
            variableName = parser.getNameVal((Name) target);
            exprType value = ((Assign) node).value;
            Expression expression = parser.parseExpression(value);
            return new AssignStatement(variableName,expression);
        }
        if (node instanceof For){

        }
        if (node instanceof If){
            If ifNode = (If) node;
            Expression test = parser.parseExpression(ifNode.test);
            List<Statement> body = new ArrayList<>();
            for(stmtType stmt: ifNode.body){
                body.add(this.parseAstNode(stmt));
            }
            return new IfStatement(test,body);
        }
        if(node instanceof While){
            While whileNode = (While) node;
            Expression test = parser.parseExpression(whileNode.test);
            List<Statement> body = new ArrayList<>();
            for(stmtType stmt: whileNode.body){
                body.add(this.parseAstNode(stmt));
            }
            return new WhileStatement(test,body);
        }
        if (node instanceof Expr){
            exprType target = ((Expr) node).value;
            Expression expression = parser.parseExpression(target);
            return new ExprStatement(expression);
        }
        return  new EmpytStatement();
    }

    public static void main(String[] args) {
        String fileName = "./source/assigntest.py";
        File file = new File(fileName);
        try
        {
            Node node = ParseToken.parseFile(file, GramInit.grammar, 1);
            Ast ast = new Ast();
            // get the modType
            Module mod = (Module) ast.fromNode(node);
            Interpreter interpreter = new Interpreter(mod.getBody());
            interpreter.runProgram();

        }
        catch (PyExceptions e)
        {
            System.out.println(fileName);
            e.printStackTrace();
            throw e;
        }
    }
}
