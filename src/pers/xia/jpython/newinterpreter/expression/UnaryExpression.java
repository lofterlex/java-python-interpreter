package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.ast.UnaryOp;
import pers.xia.jpython.ast.unaryopType;
import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.object.PyBoolean;
import pers.xia.jpython.object.PyObject;

public class UnaryExpression extends Expression{
    Expression operand;
    unaryopType type;
    public UnaryExpression(Expression operand,unaryopType type){
        this.operand = operand;
        this.type = type;
    }

    @Override
    public Object accept(ProgramState programState, ExpVisitor v) {
        PyObject res = (PyObject) operand.accept(programState,v);
        switch (type){
            case UAdd:
                return res;
            case Not:
                return new PyBoolean(!res.asBoolean());
            case USub:
                return res.uSub();
            default:
                return res; // 未实现
        }

    }
}
