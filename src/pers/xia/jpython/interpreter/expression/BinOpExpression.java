package pers.xia.jpython.interpreter.expression;

import pers.xia.jpython.ast.OperatorType;
import pers.xia.jpython.interpreter.ProgramState;
import pers.xia.jpython.object.*;

public class BinOpExpression extends OpExpression {
    OperatorType operatorType;
    public BinOpExpression(Expression lhs, Expression rhs, OperatorType operatorType)
    {
        super(lhs, rhs);
        this.operatorType = operatorType;
    }

    @Override
    public Object eval(ProgramState programState) {
        Object lhs = this.lhs.eval(programState);
        Object rhs = this.rhs.eval(programState);
        switch (operatorType){
            case Add:
                return ((PyObject) lhs).add((PyObject) rhs);
            case Sub:
                return ((PyObject) lhs).sub((PyObject) rhs);
            case Mult:
                return ((PyObject) lhs).mul((PyObject) rhs);
            default:
                return new PyString("暂未实现");

        }
    }
}
