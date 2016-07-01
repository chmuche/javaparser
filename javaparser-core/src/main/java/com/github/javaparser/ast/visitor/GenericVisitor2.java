package com.github.javaparser.ast.visitor;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.comments.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;

public interface GenericVisitor2<R, A> {
	
	R visit(AnnotationDeclaration n, A arg);
	
	R visit(AnnotationMemberDeclaration n, A arg);
	
	R visit(ArrayAccessExpr n, A arg);
	
	R visit(ArrayCreationExpr n, A arg);
	
	R visit(ArrayInitializerExpr n, A arg);
	
	R visit(AssertStmt n, A arg);
	
	R visit(AssignExpr n, A arg);
	
	R visit(BinaryExpr n, A arg);
	
	R visit(BlockComment n, A arg);
	
	R visit(BlockStmt n, A arg);
	
	R visit(BooleanLiteralExpr n, A arg);
	
	R visit(BreakStmt n, A arg);
	
	R visit(CastExpr n, A arg);
	
	R visit(CatchClause n, A arg);
	
	R visit(CharLiteralExpr n, A arg);
	
	R visit(ClassExpr n, A arg);
	
	R visit(ClassOrInterfaceDeclaration n, A arg);
	
	R visit(ClassOrInterfaceType n, A arg);
	
	R visit(CompilationUnit n, A arg);
	
	R visit(ConditionalExpr n, A arg);
	
	R visit(ConstructorDeclaration n, A arg);
	
	R visit(ContinueStmt n, A arg);
	
	R visit(DoStmt n, A arg);
	
	R visit(DoubleLiteralExpr n, A arg);
	
	R visit(EmptyMemberDeclaration n, A arg);
	
	R visit(EmptyStmt n, A arg);
	
	R visit(EmptyTypeDeclaration n, A arg);
	
	R visit(EnclosedExpr n, A arg);
	
	R visit(EnumConstantDeclaration n, A arg);
	
	R visit(EnumDeclaration n, A arg);
	
	R visit(ExplicitConstructorInvocationStmt n, A arg);
	
	R visit(ExpressionStmt n, A arg);
	
	R visit(FieldAccessExpr n, A arg);
	
	R visit(FieldDeclaration n, A arg);
	
	R visit(ForStmt n, A arg);
	
	R visit(ForeachStmt n, A arg);
	
	R visit(IfStmt n, A arg);
	
	R visit(ImportDeclaration n, A arg);
	
	R visit(InitializerDeclaration n, A arg);
	
	R visit(InstanceOfExpr n, A arg);
	
	R visit(IntegerLiteralExpr n, A arg);
	
	R visit(IntegerLiteralMinValueExpr n, A arg);
	
	R visit(IntersectionType n, A arg);
	
	R visit(JavadocComment n, A arg);
	
	R visit(LabeledStmt n, A arg);
	
	R visit(LambdaExpr n, A arg);
	
	R visit(LineComment n, A arg);
	
	R visit(LongLiteralExpr n, A arg);
	
	R visit(LongLiteralMinValueExpr n, A arg);
	
	R visit(MarkerAnnotationExpr n, A arg);
	
	R visit(MemberValuePair n, A arg);
	
	R visit(MethodCallExpr n, A arg);
	
	R visit(MethodDeclaration n, A arg);
	
	R visit(MethodReferenceExpr n, A arg);
	
	R visit(MultiTypeParameter n, A arg);
	
	R visit(NameExpr n, A arg);
	
	R visit(NormalAnnotationExpr n, A arg);
	
	R visit(NullLiteralExpr n, A arg);
	
	R visit(ObjectCreationExpr n, A arg);
	
	R visit(PackageDeclaration n, A arg);
	
	R visit(Parameter n, A arg);
	
	R visit(PrimitiveType n, A arg);
	
	R visit(QualifiedNameExpr n, A arg);
	
	R visit(ReferenceType n, A arg);
	
	R visit(ReturnStmt n, A arg);
	
	R visit(SingleMemberAnnotationExpr n, A arg);
	
	R visit(StringLiteralExpr n, A arg);
	
	R visit(SuperExpr n, A arg);
	
	R visit(SwitchEntryStmt n, A arg);
	
	R visit(SwitchStmt n, A arg);
	
	R visit(SynchronizedStmt n, A arg);
	
	R visit(ThisExpr n, A arg);
	
	R visit(ThrowStmt n, A arg);
	
	R visit(TryStmt n, A arg);
	
	R visit(TypeDeclarationStmt n, A arg);
	
	R visit(TypeExpr n, A arg);
	
	R visit(TypeParameter n, A arg);
	
	R visit(UnaryExpr n, A arg);
	
	R visit(UnionType n, A arg);
	
	R visit(UnknownType n, A arg);
	
	R visit(VariableDeclarationExpr n, A arg);
	
	R visit(VariableDeclarator n, A arg);
	
	R visit(VariableDeclaratorId n, A arg);
	
	R visit(VoidType n, A arg);
	
	R visit(WhileStmt n, A arg);
	
	R visit(WildcardType n, A arg);
	
}
