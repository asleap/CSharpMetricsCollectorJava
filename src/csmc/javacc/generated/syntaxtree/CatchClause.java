//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> <CATCH>
 * f1 -> ExceptionSpecifierOpt()
 * f2 -> ExceptionFilterOpt()
 * f3 -> Block()
 */
public class CatchClause implements Node {
   public NodeToken f0;
   public ExceptionSpecifierOpt f1;
   public ExceptionFilterOpt f2;
   public Block f3;

   public CatchClause(NodeToken n0, ExceptionSpecifierOpt n1, ExceptionFilterOpt n2, Block n3) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
   }

   public CatchClause(ExceptionSpecifierOpt n0, ExceptionFilterOpt n1, Block n2) {
      f0 = new NodeToken("catch");
      f1 = n0;
      f2 = n1;
      f3 = n2;
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

