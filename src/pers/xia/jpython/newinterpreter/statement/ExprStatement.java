package pers.xia.jpython.newinterpreter.statement;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.expression.ExpVisitor;
import pers.xia.jpython.newinterpreter.expression.Expression;

public class ExprStatement implements Statement {
    private Expression expression;

    public ExprStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void run(ProgramState programState, ExpVisitor expVisitor) {
        if(expression.accept(programState, expVisitor)!=null){
            System.out.println(expression.accept(programState, expVisitor));
        }
    }
}
