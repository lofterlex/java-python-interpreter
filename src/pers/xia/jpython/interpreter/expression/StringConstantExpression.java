package pers.xia.jpython.interpreter.expression;

import pers.xia.jpython.interpreter.ProgramState;

public class StringConstantExpression extends Expression<String> {

    public StringConstantExpression(String valName) {
        super(valName);
    }

    @Override
    public String evaluate(ProgramState programState) {
        return null;
    }
}
