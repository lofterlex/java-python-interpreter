package pers.xia.jpython.newinterpreter.statement;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.expression.ExpVisitor;
import pers.xia.jpython.newinterpreter.expression.Expression;
import pers.xia.jpython.object.PyObject;

public class AssignStatement implements Statement {
    private String variableName;
    private Expression expression;

    public AssignStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }


    @Override
    public void run(ProgramState programState, ExpVisitor expVisitor) {
        Object expressionValue = expression.accept(programState, expVisitor);
        programState.setVariable(variableName, (PyObject) expressionValue);
    }
}
