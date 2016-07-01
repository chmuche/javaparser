package com.github.javaparser.bootstrap;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static com.github.javaparser.bootstrap.Strings.*;
import static com.laamella.gencodegen.java.JavaCodeGenerators.call;
import static me.tomassetti.symbolsolver.model.typesystem.PrimitiveTypeUsage.BOOLEAN;

public class Model {
    private final SortedSet<TypeMeta> types;
    
    private final Map<String, String> irregularGetterMappings = new HashMap<String, String>() {{
        put("CompilationUnit.pakage", "getPackage");
        put("ImportDeclaration.static_", "isStatic");
        put("ImportDeclaration.isEmptyImportDeclaration", "isEmptyImportDeclaration");
        put("AnnotationDeclaration.javadocComment", "getJavaDoc");
        put("AnnotationMemberDeclaration.javadocComment", "getJavaDoc");
        put("ClassOrInterfaceDeclaration.interface_", "isInterface");
        put("ClassOrInterfaceDeclaration.extendsList", "getExtends");
        put("ClassOrInterfaceDeclaration.implementsList", "getImplements");
        put("ClassOrInterfaceDeclaration.javadocComment", "getJavaDoc");
        put("ConstructorDeclaration.javadocComment", "getJavaDoc");
        put("EmptyMemberDeclaration.javadocComment", "getJavaDoc");
        put("EnumConstantDeclaration.javadocComment", "getJavaDoc");
        put("EnumDeclaration.javadocComment", "getJavaDoc");
        put("FieldDeclaration.javadocComment", "getJavaDoc");
        put("InitializerDeclaration.javadocComment", "getJavaDoc");
        put("MethodDeclaration.javadocComment", "getJavaDoc");
        put("ConstructorDeclaration.throws_", "getThrows");
        put("EnumDeclaration.implementsList", "getImplements");
        put("InitializerDeclaration.isStatic", "isStatic");
        put("MethodDeclaration.throws_", "getThrows");
        put("MethodDeclaration.isDefault", "isDefault");
        put("Parameter.isVarArgs", "isVarArgs");
        put("AssignExpr.op", "getOperator");
        put("BinaryExpr.op", "getOperator");
        put("BooleanLiteralExpr.value", "getValue");
        put("UnaryExpr.op", "getOperator");
        put("AssertStmt.msg", "getMessage");
        put("ExplicitConstructorInvocationStmt.isThis", "isThis");
        put("ExpressionStmt.expr", "getExpression");
        put("ForeachStmt.var", "getVariable");
        put("TypeDeclarationStmt.typeDecl", "getTypeDeclaration");
        put("WildcardType.ext", "getExtends");
        put("WildcardType.sup", "getSuper");
    }};

    public Model(SortedSet<TypeMeta> types) {
        this.types = types;
    }
    
    public SortedSet<TypeMeta> visitableTypes() {
        return types.stream().filter(TypeMeta::isConcrete).collect(Collectors.toCollection(TreeSet::new));
    }

    public String getter(TypeMeta t, FieldMeta f) {
        String name = f.field.getId().getName();
        String fqn = t.coi.getName() + "." + name;
        if (irregularGetterMappings.containsKey(fqn)) {
            return call(irregularGetterMappings.get(fqn));
        }
        if (f.typeUsage == BOOLEAN) {
            return call("is" + capitalize(name));
        }
        return call("get" + capitalize(name));
    }

}
