package pers.xia.jpython.newinterpreter.statement;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.expression.ExpVisitor;
import pers.xia.jpython.object.PyObject;

public class PrintStatement implements Statement {
    public PrintStatement() {
    }
    //the varName in VariableHeap.
    private static final String varName = "arg";
    @Override
    public void run(ProgramState programState, ExpVisitor expVisitor) {
        PyObject variable = programState.getVariable(varName);
        printPy(variable);
    }
    public void printPy(PyObject object){
        System.out.println(object.toString());
    }
}
