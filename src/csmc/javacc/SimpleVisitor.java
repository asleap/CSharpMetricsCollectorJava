package csmc.javacc;

import csmc.javacc.generated.*;

public class SimpleVisitor implements CSharpParserVisitor {

    private void visitAllChildren(SimpleNode node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
    }

    @Override
    public Object visit(SimpleNode node, Object data) {
        throw new RuntimeException("Visited SimpleNode");
    }

    @Override
    public Object visit(ASTInput node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStart node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTCompilationUnit node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExternAliasDirectiveList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUsingDirectiveList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTGlobalAttributesOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNamespaceMemberDeclarationList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExternAliasDirective node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUsingDirective node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUsingAliasDirective node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNamespaceOrTypeName node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNamespaceOrTypeNamePrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeArgumentListOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeArgumentList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeArguments node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreTypeArguments node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeArgument node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypePrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTValueType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStructType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeName node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSimpleType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNumericType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTIntegralType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFloatingPointType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNullableType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNonNullableValueType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEnumType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTReferenceType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTClassType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTArrayType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNonArrayType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTRankSpecifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTRankSpecifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDimSeparatorList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDimSeparator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDelegateType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeParameter node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPointerType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUnmanagedType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTQualifiedAliasMember node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUsingNamespaceDirective node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNamespaceName node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUsingStaticDirective node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTGlobalAttributes node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTGlobalAttributesSectionList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTGlobalAttributesSection node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTGlobalAttributeTargetSpecifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTGlobalAttributeSpecifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAttributeList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAttribute node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreAttributes node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAttributeName node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAttributeArgumentsOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAttributeArguments node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPositionalArgumentListOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPositionalArgumentList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPositionalArgument node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAttributeArgumentExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMorePositionalArguments node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNamedArgumentList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNamedArgument node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreNamedArguments node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNamespaceMemberDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNamespaceDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSemicolonOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTQualifiedIdentifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreIdentifiers node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNamespaceBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTClassDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAttributesOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTClassModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPartialOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeParameterListOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTClassBaseOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeParameterConstraintsClauseList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAttributes node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAttributeSectionList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAttributeSection node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAttributeTargetSpecifierOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAttributeTargetSpecifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAttributeTarget node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTClassModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTClassModifierUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeParameterList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeParameters node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeParametersPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTClassBase node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceTypeList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreInterfaceTypes node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeParameterConstraintsClause node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeParameterConstraints node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPrimaryConstraint node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSecondaryConstraints node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSecondaryConstraintsPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstructorConstraint node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTClassBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTClassMemberDeclarationList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTClassMemberDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstantDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstantModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstantModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstantDeclarators node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstantDeclarator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstantExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreConstantDeclarators node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFieldDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFieldModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFieldModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFieldModifierUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVariableDeclarators node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVariableDeclarator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVariableInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreVariableDeclarators node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMethodDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMethodHeader node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMethodModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMethodModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMethodModifierUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTReturnType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMemberName node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFormalParameterListOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFormalParameterList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFixedParameters node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFixedParameter node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTParameterModifierOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTParameterModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDefaultArgumentOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDefaultArgument node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreFixedParameters node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTParameterArray node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMethodBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStatementListOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStatementList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPropertyDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPropertyModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPropertyModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPropertyBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAccessorDeclarations node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTGetAccessorDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAccessorModifierOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAccessorModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAccessorBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSetAccessorDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPropertyInitializerOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPropertyInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEventDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEventModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEventModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEventModifierUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEventAccessorDeclarations node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAddAccessorDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTRemoveAccessorDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTIndexerDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTIndexerModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTIndexerModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTIndexerModifierUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTIndexerDeclarator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTIndexerBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOperatorDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOperatorModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOperatorModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOperatorModifierUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOperatorDeclarator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUnaryOperatorDeclarator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOverloadableUnaryOperator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTBinaryOperatorDeclarator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOverloadableBinaryOperator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConversionOperatorDeclarator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOperatorBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstructorDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstructorModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstructorModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstructorModifierUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstructorDeclarator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstructorInitializerOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstructorInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTArgumentListOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTArgumentList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTArgument node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreArguments node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTArgumentNameOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTArgumentName node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTArgumentValue node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVariableReference node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstructorBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDestructorDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExternOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDestructorBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDestructorDeclarationUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUnsafeOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStaticConstructorDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStaticConstructorModifiers node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStaticConstructorModifiersUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStaticConstructorBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStructDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStructModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStructModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStructModifierUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStructInterfacesOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStructInterfaces node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStructBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStructMemberDeclarationList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStructMemberDeclarationUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFixedSizedBufferDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFixedSizeBufferModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFixedSizeBufferModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTBufferElementType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFixedSizeBufferDeclaratorList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFixedSizeBufferDeclarator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceModifierUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVariantTypeParameterListOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVariantTypeParameterList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVariantTypeParameters node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVariantTypeParametersPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVarianceAnnotationOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVarianceAnnotation node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceBaseOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceBase node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceMemberDeclarationList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceMemberDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceMethodDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNewOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfacePropertyDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceAccessors node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceEventDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceIndexerDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEnumDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEnumModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEnumModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEnumBaseOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEnumBase node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEnumBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEnumMemberDeclarations node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreEnumMemberDeclarations node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEnumMemberDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDelegateDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDelegateModifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDelegateModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDelegateModifierUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNonAssignmentExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConditionalExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNullCoalescingExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConditionalOrExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConditionalOrExpressionPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConditionalAndExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConditionalAndExpressionPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInclusiveOrExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInclusiveOrExpressionPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExclusiveOrExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExclusiveOrExpressionPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAndExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAndExpressionPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEqualityExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEqualityExpressionPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTRelationalExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTRelationalExpressionPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTShiftExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTShiftExpressionPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAdditiveExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAdditiveExpressionPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMultiplicativeExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMultiplicativeExpressionPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUnaryExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPrimaryExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPrimaryExpressionPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPrimaryNoArrayCreationExpressionPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPrimaryNoArrayCreationExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPrimaryNoArrayCreationExpressionPrime2 node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLiteral node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSimpleName node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTParanthesizedExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMemberAccess node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInvocationExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTElementAccess node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExpressionList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreExpressions node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTThisAccess node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTBaseAccess node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPostIncrementExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPostDecrementExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTObjectCreationExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTObjectOrCollectionInitializerOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTObjectOrCollectionInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTObjectInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMemberInitializerListOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMemberInitializerList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreMemberInitializers node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMemberInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInitializerTarget node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInitializerValue node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTCollectionInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTElementInitializerList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreElementInitializers node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTElementInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDelegateCreationExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAnonymousObjectCreationExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAnonymousObjectInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMemberDeclarattorListOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMemberDeclaratorList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreMemberDeclarators node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMemberDeclarator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNullConditionalMemberAccess node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNullConditionalOperationsOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNullConditionalOperations node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNullConditionalOperationsPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeofExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUnboundTypeName node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUnboundTypeNamePrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTGenericDimensionSpecifierOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTGenericDimensionSpecifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTCommaList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTCheckedExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUncheckedExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDefaultValueExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNameofExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNamedEntity node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNamedEntityPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNamedEntityTargetPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNamedEntityTarget node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPredefinedType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAnonymousMethodExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExplicitAnonymousFunctionSignatureOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExplicitAnonymousFunctionSignature node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExplicitAnonymousFunctionParameterListOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExplicitAnonymousFunctionParameterList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreExplicitAnonymousFunctionParameters node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExplicitAnonymousFunctionParameter node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAnonymousFunctionParameterModifierOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAnonymousFunctionParameterModifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPrimaryNoArrayCreationExpressionUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPointerMemberAccess node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPointerElementAccess node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSizeofExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTArrayCreationExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTRankSpecifierListOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTArrayInitializerOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTArrayInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVariableInitializerListOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVariableInitializerList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreVariableInitializers node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNullConditionalExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPreIncrementExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPreDecrementExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTCastExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAwaitExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUnaryExpressionUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPointerIndirectionExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAddressofExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLambdaExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAnonymousFunctionSignature node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTImplicitAnonymousFunctionSignature node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTImplicitAnonymousFunctionParameterListOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTImplicitAnonymousFunctionParameterList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreImplicitAnonymousFunctionParameters node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTImplicitAnonymousFunctionParameter node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAnonymousFunctionBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTQueryExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFromClause node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTQueryBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTQueryBodyClausesOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTQueryContinuationOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTQueryBodyClauses node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTQueryBodyClause node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLetClause node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTWhereClause node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTBooleanExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTJoinClause node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTJoinIntoClause node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOrderbyClause node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOrderings node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreOrderings node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOrdering node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOrderingDirectionOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOrderingDirection node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSelectOrGroupClause node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSelectClause node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTGroupClause node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTQueryContinuation node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAssignment node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAssignmentOperator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLabeledStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDeclarationStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLocalVariableDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLocalVariableType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLocalVariableDeclarators node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLocalVariableDeclaratorsPrime node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLocalVariableDeclarator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLocalVariableInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLocalVariableInitializerUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStackallocInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLocalConstantDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEmbeddedStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTBlock node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEmptyStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExpressionStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStatementExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTNullConditionalInvocationExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSelectionStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTIfStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSwitchStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSwitchBlock node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSwitchSectionList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSwitchSection node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSwitchLabelList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSwitchLabel node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTIterationStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTWhileStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDoStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTForStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTForInitializerOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTForConditionOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTForIteratorOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTForInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStatementExpressionList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreStatementExpressions node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTForCondition node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTForIterator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTForeachStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTJumpStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTBreakStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTContinueStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTGotoStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTReturnStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExpressionOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTThrowStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTryStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTCatchClauseList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTCatchClause node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExceptionSpecifierOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExceptionFilterOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExceptionSpecifier node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTIdentifierOpt node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTExceptionFilter node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFinallyClause node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTCheckedStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUncheckedStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLockStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUsingStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTResourceAcquisition node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTYieldStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTEmbeddedStatementUnsafe node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTUnsafeStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFixedStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFixedPointerDeclarators node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMoreFixedPointerDeclarators node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFixedPointerDeclarator node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFixedPointerInitializer node, Object data) {
        visitAllChildren(node, data);
        return data;
    }
}
