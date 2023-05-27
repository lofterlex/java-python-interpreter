package pers.xia.jpython.newinterpreter.statement;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.expression.ExpVisitor;

import java.util.List;

public class DefineFunctionStatement implements Statement {
    private String functionName;
    private List<String> parameterNames;
    private List<Statement> functionStatements;

    public DefineFunctionStatement(String functionName, List<String> parameterNames, List<Statement> functionStatements){
        this.functionName = functionName;
        this.parameterNames = parameterNames;
        this.functionStatements = functionStatements;
    }

    @Override
    public void run(ProgramState programState, ExpVisitor expVisitor) {
        programState.registerFunction(functionName, parameterNames, functionStatements);
    }


}
