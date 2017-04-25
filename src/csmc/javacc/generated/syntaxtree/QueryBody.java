//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> ( QueryBodyClause() )*
 * f1 -> SelectOrGroupClause()
 * f2 -> QueryContinuationOpt()
 */
public class QueryBody implements Node {
   public NodeListOptional f0;
   public SelectOrGroupClause f1;
   public QueryContinuationOpt f2;

   public QueryBody(NodeListOptional n0, SelectOrGroupClause n1, QueryContinuationOpt n2) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
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
