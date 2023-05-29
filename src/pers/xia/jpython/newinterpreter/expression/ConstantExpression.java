package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.object.PyObject;

public class ConstantExpression extends Expression{

    public ConstantExpression(PyObject n) {
        super(n);
    }

    @Override
    public Object accept(ProgramState programState, ExpVisitor v) {
        return v.constantVisit(programState,this);
    }
}
