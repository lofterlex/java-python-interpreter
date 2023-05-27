package pers.xia.jpython.object;

public class PyBoolean extends PyObject
{
    boolean b;
    public PyBoolean(boolean b)
    {
        this.b = b;
    }

    @Override
    public boolean asBoolean(){
        return this.b;
    }
    @Override
    public String toString(){
        return b ? "True" : "False";
    }
}
