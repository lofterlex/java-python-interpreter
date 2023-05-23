package pers.xia.jpython.interpreter.expression;

import pers.xia.jpython.interpreter.ProgramState;
import pers.xia.jpython.object.PyLong;
import pers.xia.jpython.object.PyObject;

public class LongConstantExpression extends Expression<Long>{
    private long val;
    public LongConstantExpression(long val) {
        this.val = val;
    }

    @Override
    public Long evaluate(ProgramState programState) {
        return val;
    }
}
