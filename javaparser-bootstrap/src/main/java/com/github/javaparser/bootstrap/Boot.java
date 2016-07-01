package com.github.javaparser.bootstrap;

import com.github.javaparser.ASTHelper;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.*;
import com.laamella.gencodegen.core.io.FileOutputAggregator;
import com.laamella.gencodegen.core.io.OutputAggregator;
import me.tomassetti.symbolsolver.javaparsermodel.JavaParserFacade;
import me.tomassetti.symbolsolver.model.declarations.TypeDeclaration;
import me.tomassetti.symbolsolver.model.resolution.TypeSolver;
import me.tomassetti.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import me.tomassetti.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import me.tomassetti.symbolsolver.resolution.typesolvers.JreTypeSolver;

import java.io.File;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class Boot {
    private final String SRC = "javaparser-core/src/main/java/";
    private final OutputAggregator aggregator = new FileOutputAggregator(new File(SRC));

    public static void main(String[] args) throws Exception {
        new Boot().generate();
    }

    public void generate() throws Exception {
        Model model = createModel();
        new HashCodeVisitorGenerator().generate(model, aggregator);
        new VoidVisitorGenerator().generate(model, aggregator);
    }

    private Model createModel() throws ParseException, IOException {
        final SortedSet<TypeMeta> typeMetas = new TreeSet<>();
        final File[] sourcePaths = {
                new File(SRC + "com/github/javaparser/ast/"),
                new File(SRC + "com/github/javaparser/ast/body/"),
                new File(SRC + "com/github/javaparser/ast/comments/"),
                new File(SRC + "com/github/javaparser/ast/expr/"),
                new File(SRC + "com/github/javaparser/ast/internal/"),
                new File(SRC + "com/github/javaparser/ast/stmt/"),
                new File(SRC + "com/github/javaparser/ast/type/")
        };
        final CombinedTypeSolver typeSolver = new CombinedTypeSolver();
        typeSolver.add(new JreTypeSolver());
//		for (File sourcePath : sourcePaths) {
        typeSolver.add(new JavaParserTypeSolver(new File(SRC)));
//		}

        CompilationUnit parse = JavaParser.parse(new File(SRC + "com/github/javaparser/ast/Node.java"));
        ClassOrInterfaceDeclaration nodesByType = ASTHelper.getNodesByType(parse, ClassOrInterfaceDeclaration.class).get(0);
        TypeDeclaration nodeType = JavaParserFacade.get(typeSolver).getTypeDeclaration(nodesByType);

        for (File sourcePath : sourcePaths) {
            File[] files = sourcePath.listFiles();
            if (files == null) {
                throw new IOException();
            }
            for (File sourceFile : files) {
                // TODO make this recursive
                if (sourceFile.isFile()) {
                    final Node nodeFile = JavaParser.parse(sourceFile);
                    for (Node node : nodeFile.getChildrenNodes()) {
                        if (node instanceof ClassOrInterfaceDeclaration) {
                            final ClassOrInterfaceDeclaration coiNode = (ClassOrInterfaceDeclaration) node;
                            TypeDeclaration typeDeclaration = JavaParserFacade.get(typeSolver).getTypeDeclaration(coiNode);
                            if (typeDeclaration.canBeAssignedTo(nodeType)) {
                                System.out.println(coiNode.getName());
                                typeMetas.add(new TypeMeta(coiNode, typeDeclaration, findFields(coiNode, typeSolver)));
                            }
                        }
                    }
                }
            }
        }
        return new Model(typeMetas);
    }

    private SortedSet<FieldMeta> findFields(ClassOrInterfaceDeclaration coi, TypeSolver typeSolver) {
        TreeSet<FieldMeta> fields = new TreeSet<>();
        for (BodyDeclaration bd : coi.getMembers()) {
            if (bd instanceof FieldDeclaration) {
                FieldDeclaration field = (FieldDeclaration) bd;
                if ((field.getModifiers() & (ModifierSet.STATIC | ModifierSet.FINAL)) > 0) {
                    continue;
                }
                VariableDeclarator var = field.getVariables().get(0);
                System.out.println("\t" + field.toStringWithoutComments());
                // TODO go through all variables.
                fields.add(new FieldMeta(var, JavaParserFacade.get(typeSolver).getType(var)));
            }
        }
        return fields;
    }
}
