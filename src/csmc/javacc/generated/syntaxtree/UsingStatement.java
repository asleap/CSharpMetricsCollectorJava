//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> <USING>
 * f1 -> <LPAREN>
 * f2 -> ResourceAcquisition()
 * f3 -> <RPAREN>
 * f4 -> EmbeddedStatement()
 */
public class UsingStatement implements Node {
   public NodeToken f0;
   public NodeToken f1;
   public ResourceAcquisition f2;
   public NodeToken f3;
   public EmbeddedStatement f4;

   public UsingStatement(NodeToken n0, NodeToken n1, ResourceAcquisition n2, NodeToken n3, EmbeddedStatement n4) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
   }

   public UsingStatement(ResourceAcquisition n0, EmbeddedStatement n1) {
      f0 = new NodeToken("using");
      f1 = new NodeToken("(");
      f2 = n0;
      f3 = new NodeToken(")");
      f4 = n1;
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
