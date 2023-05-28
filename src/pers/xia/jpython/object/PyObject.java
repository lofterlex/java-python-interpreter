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

    public boolean asBoolean(){
        return true;
    }

    public PyObject add(PyObject p){
        throw new PyExceptions(PyExceptions.ErrorType.TYPE_ERROR, "TypeError: unsupported operand type(s) for " + this.getType()+" and "+p.getType());
    }

    public PyObject sub(PyObject p){
        throw new PyExceptions(PyExceptions.ErrorType.TYPE_ERROR, "TypeError: unsupported operand type(s) for " + this.getType()+" and "+p.getType());
    }

    public PyObject mul(PyObject p){
        throw new PyExceptions(PyExceptions.ErrorType.TYPE_ERROR, "TypeError: unsupported operand type(s) for " + this.getType()+" and "+p.getType());
    }

    public boolean equals(PyObject p){
        return false;
    }

    public String getType(){
        return "object";
    }


}
