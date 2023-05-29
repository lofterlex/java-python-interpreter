package pers.xia.jpython.newinterpreter.statement;

import pers.xia.jpython.newinterpreter.FunctionState;
import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.expression.ExpVisitor;
import pers.xia.jpython.newinterpreter.expression.Expression;

public class ReturnStatement implements Statement{
    Expression expression;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }
    @Override
    public void run(ProgramState programState, ExpVisitor expVisitor) {
        Object expressionValue = expression.accept(programState, expVisitor);
        FunctionState topFunctionState = programState.getTopFunctionState();
        topFunctionState.setReturnValue(expressionValue);
    }
}
