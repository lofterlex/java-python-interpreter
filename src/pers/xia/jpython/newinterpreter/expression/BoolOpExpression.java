package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.ast.boolopType;
import pers.xia.jpython.ast.exprType;
import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.object.PyBoolean;
import pers.xia.jpython.object.PyObject;

import java.util.List;

public class BoolOpExpression extends Expression{
    List<Expression> values;
    boolopType type;
    public BoolOpExpression(List<Expression> values, boolopType type){
        this.values = values;
        this.type = type;
    }
    @Override
    public Object accept(ProgramState programState, ExpVisitor v) {
        boolean tempRes = this.type==boolopType.And ? true : false;
        for(Expression expression: values){
            boolean boo = ((PyObject)expression.accept(programState, v)).asBoolean();
            tempRes = this.type==boolopType.And ? (tempRes && boo) : (tempRes || boo);
            if(this.type == boolopType.And && !tempRes){return new PyBoolean(false);}
            if(this.type == boolopType.Or && tempRes){return new PyBoolean(true);}
        }
        return new PyBoolean(tempRes);
    }
}
