//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> <GROUP>
 * f1 -> Expression()
 * f2 -> <BY>
 * f3 -> Expression()
 */
public class GroupClause implements Node {
   public NodeToken f0;
   public Expression f1;
   public NodeToken f2;
   public Expression f3;

   public GroupClause(NodeToken n0, Expression n1, NodeToken n2, Expression n3) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
   }

   public GroupClause(Expression n0, Expression n1) {
      f0 = new NodeToken("group");
      f1 = n0;
      f2 = new NodeToken("by");
      f3 = n1;
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
