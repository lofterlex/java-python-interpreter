package pers.xia.jpython.object;

import pers.xia.jpython.interpreter.expression.DoubleConstantExpression;

public abstract class PyObject
{
//    public abstract PyObjType getType();
    public static PyObject objectToPyObj(Object obj){
        if (obj instanceof Long){
            return new PyLong((long)obj);
        }
        if(obj instanceof Double){
            return new PyFloat((double) obj);
        }
        if(obj instanceof String){
            return new PyString((String) obj);
        }
        return new PyNone();
    }
}
