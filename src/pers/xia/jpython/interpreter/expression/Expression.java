package pers.xia.jpython.interpreter.expression;

import pers.xia.jpython.ast.Str;
import pers.xia.jpython.interpreter.ProgramState;

public abstract class Expression<T> {
    protected String valName;
    public Expression(String valName){
        this.valName = valName;
    }
    public Expression(){}
    public abstract T evaluate(ProgramState programState);
}
