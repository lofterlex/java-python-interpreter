package pers.xia.jpython.interpreter.expression;

import pers.xia.jpython.interpreter.ProgramState;
import pers.xia.jpython.object.PyFloat;
import pers.xia.jpython.object.PyLong;
import pers.xia.jpython.object.PyObject;

public class DoubleConstantExpression extends Expression<Double>{
    double val;

    public DoubleConstantExpression(double val){
        this.val = val;
    }

    @Override
    public Double evaluate(ProgramState programState) {
        return val;
    }


}
