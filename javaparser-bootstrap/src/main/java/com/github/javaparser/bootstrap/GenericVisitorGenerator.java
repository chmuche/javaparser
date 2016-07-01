package com.github.javaparser.bootstrap;

import com.laamella.gencodegen.core.io.OutputAggregator;
import com.laamella.gencodegen.java.ClassBody;
import com.laamella.gencodegen.java.JavaFile;

public class GenericVisitorGenerator implements Generator {
    @Override
    public void generate(Model model, OutputAggregator aggregator) throws Exception {
        JavaFile file = new JavaFile("com.github.javaparser.ast.visitor", "GenericVisitor2");
        file.imports.add("com.github.javaparser.ast.*");
        file.imports.add("com.github.javaparser.ast.body.*");
        file.imports.add("com.github.javaparser.ast.expr.*");
        file.imports.add("com.github.javaparser.ast.comments.*");
        file.imports.add("com.github.javaparser.ast.stmt.*");
        file.imports.add("com.github.javaparser.ast.type.*");

        ClassBody classBody = file.class_("public interface GenericVisitor2<R, A>");
        for (TypeMeta t : model.visitableTypes()) {
            classBody.add("R visit(%s n, A arg);", t.type.getName()).ln();
        }

        file.write(aggregator);
    }
}
