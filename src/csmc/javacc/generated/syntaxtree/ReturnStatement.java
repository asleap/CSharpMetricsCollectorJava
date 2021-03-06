//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> <RETURN>
 * f1 -> ExpressionOpt()
 * f2 -> <SEMICOLON>
 */
public class ReturnStatement implements Node {
   public NodeToken f0;
   public ExpressionOpt f1;
   public NodeToken f2;

   public ReturnStatement(NodeToken n0, ExpressionOpt n1, NodeToken n2) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
   }

   public ReturnStatement(ExpressionOpt n0) {
      f0 = new NodeToken("return");
      f1 = n0;
      f2 = new NodeToken(";");
   }

   public void accept(csmc.javacc.generated.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(csmc.javacc.generated.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(csmc.javacc.generated.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(csmc.javacc.generated.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}

