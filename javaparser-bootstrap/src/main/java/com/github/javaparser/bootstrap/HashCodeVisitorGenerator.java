package com.github.javaparser.bootstrap;

import com.laamella.gencodegen.core.Block;
import com.laamella.gencodegen.core.io.OutputAggregator;
import com.laamella.gencodegen.java.ClassBody;
import com.laamella.gencodegen.java.JavaFile;

import static me.tomassetti.symbolsolver.model.typesystem.PrimitiveTypeUsage.BOOLEAN;
import static me.tomassetti.symbolsolver.model.typesystem.PrimitiveTypeUsage.INT;

public class HashCodeVisitorGenerator implements Generator {
    @Override
    public void generate(Model model, OutputAggregator aggregator) throws Exception {
        JavaFile file = new JavaFile("com.github.javaparser.ast.visitor", "HashCodeVisitor2");
        file.imports.add("com.github.javaparser.ast.*");
        file.imports.add("com.github.javaparser.ast.body.*");
        file.imports.add("com.github.javaparser.ast.expr.*");
        file.imports.add("com.github.javaparser.ast.comments.*");
        file.imports.add("com.github.javaparser.ast.stmt.*");
        file.imports.add("com.github.javaparser.ast.type.*");
        file.imports.add("java.util.List");

        ClassBody classBody = file.class_("public class HashCodeVisitor2 implements GenericVisitor<Integer, Void>");

        for (TypeMeta t : model.visitableTypes()) {
            Block method = classBody.method("@Override public Integer visit(%s n, Void arg)", t.type.getName());
            if (t.fields.size() == 0) {
                method.add("return 0;");
            } else {
                String prefix = "return";
                for (FieldMeta f : t.fields) {
                    if (f.typeUsage == BOOLEAN) {
                        method.add("%s (n.%s?1:0)", prefix, model.getter(t, f));
                    } else if (f.typeUsage == INT) {
                        method.add("%s n.%s", prefix, model.getter(t, f));
                    } else if (f.typeUsage.isReferenceType()) {
                        method.add("%s (n.%s==null?0:  n.%s.hashCode())", prefix, model.getter(t, f), model.getter(t, f));
                    } else {
                        method.add("// TODO %s", f.field.getId().getName());
                    }
                    if (prefix.equals("return")) {
                        prefix = "* 31 +";
                        method.in().in();
                    }
                }
                method.add(";");
            }
        }

        file.write(aggregator);
    }


}
