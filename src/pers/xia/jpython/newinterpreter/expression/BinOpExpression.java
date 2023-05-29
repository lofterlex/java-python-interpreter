package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.ast.OperatorType;
import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.object.*;

public class BinOpExpression extends OpExpression {
    OperatorType operatorType;
    public BinOpExpression(Expression lhs, Expression rhs, OperatorType operatorType)
    {
        super(lhs, rhs);
        this.operatorType = operatorType;
    }

    @Override
    public Object accept(ProgramState programState, ExpVisitor v) {
        Object lhs = this.lhs.accept(programState, v);
        Object rhs = this.rhs.accept(programState, v);
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
