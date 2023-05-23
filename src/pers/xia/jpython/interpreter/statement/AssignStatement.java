package pers.xia.jpython.interpreter.statement;

import pers.xia.jpython.ast.OperatorType;
import pers.xia.jpython.interpreter.ProgramState;
import pers.xia.jpython.interpreter.expression.Expression;
import pers.xia.jpython.object.PyObject;

public class AssignStatement implements Statement{
    private String variableName;
    private Expression<?> expression;

    public AssignStatement(String variableName, Expression<?> expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public AssignStatement() {
    }

    @Override
    public void run(ProgramState programState) {
        Object expressionValue = expression.evaluate(programState);
        programState.setVariable(variableName, PyObject.objectToPyObj(expressionValue));
    }
}
