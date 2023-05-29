package pers.xia.jpython.newinterpreter.statement;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.expression.ExpVisitor;

public class EmpytStatement implements Statement {
    @Override
    public void run(ProgramState programState, ExpVisitor expVisitor) {
        throw new RuntimeException("unknow Statement error exception");
    }
}
