package pers.xia.jpython.newinterpreter.statement;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.expression.ExpVisitor;

public interface Statement {
    void run(ProgramState programState,ExpVisitor expVisitor);
}
