package pers.xia.jpython.newinterpreter.statement;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.expression.ExpVisitor;
import pers.xia.jpython.newinterpreter.expression.Expression;

import java.util.List;

public class IfStatement extends BlockStatement {
    // 只有if块的break,continue需要向外层传递
    protected boolean breakFlag = false;
    protected boolean continueFlag = false;


    public IfStatement(Expression expression, List<Statement> body, List<Statement> elseBody) {
        super(expression, body, elseBody);
    }

    @Override
    public void run(ProgramState programState, ExpVisitor v) {
        ifBlock(programState, v);
    }
}
