package pers.xia.jpython.interpreter.expression;

import pers.xia.jpython.ast.boolopType;
import pers.xia.jpython.interpreter.ProgramState;
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
    public PyObject eval(ProgramState programState) {
        // TODO 补充代码返回逻辑运算结果
        boolean tempRes = this.type == boolopType.And;
        for(Expression expression: values){
            boolean boo = (expression.eval(programState)).asBoolean();
            tempRes = this.type==boolopType.And ? (tempRes && boo) : (tempRes || boo);
            if(this.type == boolopType.And && !tempRes){return new PyBoolean(false);}
            if(this.type == boolopType.Or && tempRes){return new PyBoolean(true);}
        }
        return new PyBoolean(tempRes);
    }
}
