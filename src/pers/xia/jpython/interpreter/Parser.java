package pers.xia.jpython.interpreter;

import pers.xia.jpython.ast.*;
import pers.xia.jpython.interpreter.expression.DoubleConstantExpression;
import pers.xia.jpython.interpreter.expression.Expression;
import pers.xia.jpython.interpreter.expression.FunctionCallExpression;
import pers.xia.jpython.interpreter.expression.LongConstantExpression;
import pers.xia.jpython.interpreter.statement.AssignStatement;
import pers.xia.jpython.interpreter.statement.Statement;
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

    public Expression<?> parseExpression(exprType expression){
        if(expression instanceof BinOp){

        }
        if(expression instanceof Num){
            Num n = (Num) expression;
            if(n.n instanceof PyLong){
               return new LongConstantExpression(((PyLong) n.n).asLong());
            }
            if (n.n instanceof PyFloat){
                return new DoubleConstantExpression(((PyFloat) n.n).asFloat());
            }
        }
        if(expression instanceof Call){
            String functionName = getNameVal((Name) ((Call) expression).func);
            List<PyObject> pyObjectList = new ArrayList<>();
            for (exprType arg : ((Call) expression).args) {
                if(arg instanceof Name){
                    pyObjectList.add(new PyVar(((Name) arg).getId()));
                }
                if(arg instanceof Str){
                    PyObject s = ((Str) arg).s;
                    pyObjectList.add(s);
                }
                if(arg instanceof Num){
                    Num n = (Num) arg;
                    if(n.n instanceof PyLong){
                        return new LongConstantExpression(((PyLong) n.n).asLong());
                    }
                    if (n.n instanceof PyFloat){
                        return new DoubleConstantExpression(((PyFloat) n.n).asFloat());
                    }
                }
            }
            return createFunctionCallExpression(functionName,pyObjectList);
        }

        return new DoubleConstantExpression(1.2);
    }

    public String getNameVal(Name expr){
        return expr.getId();
    }
}
