package pers.xia.jpython.newinterpreter.statement;
import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.expression.ExpVisitor;
import pers.xia.jpython.newinterpreter.expression.Expression;
import pers.xia.jpython.object.PyLong;
import pers.xia.jpython.object.PyObject;

import java.util.List;

public class ForStatement extends BlockStatement {
    private List<Statement> body;
    private String loopVariable;
    private Expression rangeStart;
    private Expression rangeEnd;

    public ForStatement(String loopVariable, Expression rangeStart, Expression rangeEnd, List<Statement> body) {
        super(null, body);
        this.loopVariable = loopVariable;
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    @Override
    public void run(ProgramState programState, ExpVisitor v) {
        programState.setVariable(loopVariable, (PyObject) rangeStart.accept(programState, v));
        for(long i = ((PyLong)programState.getVariable(loopVariable)).asLong(); i < ((PyLong)rangeEnd.accept(programState, v)).asLong(); i++) {
            forBlock(programState, v);
            programState.setVariable(loopVariable, new PyLong(i+1));
        }
    }
}
