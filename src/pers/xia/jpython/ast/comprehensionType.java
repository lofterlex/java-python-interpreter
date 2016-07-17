// Autogenerated AST node
package pers.xia.jpython.ast;
import pers.xia.jpython.object.PyObject;
import java.io.DataOutputStream;
import java.io.IOException;

public class comprehensionType{
    public exprType target;
    public exprType iter;
    public java.util.List<exprType> ifs;

    public comprehensionType(exprType target, exprType iter,
    java.util.List<exprType> ifs) {
        this.target = target;
        this.iter = iter;
        this.ifs = ifs;
    }

    public String toString() {
        return "comprehensionType";
    }

    public void pickle(DataOutputStream ostream) throws IOException {
    }

    public Object accept(VisitorIF visitor) throws Exception {
        traverse(visitor);
        return null;
    }

    public void traverse(VisitorIF visitor) throws Exception {
    }

}
