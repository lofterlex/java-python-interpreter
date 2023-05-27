package pers.xia.jpython.object;

import pers.xia.jpython.interpreter.Interpreter;
import pers.xia.jpython.interpreter.expression.DoubleConstantExpression;

public class PyFloat extends PyObject
{
    double num;

    public PyFloat(double num)
    {
        this.num = num;
    }

    public PyFloat(String s){
        this.num = Double.parseDouble(s);
    }
    public double asFloat(){
        return  num;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}
