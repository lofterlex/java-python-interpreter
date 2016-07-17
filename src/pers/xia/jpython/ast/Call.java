// Autogenerated AST node
package pers.xia.jpython.ast;
import pers.xia.jpython.object.PyObject;
import java.io.DataOutputStream;
import java.io.IOException;

public class Call extends exprType {
    public exprType func;
    public java.util.List<exprType> args;
    public java.util.List<keywordType> keywords;

    public Call(exprType func, java.util.List<exprType> args,
    java.util.List<keywordType> keywords,int lineno, int col_offset) {
        this.func = func;
        this.args = args;
        this.keywords = keywords;
        this.lineno = lineno;
        this.col_offset = col_offset;
    }

    public String toString() {
        return "Call";
    }

    public void pickle(DataOutputStream ostream) throws IOException {
    }

    public Object accept(VisitorIF visitor) throws Exception {
        return visitor.visitCall(this);
    }

    public void traverse(VisitorIF visitor) throws Exception {
    }

}
