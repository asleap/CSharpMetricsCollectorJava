//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> AttributesOpt()
 * f1 -> StaticConstructorModifiers()
 * f2 -> Identifier()
 * f3 -> <LPAREN>
 * f4 -> <RPAREN>
 * f5 -> StaticConstructorBody()
 */
public class StaticConstructorDeclaration implements Node {
   public AttributesOpt f0;
   public StaticConstructorModifiers f1;
   public Identifier f2;
   public NodeToken f3;
   public NodeToken f4;
   public StaticConstructorBody f5;

   public StaticConstructorDeclaration(AttributesOpt n0, StaticConstructorModifiers n1, Identifier n2, NodeToken n3, NodeToken n4, StaticConstructorBody n5) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
      f5 = n5;
   }

   public StaticConstructorDeclaration(AttributesOpt n0, StaticConstructorModifiers n1, Identifier n2, StaticConstructorBody n3) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = new NodeToken("(");
      f4 = new NodeToken(")");
      f5 = n3;
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
