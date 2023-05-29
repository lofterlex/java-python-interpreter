package pers.xia.jpython.interpreter;

import pers.xia.jpython.ast.*;
import pers.xia.jpython.ast.Module;
import pers.xia.jpython.compiler.Symtable;
import pers.xia.jpython.grammar.GramInit;
import pers.xia.jpython.interpreter.expression.Expression;
import pers.xia.jpython.interpreter.statement.*;
import pers.xia.jpython.object.PyExceptions;
import pers.xia.jpython.parser.Ast;
import pers.xia.jpython.parser.Node;
import pers.xia.jpython.parser.ParseToken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Interpreter {
    private final List<Statement> statements = new ArrayList<>();
//    所有的interpreter 共用一个programstate，这样就可以保存之前的内容。
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
        for (Statement statement: statements) {
            statement.run(programState);
        }
    }

    private Statement parseAstNode(stmtType node){
        Parser parser = new Parser();
        if(node instanceof Assign){
            String variableName;
            exprType target = ((Assign) node).targets.get(0);
            variableName = parser.getNameVal((Name) target);
            exprType value = ((Assign) node).value;
            Expression<?> expression = parser.parseExpression(value);
            return new AssignStatement(variableName,expression);
        }
        if (node instanceof For){
        }
        if (node instanceof If){
        }
        if (node instanceof Expr){
            exprType target = ((Expr) node).value;
            Expression<?> expression = parser.parseExpression(target);
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
