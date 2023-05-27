package pers.xia.jpython.newinterpreter;

import pers.xia.jpython.ast.*;
import pers.xia.jpython.newinterpreter.expression.*;
import pers.xia.jpython.newinterpreter.expression.Expression;
import pers.xia.jpython.newinterpreter.statement.AssignStatement;
import pers.xia.jpython.newinterpreter.statement.Statement;
import pers.xia.jpython.object.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public Statement createAssignStatement(String variableName, exprType expression){
        return new AssignStatement(variableName,parseExpression(expression));
    }
    public Expression createFunctionCallExpression(String functionName, List<PyObject> parameterValues) {
        return new FunctionCallExpression(functionName, parameterValues);
    }

    public Expression parseExpression(exprType expression){
        if(expression instanceof BinOp){
            BinOp b = (BinOp) expression;
            Expression left = parseExpression(b.left);
            Expression right = parseExpression(b.right);
            switch (b.op){
                case Add:
                    return new AddExpression(left,right);
                case Mult:
                    return new MultiplyExpression(left, right);
                default:
                    return new ConstantExpression(new PyString("暂未实现"));
            }
        }
        if(expression instanceof Compare){
            Compare compare = (Compare) expression;
            Expression left = parseExpression(compare.left);
            Expression right = parseExpression(compare.right);
            switch (compare.ops){
                case Gt:
                    return new GTConditionExpression(left,right);
                case Lt:
                    return new LTConditionExpression(left,right);
                case Eq:
                    return new EqualExpression(left,right);
                case NotEq:
                    return new NotEqualExpression(left,right);
                default:
                    return new ConstantExpression(new PyBoolean(true));
            }

        }
        if(expression instanceof BoolOp){
            BoolOp boolOp = ((BoolOp) expression);
            List<Expression> expressions = new ArrayList<>();
            for(exprType value: boolOp.values){
                expressions.add(parseExpression(value));
            }
            return new BoolOpExpression(expressions,boolOp.op);
        }
        if(expression instanceof Num){
            Num n = (Num) expression;
            return new ConstantExpression(n.n);
        }
        if(expression instanceof Str){
            Str s = (Str) expression;
            return new ConstantExpression(s.s);
        }
        if(expression instanceof Call){
            String functionName = getNameVal((Name) ((Call) expression).func);
            List<PyObject> pyObjectList = new ArrayList<>();
            for (exprType arg : ((Call) expression).args) {
                if(arg instanceof Name){
                    pyObjectList.add(new PyVar(((Name) arg).getId()));
                }
                if(arg instanceof Str){
                    Str s = (Str) arg;
                    pyObjectList.add(s.s);
                }
                if(arg instanceof Num){
                    Num n = (Num) arg;
                    return new ConstantExpression(n.n);
                }
            }
            return createFunctionCallExpression(functionName,pyObjectList);
        }
        if(expression instanceof Name){
            Name n = (Name) expression;
            return new VariableExpression(new PyVar(n.id));
        }

        return new ConstantExpression(new PyLong(123));
    }

    public String getNameVal(Name expr){
        return expr.getId();
    }
}
