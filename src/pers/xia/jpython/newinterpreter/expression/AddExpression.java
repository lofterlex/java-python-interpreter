package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.object.*;

public class AddExpression extends OpExpression {

    public AddExpression(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public Object accept(ProgramState programState, ExpVisitor v) {
        Object lhs = this.lhs.accept(programState, v);
        Object rhs = this.rhs.accept(programState, v);
        if(lhs instanceof PyUnicode || rhs instanceof PyUnicode){
            return new PyUnicode( (lhs.toString() + rhs.toString()).getBytes(), "utf-8");
        }else if(lhs instanceof PyFloat) {
            if(rhs instanceof PyFloat){
                return new PyFloat(((PyFloat) lhs).asFloat()+((PyFloat) rhs).asFloat());
            }else if(rhs instanceof PyLong){
                return new PyFloat(((PyFloat) lhs).asFloat()+((PyLong) rhs).asLong());
            }
        }else if(lhs instanceof PyLong){
            if(rhs instanceof PyFloat){
                return new PyFloat(((PyLong) lhs).asLong()+((PyFloat) rhs).asFloat());
            }else if(rhs instanceof PyLong){
                return new PyLong(((PyLong) lhs).asLong()+((PyLong) rhs).asLong());
            }
        }
        return new PyNone();
    }
}
