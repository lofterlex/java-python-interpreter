package pers.xia.jpython.newinterpreter.statement;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.expression.ExpVisitor;
import pers.xia.jpython.newinterpreter.expression.Expression;

import java.util.List;

public class IfStatement extends BlockStatement {
    private Expression expression;
    private List<Statement> body;

    public IfStatement(Expression expression, List<Statement> body) {
        super(expression, body);
    }

    @Override
    public void run(ProgramState programState, ExpVisitor v) {
        ifBlock(programState, v);
    }
}
