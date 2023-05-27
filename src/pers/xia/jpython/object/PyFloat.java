package pers.xia.jpython.object;

import pers.xia.jpython.interpreter.expression.DoubleConstantExpression;

public class PyFloat extends PyObject
{
    double num;

    public PyFloat(double num)
    {
        this.num = num;
    }

    public PyFloat(String s){
        this.num = Double.valueOf(s);
    }
    public double asFloat(){
        return  num;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }

    public double compare(PyObject object){
        try{
            if(object instanceof PyLong){
                return this.num - ((PyLong) object).asLong();
            }else if(object instanceof PyFloat){
                return this.num - ((PyFloat) object).asFloat();
            }
        }catch (Exception e){
            System.out.println("not support ");
            return -1;
        }
        return -1;
    }

    @Override
    public boolean asBoolean() {
        return this.num != 0;
    }
}
