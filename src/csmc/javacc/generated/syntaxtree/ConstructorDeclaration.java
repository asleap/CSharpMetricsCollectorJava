//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> AttributesOpt()
 * f1 -> ConstructorModifierList()
 * f2 -> ConstructorDeclarator()
 * f3 -> ConstructorBody()
 */
public class ConstructorDeclaration implements Node {
   public AttributesOpt f0;
   public ConstructorModifierList f1;
   public ConstructorDeclarator f2;
   public ConstructorBody f3;

   public ConstructorDeclaration(AttributesOpt n0, ConstructorModifierList n1, ConstructorDeclarator n2, ConstructorBody n3) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
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

