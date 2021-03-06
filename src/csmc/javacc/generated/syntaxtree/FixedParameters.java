//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> FixedParameter()
 * f1 -> MoreFixedParameters()
 * f2 -> ParameterArrayOpt()
 */
public class FixedParameters implements Node {
   public FixedParameter f0;
   public MoreFixedParameters f1;
   public ParameterArrayOpt f2;

   public FixedParameters(FixedParameter n0, MoreFixedParameters n1, ParameterArrayOpt n2) {
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

