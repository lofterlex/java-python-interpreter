package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.ast.boolopType;
import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.object.*;

public class EqualExpression extends OpExpression{

    boolean equal;

    public EqualExpression(Expression lhs, Expression rhs, boolean equal) {
        super(lhs, rhs);
        this.equal = equal;
    }

    @Override
    public Object accept(ProgramState programState, ExpVisitor v) {
        Object lhs = this.lhs.accept(programState,v);
        Object rhs = this.rhs.accept(programState, v);
        boolean res;
        if(lhs instanceof PyUnicode && rhs instanceof PyUnicode){
            res = ((PyUnicode) lhs).compare((PyUnicode) rhs) != 0;
        }else{
            res = !((PyObject)lhs).equals((PyObject)rhs);
        }
        return new PyBoolean(equal ? res : !res);
    }
}
