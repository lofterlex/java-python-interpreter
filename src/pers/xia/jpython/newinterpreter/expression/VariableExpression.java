package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.object.PyObject;
import pers.xia.jpython.object.PyVar;

public class VariableExpression extends Expression {

    public VariableExpression(PyObject val) {
        super(val);
    }

    @Override
    public Object accept(ProgramState programState, ExpVisitor v) {
        return v.varVisit(programState,this);
    }
}
