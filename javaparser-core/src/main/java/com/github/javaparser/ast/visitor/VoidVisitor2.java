package com.github.javaparser.ast.visitor;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.comments.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;

public interface VoidVisitor2<T> {
	
	void visit(PackageDeclaration n, T arg);
	
	void visit(CompilationUnit n, T arg);
	
	void visit(ImportDeclaration n, T arg);
	
	void visit(TypeParameter n, T arg);
	
	void visit(InitializerDeclaration n, T arg);
	
	void visit(VariableDeclaratorId n, T arg);
	
	void visit(EnumDeclaration n, T arg);
	
	void visit(MethodDeclaration n, T arg);
	
	void visit(EmptyMemberDeclaration n, T arg);
	
	void visit(EnumConstantDeclaration n, T arg);
	
	void visit(EmptyTypeDeclaration n, T arg);
	
	void visit(AnnotationMemberDeclaration n, T arg);
	
	void visit(MultiTypeParameter n, T arg);
	
	void visit(ClassOrInterfaceDeclaration n, T arg);
	
	void visit(FieldDeclaration n, T arg);
	
	void visit(Parameter n, T arg);
	
	void visit(ConstructorDeclaration n, T arg);
	
	void visit(AnnotationDeclaration n, T arg);
	
	void visit(VariableDeclarator n, T arg);
	
	void visit(JavadocComment n, T arg);
	
	void visit(BlockComment n, T arg);
	
	void visit(LineComment n, T arg);
	
	void visit(SuperExpr n, T arg);
	
	void visit(FieldAccessExpr n, T arg);
	
	void visit(NormalAnnotationExpr n, T arg);
	
	void visit(BooleanLiteralExpr n, T arg);
	
	void visit(SingleMemberAnnotationExpr n, T arg);
	
	void visit(ConditionalExpr n, T arg);
	
	void visit(IntegerLiteralMinValueExpr n, T arg);
	
	void visit(CastExpr n, T arg);
	
	void visit(UnaryExpr n, T arg);
	
	void visit(ArrayAccessExpr n, T arg);
	
	void visit(ArrayInitializerExpr n, T arg);
	
	void visit(ObjectCreationExpr n, T arg);
	
	void visit(MemberValuePair n, T arg);
	
	void visit(StringLiteralExpr n, T arg);
	
	void visit(EnclosedExpr n, T arg);
	
	void visit(ClassExpr n, T arg);
	
	void visit(CharLiteralExpr n, T arg);
	
	void visit(MarkerAnnotationExpr n, T arg);
	
	void visit(LongLiteralExpr n, T arg);
	
	void visit(TypeExpr n, T arg);
	
	void visit(MethodReferenceExpr n, T arg);
	
	void visit(LongLiteralMinValueExpr n, T arg);
	
	void visit(LambdaExpr n, T arg);
	
	void visit(DoubleLiteralExpr n, T arg);
	
	void visit(IntegerLiteralExpr n, T arg);
	
	void visit(VariableDeclarationExpr n, T arg);
	
	void visit(NameExpr n, T arg);
	
	void visit(ArrayCreationExpr n, T arg);
	
	void visit(BinaryExpr n, T arg);
	
	void visit(QualifiedNameExpr n, T arg);
	
	void visit(ThisExpr n, T arg);
	
	void visit(AssignExpr n, T arg);
	
	void visit(InstanceOfExpr n, T arg);
	
	void visit(MethodCallExpr n, T arg);
	
	void visit(NullLiteralExpr n, T arg);
	
	void visit(ExpressionStmt n, T arg);
	
	void visit(ReturnStmt n, T arg);
	
	void visit(ForStmt n, T arg);
	
	void visit(ThrowStmt n, T arg);
	
	void visit(TryStmt n, T arg);
	
	void visit(CatchClause n, T arg);
	
	void visit(BreakStmt n, T arg);
	
	void visit(SwitchStmt n, T arg);
	
	void visit(ExplicitConstructorInvocationStmt n, T arg);
	
	void visit(WhileStmt n, T arg);
	
	void visit(EmptyStmt n, T arg);
	
	void visit(LabeledStmt n, T arg);
	
	void visit(ForeachStmt n, T arg);
	
	void visit(IfStmt n, T arg);
	
	void visit(SwitchEntryStmt n, T arg);
	
	void visit(TypeDeclarationStmt n, T arg);
	
	void visit(DoStmt n, T arg);
	
	void visit(BlockStmt n, T arg);
	
	void visit(AssertStmt n, T arg);
	
	void visit(SynchronizedStmt n, T arg);
	
	void visit(ContinueStmt n, T arg);
	
	void visit(ReferenceType n, T arg);
	
	void visit(ClassOrInterfaceType n, T arg);
	
	void visit(VoidType n, T arg);
	
	void visit(WildcardType n, T arg);
	
	void visit(IntersectionType n, T arg);
	
	void visit(UnionType n, T arg);
	
	void visit(PrimitiveType n, T arg);
	
	void visit(UnknownType n, T arg);
	
}
