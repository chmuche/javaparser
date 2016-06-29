package com.github.javaparser.ast.visitor;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.comments.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;
import java.util.List;

public class HashCodeVisitor2 implements GenericVisitor<Integer, Void> {
	
	@Override public Integer visit(PackageDeclaration n, Void arg) {
		return (n.getAnnotations()==null?0:  n.getAnnotations().hashCode())
				* 31 + (n.getName()==null?0:  n.getName().hashCode())
				;
	}
	
	@Override public Integer visit(CompilationUnit n, Void arg) {
		return (n.getPackage()==null?0:  n.getPackage().hashCode())
				* 31 + (n.getImports()==null?0:  n.getImports().hashCode())
				* 31 + (n.getTypes()==null?0:  n.getTypes().hashCode())
				;
	}
	
	@Override public Integer visit(ImportDeclaration n, Void arg) {
		return (n.getName()==null?0:  n.getName().hashCode())
				* 31 + (n.isStatic()?1:0)
				* 31 + (n.isAsterisk()?1:0)
				* 31 + (n.isEmptyImportDeclaration()?1:0)
				;
	}
	
	@Override public Integer visit(TypeParameter n, Void arg) {
		return (n.getName()==null?0:  n.getName().hashCode())
				* 31 + (n.getAnnotations()==null?0:  n.getAnnotations().hashCode())
				* 31 + (n.getTypeBound()==null?0:  n.getTypeBound().hashCode())
				;
	}
	
	@Override public Integer visit(InitializerDeclaration n, Void arg) {
		return (n.isStatic()?1:0)
				* 31 + (n.getBlock()==null?0:  n.getBlock().hashCode())
				* 31 + (n.getJavaDoc()==null?0:  n.getJavaDoc().hashCode())
				;
	}
	
	@Override public Integer visit(VariableDeclaratorId n, Void arg) {
		return (n.getName()==null?0:  n.getName().hashCode())
				* 31 + n.getArrayCount()
				;
	}
	
	@Override public Integer visit(EnumDeclaration n, Void arg) {
		return (n.getImplements()==null?0:  n.getImplements().hashCode())
				* 31 + (n.getEntries()==null?0:  n.getEntries().hashCode())
				* 31 + (n.getJavaDoc()==null?0:  n.getJavaDoc().hashCode())
				;
	}
	
	@Override public Integer visit(MethodDeclaration n, Void arg) {
		return n.getModifiers()
				* 31 + (n.getTypeParameters()==null?0:  n.getTypeParameters().hashCode())
				* 31 + (n.getType()==null?0:  n.getType().hashCode())
				* 31 + (n.getName()==null?0:  n.getName().hashCode())
				* 31 + (n.getParameters()==null?0:  n.getParameters().hashCode())
				* 31 + n.getArrayCount()
				* 31 + (n.getThrows()==null?0:  n.getThrows().hashCode())
				* 31 + (n.getBody()==null?0:  n.getBody().hashCode())
				* 31 + (n.isDefault()?1:0)
				* 31 + (n.getJavaDoc()==null?0:  n.getJavaDoc().hashCode())
				;
	}
	
	@Override public Integer visit(EmptyMemberDeclaration n, Void arg) {
		return (n.getJavaDoc()==null?0:  n.getJavaDoc().hashCode())
				;
	}
	
	@Override public Integer visit(EnumConstantDeclaration n, Void arg) {
		return (n.getName()==null?0:  n.getName().hashCode())
				* 31 + (n.getArgs()==null?0:  n.getArgs().hashCode())
				* 31 + (n.getClassBody()==null?0:  n.getClassBody().hashCode())
				* 31 + (n.getJavaDoc()==null?0:  n.getJavaDoc().hashCode())
				;
	}
	
	@Override public Integer visit(EmptyTypeDeclaration n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(AnnotationMemberDeclaration n, Void arg) {
		return n.getModifiers()
				* 31 + (n.getType()==null?0:  n.getType().hashCode())
				* 31 + (n.getName()==null?0:  n.getName().hashCode())
				* 31 + (n.getDefaultValue()==null?0:  n.getDefaultValue().hashCode())
				* 31 + (n.getJavaDoc()==null?0:  n.getJavaDoc().hashCode())
				;
	}
	
	@Override public Integer visit(MultiTypeParameter n, Void arg) {
		return (n.getType()==null?0:  n.getType().hashCode())
				;
	}
	
	@Override public Integer visit(ClassOrInterfaceDeclaration n, Void arg) {
		return (n.isInterface()?1:0)
				* 31 + (n.getTypeParameters()==null?0:  n.getTypeParameters().hashCode())
				* 31 + (n.getExtends()==null?0:  n.getExtends().hashCode())
				* 31 + (n.getImplements()==null?0:  n.getImplements().hashCode())
				* 31 + (n.getJavaDoc()==null?0:  n.getJavaDoc().hashCode())
				;
	}
	
	@Override public Integer visit(FieldDeclaration n, Void arg) {
		return n.getModifiers()
				* 31 + (n.getType()==null?0:  n.getType().hashCode())
				* 31 + (n.getVariables()==null?0:  n.getVariables().hashCode())
				* 31 + (n.getJavaDoc()==null?0:  n.getJavaDoc().hashCode())
				;
	}
	
	@Override public Integer visit(Parameter n, Void arg) {
		return (n.getType()==null?0:  n.getType().hashCode())
				* 31 + (n.isVarArgs()?1:0)
				;
	}
	
	@Override public Integer visit(ConstructorDeclaration n, Void arg) {
		return n.getModifiers()
				* 31 + (n.getTypeParameters()==null?0:  n.getTypeParameters().hashCode())
				* 31 + (n.getName()==null?0:  n.getName().hashCode())
				* 31 + (n.getParameters()==null?0:  n.getParameters().hashCode())
				* 31 + (n.getThrows()==null?0:  n.getThrows().hashCode())
				* 31 + (n.getBlock()==null?0:  n.getBlock().hashCode())
				* 31 + (n.getJavaDoc()==null?0:  n.getJavaDoc().hashCode())
				;
	}
	
	@Override public Integer visit(AnnotationDeclaration n, Void arg) {
		return (n.getJavaDoc()==null?0:  n.getJavaDoc().hashCode())
				;
	}
	
	@Override public Integer visit(VariableDeclarator n, Void arg) {
		return (n.getId()==null?0:  n.getId().hashCode())
				* 31 + (n.getInit()==null?0:  n.getInit().hashCode())
				;
	}
	
	@Override public Integer visit(JavadocComment n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(BlockComment n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(LineComment n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(SuperExpr n, Void arg) {
		return (n.getClassExpr()==null?0:  n.getClassExpr().hashCode())
				;
	}
	
	@Override public Integer visit(FieldAccessExpr n, Void arg) {
		return (n.getScope()==null?0:  n.getScope().hashCode())
				* 31 + (n.getTypeArgs()==null?0:  n.getTypeArgs().hashCode())
				* 31 + (n.getField()==null?0:  n.getField().hashCode())
				;
	}
	
	@Override public Integer visit(NormalAnnotationExpr n, Void arg) {
		return (n.getPairs()==null?0:  n.getPairs().hashCode())
				;
	}
	
	@Override public Integer visit(BooleanLiteralExpr n, Void arg) {
		return (n.getValue()?1:0)
				;
	}
	
	@Override public Integer visit(SingleMemberAnnotationExpr n, Void arg) {
		return (n.getMemberValue()==null?0:  n.getMemberValue().hashCode())
				;
	}
	
	@Override public Integer visit(ConditionalExpr n, Void arg) {
		return (n.getCondition()==null?0:  n.getCondition().hashCode())
				* 31 + (n.getThenExpr()==null?0:  n.getThenExpr().hashCode())
				* 31 + (n.getElseExpr()==null?0:  n.getElseExpr().hashCode())
				;
	}
	
	@Override public Integer visit(IntegerLiteralMinValueExpr n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(CastExpr n, Void arg) {
		return (n.getType()==null?0:  n.getType().hashCode())
				* 31 + (n.getExpr()==null?0:  n.getExpr().hashCode())
				;
	}
	
	@Override public Integer visit(UnaryExpr n, Void arg) {
		return (n.getExpr()==null?0:  n.getExpr().hashCode())
				* 31 + (n.getOperator()==null?0:  n.getOperator().hashCode())
				;
	}
	
	@Override public Integer visit(ArrayAccessExpr n, Void arg) {
		return (n.getName()==null?0:  n.getName().hashCode())
				* 31 + (n.getIndex()==null?0:  n.getIndex().hashCode())
				;
	}
	
	@Override public Integer visit(ArrayInitializerExpr n, Void arg) {
		return (n.getValues()==null?0:  n.getValues().hashCode())
				;
	}
	
	@Override public Integer visit(ObjectCreationExpr n, Void arg) {
		return (n.getScope()==null?0:  n.getScope().hashCode())
				* 31 + (n.getType()==null?0:  n.getType().hashCode())
				* 31 + (n.getTypeArgs()==null?0:  n.getTypeArgs().hashCode())
				* 31 + (n.getArgs()==null?0:  n.getArgs().hashCode())
				* 31 + (n.getAnonymousClassBody()==null?0:  n.getAnonymousClassBody().hashCode())
				;
	}
	
	@Override public Integer visit(MemberValuePair n, Void arg) {
		return (n.getName()==null?0:  n.getName().hashCode())
				* 31 + (n.getValue()==null?0:  n.getValue().hashCode())
				;
	}
	
	@Override public Integer visit(StringLiteralExpr n, Void arg) {
		return (n.getValue()==null?0:  n.getValue().hashCode())
				;
	}
	
	@Override public Integer visit(EnclosedExpr n, Void arg) {
		return (n.getInner()==null?0:  n.getInner().hashCode())
				;
	}
	
	@Override public Integer visit(ClassExpr n, Void arg) {
		return (n.getType()==null?0:  n.getType().hashCode())
				;
	}
	
	@Override public Integer visit(CharLiteralExpr n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(MarkerAnnotationExpr n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(LongLiteralExpr n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(TypeExpr n, Void arg) {
		return (n.getType()==null?0:  n.getType().hashCode())
				;
	}
	
	@Override public Integer visit(MethodReferenceExpr n, Void arg) {
		return (n.getScope()==null?0:  n.getScope().hashCode())
				* 31 + (n.getTypeParameters()==null?0:  n.getTypeParameters().hashCode())
				* 31 + (n.getIdentifier()==null?0:  n.getIdentifier().hashCode())
				;
	}
	
	@Override public Integer visit(LongLiteralMinValueExpr n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(LambdaExpr n, Void arg) {
		return (n.getParameters()==null?0:  n.getParameters().hashCode())
				* 31 + (n.isParametersEnclosed()?1:0)
				* 31 + (n.getBody()==null?0:  n.getBody().hashCode())
				;
	}
	
	@Override public Integer visit(DoubleLiteralExpr n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(IntegerLiteralExpr n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(VariableDeclarationExpr n, Void arg) {
		return n.getModifiers()
				* 31 + (n.getAnnotations()==null?0:  n.getAnnotations().hashCode())
				* 31 + (n.getType()==null?0:  n.getType().hashCode())
				* 31 + (n.getVars()==null?0:  n.getVars().hashCode())
				;
	}
	
	@Override public Integer visit(NameExpr n, Void arg) {
		return (n.getName()==null?0:  n.getName().hashCode())
				;
	}
	
	@Override public Integer visit(ArrayCreationExpr n, Void arg) {
		return (n.getType()==null?0:  n.getType().hashCode())
				* 31 + n.getArrayCount()
				* 31 + (n.getInitializer()==null?0:  n.getInitializer().hashCode())
				* 31 + (n.getDimensions()==null?0:  n.getDimensions().hashCode())
				* 31 + (n.getArraysAnnotations()==null?0:  n.getArraysAnnotations().hashCode())
				;
	}
	
	@Override public Integer visit(BinaryExpr n, Void arg) {
		return (n.getLeft()==null?0:  n.getLeft().hashCode())
				* 31 + (n.getRight()==null?0:  n.getRight().hashCode())
				* 31 + (n.getOperator()==null?0:  n.getOperator().hashCode())
				;
	}
	
	@Override public Integer visit(QualifiedNameExpr n, Void arg) {
		return (n.getQualifier()==null?0:  n.getQualifier().hashCode())
				;
	}
	
	@Override public Integer visit(ThisExpr n, Void arg) {
		return (n.getClassExpr()==null?0:  n.getClassExpr().hashCode())
				;
	}
	
	@Override public Integer visit(AssignExpr n, Void arg) {
		return (n.getTarget()==null?0:  n.getTarget().hashCode())
				* 31 + (n.getValue()==null?0:  n.getValue().hashCode())
				* 31 + (n.getOperator()==null?0:  n.getOperator().hashCode())
				;
	}
	
	@Override public Integer visit(InstanceOfExpr n, Void arg) {
		return (n.getExpr()==null?0:  n.getExpr().hashCode())
				* 31 + (n.getType()==null?0:  n.getType().hashCode())
				;
	}
	
	@Override public Integer visit(MethodCallExpr n, Void arg) {
		return (n.getScope()==null?0:  n.getScope().hashCode())
				* 31 + (n.getTypeArgs()==null?0:  n.getTypeArgs().hashCode())
				* 31 + (n.getName()==null?0:  n.getName().hashCode())
				* 31 + (n.getArgs()==null?0:  n.getArgs().hashCode())
				;
	}
	
	@Override public Integer visit(NullLiteralExpr n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(ExpressionStmt n, Void arg) {
		return (n.getExpression()==null?0:  n.getExpression().hashCode())
				;
	}
	
	@Override public Integer visit(ReturnStmt n, Void arg) {
		return (n.getExpr()==null?0:  n.getExpr().hashCode())
				;
	}
	
	@Override public Integer visit(ForStmt n, Void arg) {
		return (n.getInit()==null?0:  n.getInit().hashCode())
				* 31 + (n.getCompare()==null?0:  n.getCompare().hashCode())
				* 31 + (n.getUpdate()==null?0:  n.getUpdate().hashCode())
				* 31 + (n.getBody()==null?0:  n.getBody().hashCode())
				;
	}
	
	@Override public Integer visit(ThrowStmt n, Void arg) {
		return (n.getExpr()==null?0:  n.getExpr().hashCode())
				;
	}
	
	@Override public Integer visit(TryStmt n, Void arg) {
		return (n.getResources()==null?0:  n.getResources().hashCode())
				* 31 + (n.getTryBlock()==null?0:  n.getTryBlock().hashCode())
				* 31 + (n.getCatchs()==null?0:  n.getCatchs().hashCode())
				* 31 + (n.getFinallyBlock()==null?0:  n.getFinallyBlock().hashCode())
				;
	}
	
	@Override public Integer visit(CatchClause n, Void arg) {
		return (n.getParam()==null?0:  n.getParam().hashCode())
				* 31 + (n.getCatchBlock()==null?0:  n.getCatchBlock().hashCode())
				;
	}
	
	@Override public Integer visit(BreakStmt n, Void arg) {
		return (n.getId()==null?0:  n.getId().hashCode())
				;
	}
	
	@Override public Integer visit(SwitchStmt n, Void arg) {
		return (n.getSelector()==null?0:  n.getSelector().hashCode())
				* 31 + (n.getEntries()==null?0:  n.getEntries().hashCode())
				;
	}
	
	@Override public Integer visit(ExplicitConstructorInvocationStmt n, Void arg) {
		return (n.getTypeArgs()==null?0:  n.getTypeArgs().hashCode())
				* 31 + (n.isThis()?1:0)
				* 31 + (n.getExpr()==null?0:  n.getExpr().hashCode())
				* 31 + (n.getArgs()==null?0:  n.getArgs().hashCode())
				;
	}
	
	@Override public Integer visit(WhileStmt n, Void arg) {
		return (n.getCondition()==null?0:  n.getCondition().hashCode())
				* 31 + (n.getBody()==null?0:  n.getBody().hashCode())
				;
	}
	
	@Override public Integer visit(EmptyStmt n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(LabeledStmt n, Void arg) {
		return (n.getLabel()==null?0:  n.getLabel().hashCode())
				* 31 + (n.getStmt()==null?0:  n.getStmt().hashCode())
				;
	}
	
	@Override public Integer visit(ForeachStmt n, Void arg) {
		return (n.getVariable()==null?0:  n.getVariable().hashCode())
				* 31 + (n.getIterable()==null?0:  n.getIterable().hashCode())
				* 31 + (n.getBody()==null?0:  n.getBody().hashCode())
				;
	}
	
	@Override public Integer visit(IfStmt n, Void arg) {
		return (n.getCondition()==null?0:  n.getCondition().hashCode())
				* 31 + (n.getThenStmt()==null?0:  n.getThenStmt().hashCode())
				* 31 + (n.getElseStmt()==null?0:  n.getElseStmt().hashCode())
				;
	}
	
	@Override public Integer visit(SwitchEntryStmt n, Void arg) {
		return (n.getLabel()==null?0:  n.getLabel().hashCode())
				* 31 + (n.getStmts()==null?0:  n.getStmts().hashCode())
				;
	}
	
	@Override public Integer visit(TypeDeclarationStmt n, Void arg) {
		return (n.getTypeDeclaration()==null?0:  n.getTypeDeclaration().hashCode())
				;
	}
	
	@Override public Integer visit(DoStmt n, Void arg) {
		return (n.getBody()==null?0:  n.getBody().hashCode())
				* 31 + (n.getCondition()==null?0:  n.getCondition().hashCode())
				;
	}
	
	@Override public Integer visit(BlockStmt n, Void arg) {
		return (n.getStmts()==null?0:  n.getStmts().hashCode())
				;
	}
	
	@Override public Integer visit(AssertStmt n, Void arg) {
		return (n.getCheck()==null?0:  n.getCheck().hashCode())
				* 31 + (n.getMessage()==null?0:  n.getMessage().hashCode())
				;
	}
	
	@Override public Integer visit(SynchronizedStmt n, Void arg) {
		return (n.getExpr()==null?0:  n.getExpr().hashCode())
				* 31 + (n.getBlock()==null?0:  n.getBlock().hashCode())
				;
	}
	
	@Override public Integer visit(ContinueStmt n, Void arg) {
		return (n.getId()==null?0:  n.getId().hashCode())
				;
	}
	
	@Override public Integer visit(ReferenceType n, Void arg) {
		return (n.getType()==null?0:  n.getType().hashCode())
				* 31 + n.getArrayCount()
				* 31 + (n.getArraysAnnotations()==null?0:  n.getArraysAnnotations().hashCode())
				;
	}
	
	@Override public Integer visit(ClassOrInterfaceType n, Void arg) {
		return (n.getScope()==null?0:  n.getScope().hashCode())
				* 31 + (n.getName()==null?0:  n.getName().hashCode())
				* 31 + (n.getTypeArguments()==null?0:  n.getTypeArguments().hashCode())
				;
	}
	
	@Override public Integer visit(VoidType n, Void arg) {
		return 0;
	}
	
	@Override public Integer visit(WildcardType n, Void arg) {
		return (n.getExtends()==null?0:  n.getExtends().hashCode())
				* 31 + (n.getSuper()==null?0:  n.getSuper().hashCode())
				;
	}
	
	@Override public Integer visit(IntersectionType n, Void arg) {
		return (n.getElements()==null?0:  n.getElements().hashCode())
				;
	}
	
	@Override public Integer visit(UnionType n, Void arg) {
		return (n.getElements()==null?0:  n.getElements().hashCode())
				;
	}
	
	@Override public Integer visit(PrimitiveType n, Void arg) {
		return (n.getType()==null?0:  n.getType().hashCode())
				;
	}
	
	@Override public Integer visit(UnknownType n, Void arg) {
		return 0;
	}
	
}
