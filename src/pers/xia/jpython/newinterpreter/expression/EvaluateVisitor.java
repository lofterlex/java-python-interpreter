package pers.xia.jpython.newinterpreter.expression;

import pers.xia.jpython.newinterpreter.FunctionState;
import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.statement.Statement;
import pers.xia.jpython.object.PyObject;
import pers.xia.jpython.object.PyVar;

import java.util.List;
import java.util.Stack;

public class EvaluateVisitor implements ExpVisitor{

    @Override
    public Object constantVisit(ProgramState programState, Expression expression) {
        return expression.val;
    }

    @Override
    public Object varVisit(ProgramState programState, Expression expression){
        return programState.getVariable(expression.val.toString());
    }

    @Override
    public Object functionVisit(ProgramState programState, FunctionCallExpression expression) {
        Stack<FunctionState> FunctionStateStack = programState.getFunctionStateStack();
        //1. FunctionObj 压入栈
        FunctionState functionState = new FunctionState();
        //2. 创建一个新的ProgramState 实例对象，更新变量
        List<Statement> functionStatements = programState.getFunctionStatements(expression.functionName);
        List<String> parameterNames = programState.getParameterNames(expression.functionName);

        //2.2 遍历 parameterValue 获取入参数值。
        for (int i = 0; i < expression.parameterValue.size(); i++) {
            Expression param = expression.parameterValue.get(i);
            PyObject val = (PyObject) param.accept(programState,this);
            if(val instanceof PyVar){
                val = programState.getVariable(((PyVar) val).getVarName());
            }
            String valName = parameterNames.get(i);
            functionState.updateVariable(valName,val);
        }
        FunctionStateStack.push(functionState);
        for (Statement functionStatement : functionStatements) {
            //2.3在returnExpression 中更新 返回值
            functionStatement.run(functionState, this);
        }
        //3. 按顺序出栈FuncStack，获取返回值。
        return FunctionStateStack.pop().getReturnValue();
    }

}
