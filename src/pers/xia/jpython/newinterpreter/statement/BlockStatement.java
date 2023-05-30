package pers.xia.jpython.newinterpreter.statement;

import pers.xia.jpython.newinterpreter.ProgramState;
import pers.xia.jpython.newinterpreter.expression.ExpVisitor;
import pers.xia.jpython.newinterpreter.expression.Expression;
import pers.xia.jpython.object.*;

import java.util.ArrayList;
import java.util.List;

public abstract class BlockStatement implements Statement{
    private Expression expression;
    private List<Statement> body;
    private List<Statement> elseBody;


    public BlockStatement(Expression expression, List<Statement> body) {
        this.expression = expression;
        this.body = body;
        this.elseBody = new ArrayList<>();
    }

    public BlockStatement(Expression expression, List<Statement> body, List<Statement> elseBody) {
        this.expression = expression;
        this.body = body;
        this.elseBody = elseBody;
    }

    public void ifBlock(ProgramState programState, ExpVisitor v) {
        boolean res = ((PyObject)expression.accept(programState,v)).asBoolean();
        if(res){
            forBlock(programState,v);
        }else{
            elseBlock(programState, v);
        }
    }

    public void whileLoop(ProgramState programState, ExpVisitor v) {
        while(((PyBoolean)expression.accept(programState,v)).asBoolean()) {
            if(forBlock(programState,v));
            return;
        }
        elseBlock(programState, v);
    }

    // 返回值表示该block是否有需要向外传递的break
    public boolean forBlock(ProgramState programState, ExpVisitor v) {
        for (Statement statement: body){
            if(statement instanceof BreakStatement){
                if(this instanceof IfStatement){
                    ((IfStatement) this).breakFlag = true;
                }
                return true;
            }else if(statement instanceof ContinueStatement){
                if(this instanceof IfStatement){
                    ((IfStatement) this).continueFlag = true;
                }
                return false;
            }
            //run之后 如果是ifStatement,breakFlag可能会发生改变，所以需要再判断一次
            statement.run(programState,v);
            if (statement instanceof IfStatement){
                if(((IfStatement) statement).breakFlag) return true;
                if(((IfStatement) statement).continueFlag) {
                    // 重置continueFlag 以免影响下次判断
                    ((IfStatement) statement).continueFlag = false;
                    return false;
                };
            }
        }
        return false;
    }

    // 返回值表示该block是否有需要向外传递的break
    public boolean elseBlock(ProgramState programState, ExpVisitor v) {
        for (Statement statement: elseBody){
            if(statement instanceof BreakStatement){
                if(this instanceof IfStatement){
                    ((IfStatement) this).breakFlag = true;
                }
                return true;
            }else if(statement instanceof ContinueStatement){
                if(this instanceof IfStatement){
                    ((IfStatement) this).continueFlag = true;
                }
                return false;
            }
            //run之后 如果是ifStatement,breakFlag可能会发生改变，所以需要判断是否跳出
            statement.run(programState,v);
            if (statement instanceof IfStatement){
                if(((IfStatement) statement).breakFlag) return true;
                if(((IfStatement) statement).continueFlag) {
                    // 重置continueFlag 以免影响下次循环判断
                    ((IfStatement) statement).continueFlag = false;
                    return false;
                };
            }
        }
        return false;
    }

}
