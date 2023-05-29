package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.object.PyFloat;
import pers.xia.jpython.object.PyLong;
import pers.xia.jpython.object.PyString;
import pers.xia.jpython.object.PyUnicode;

import java.util.Collections;

public class MultiplyExpression extends OpExpression {

    public MultiplyExpression(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public Object accept(ProgramState programState, ExpVisitor v) {
        Object lhs = this.lhs.accept(programState, v);
        Object rhs = this.rhs.accept(programState, v);
        if(lhs instanceof PyLong){
            if(rhs instanceof PyUnicode){
                return new PyString(String.join("", Collections.nCopies((int) ((PyLong)lhs).asLong(), rhs.toString())));
            }else if(rhs instanceof PyLong){
                return new PyLong(((PyLong) lhs).asLong() * ((PyLong) rhs).asLong());
            }else if(rhs instanceof PyFloat){
                return new PyFloat(((PyLong) lhs).asLong() *  ((PyFloat) rhs).asFloat());
            }
        }else if(lhs instanceof PyFloat) {
            if(rhs instanceof PyLong){
                return new PyFloat(((PyFloat) lhs).asFloat() *((PyLong) rhs).asLong());
            }else if(rhs instanceof PyFloat){
                return new PyFloat(((PyFloat) lhs).asFloat() * ((PyFloat) rhs).asFloat());
            }
        }else if(lhs instanceof PyUnicode){
            if(rhs instanceof PyLong){
                return new PyString(String.join("", Collections.nCopies(Math.toIntExact((int) ((PyLong) rhs).asLong()), lhs.toString())));
            }
        }
        programState.updateException("type unimplement error");
        return new PyUnicode("暂未实现".getBytes(),"utf-8");
    }
}
