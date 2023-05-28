package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.object.*;

public class GTEConditionExpression extends OpExpression{

    public GTEConditionExpression(Expression lhs, Expression rhs){
        super(lhs, rhs);
    }

    @Override
    public Object accept(ProgramState programState, ExpVisitor v) {
        Object lhs = this.lhs.accept(programState,v);
        Object rhs = this.rhs.accept(programState, v);
        boolean res = false;
        if(lhs instanceof PyUnicode && rhs instanceof PyUnicode){
            res = ((PyUnicode) lhs).compare((PyUnicode) rhs) >= 0;
        }else{
            PyObject p= ((PyObject)lhs).sub((PyObject)rhs);
            if(p instanceof PyLong){
                res = ((PyLong) p).asLong() >= 0;
            }
            else if(p instanceof PyFloat){
                res = ((PyFloat) p).asFloat() >= 0;
            }
        }
        return new PyBoolean(res);
    }
}
