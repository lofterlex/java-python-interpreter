package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.ast.OperatorType;

public abstract class OpExpression extends Expression{
    protected Expression lhs;
    protected Expression rhs;

    public OpExpression(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
