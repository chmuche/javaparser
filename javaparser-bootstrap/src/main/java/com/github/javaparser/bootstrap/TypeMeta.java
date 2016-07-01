package com.github.javaparser.bootstrap;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ModifierSet;
import me.tomassetti.symbolsolver.model.declarations.TypeDeclaration;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class TypeMeta implements Comparable<TypeMeta> {
    public final TypeDeclaration type;
    public final ClassOrInterfaceDeclaration coi;
    public final List<FieldMeta> fields = new ArrayList<>();

    TypeMeta(ClassOrInterfaceDeclaration coi, TypeDeclaration type, SortedSet<FieldMeta> fields) {
        this.type = type;
        this.coi = coi;
    }

    public boolean isConcrete() {
        return (coi.getModifiers() & ModifierSet.ABSTRACT) == 0;
    }

    @Override
    public int compareTo(TypeMeta o) {
        return coi.getName().compareTo(o.coi.getName());
    }

    @Override
    public String toString() {
        return coi.getName();
    }
}

