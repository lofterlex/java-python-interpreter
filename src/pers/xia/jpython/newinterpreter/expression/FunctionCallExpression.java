package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.newinterpreter.FunctionState;
import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.statement.Statement;
import pers.xia.jpython.object.PyObject;
import pers.xia.jpython.object.PyVar;

import java.util.List;
import java.util.Stack;

public class FunctionCallExpression extends Expression {
    protected String functionName;
    protected List<PyObject> parameterValue;

    public FunctionCallExpression(String functionName, List<PyObject> parameterValue){
        this.functionName = functionName;
        this.parameterValue = parameterValue;
    }

    @Override
    public Object accept(ProgramState programState, ExpVisitor v) {
        return v.functionVisit(programState,this);
    }
}
