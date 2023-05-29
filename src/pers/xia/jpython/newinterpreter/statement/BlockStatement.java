package pers.xia.jpython.newinterpreter.statement;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.expression.ExpVisitor;
import pers.xia.jpython.newinterpreter.expression.Expression;
import pers.xia.jpython.object.*;

import java.util.List;

public abstract class BlockStatement implements Statement{
    private Expression expression;
    private List<Statement> body;


    public BlockStatement(Expression expression, List<Statement> body) {
        this.expression = expression;
        this.body = body;
    }

    public void ifBlock(ProgramState programState, ExpVisitor v) {
        boolean res = ((PyObject)expression.accept(programState,v)).asBoolean();
        if(res){
            forBlock(programState,v);
        }
    }

    public void whileLoop(ProgramState programState, ExpVisitor v) {
        while(((PyBoolean)expression.accept(programState,v)).asBoolean()) {
            forBlock(programState,v);
        }
    }


    public void forBlock(ProgramState programState, ExpVisitor v) {
        for (Statement statement: body){
            statement.run(programState,v);
        }
    }

}
