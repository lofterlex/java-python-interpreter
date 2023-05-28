package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.newinterpreter.ProgramState;

public interface ExpVisitor {
    Object constantVisit(ProgramState programState, Expression expression);
    Object varVisit(ProgramState programState, Expression expression);
    Object functionVisit(ProgramState programState, FunctionCallExpression expression);
}
