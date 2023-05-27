package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.object.PyObject;

public abstract class Expression {
    protected PyObject val;
    public Expression(PyObject val){
        this.val = val;
    }
    public Expression(){}
    public abstract Object accept(ProgramState programState, ExpVisitor v);
}
