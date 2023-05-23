package pers.xia.jpython.interpreter.expression;

import pers.xia.jpython.interpreter.ProgramState;

public class StringBinOpExpression extends Expression<String>{
    public StringBinOpExpression(String valName) {
        super(valName);
    }

    @Override
    public String evaluate(ProgramState programState) {
        return null;
    }
}
