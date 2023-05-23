package pers.xia.jpython.interpreter.expression;

import pers.xia.jpython.ast.OperatorType;
import pers.xia.jpython.interpreter.ProgramState;

public class LongBinOpExpression extends Expression<Long> {
    private Expression<Long> lhs;
    private Expression<Long> rhs;
    private OperatorType operatorType;

    public LongBinOpExpression(Expression<Long> lhs, Expression<Long> rhs,OperatorType operatorType) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.operatorType = operatorType;
    }

    @Override
    public Long evaluate(ProgramState programState) {
        switch (operatorType){
            case Add:
                return lhs.evaluate(programState)+rhs.evaluate(programState);

            case Sub:
                return lhs.evaluate(programState)-rhs.evaluate(programState);

            case Mult:
                return lhs.evaluate(programState)*rhs.evaluate(programState);

            case Div:
                return lhs.evaluate(programState)/rhs.evaluate(programState);

            default:
                programState.updateException("type unimplement error");
                return 0L;
        }
    }
}
