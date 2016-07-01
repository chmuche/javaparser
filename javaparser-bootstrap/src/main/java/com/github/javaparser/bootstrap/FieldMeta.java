package com.github.javaparser.bootstrap;

import com.github.javaparser.ast.body.VariableDeclarator;
import me.tomassetti.symbolsolver.model.typesystem.TypeUsage;

public class FieldMeta implements Comparable<FieldMeta> {
    public final TypeUsage typeUsage;
    public final VariableDeclarator field;

    FieldMeta(VariableDeclarator field, TypeUsage typeUsage) {
        this.typeUsage = typeUsage;
        this.field = field;
    }

    @Override
    public int compareTo(FieldMeta o) {
        return field.getId().getName().compareTo(o.field.getId().getName());
    }
}
