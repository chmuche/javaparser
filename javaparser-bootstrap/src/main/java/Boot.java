import com.github.javaparser.ASTHelper;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.laamella.gencodegen.core.Block;
import com.laamella.gencodegen.core.io.FileOutputAggregator;
import com.laamella.gencodegen.core.io.OutputAggregator;
import com.laamella.gencodegen.java.ClassBody;
import com.laamella.gencodegen.java.JavaFile;
import me.tomassetti.symbolsolver.javaparsermodel.JavaParserFacade;
import me.tomassetti.symbolsolver.javaparsermodel.UnsolvedSymbolException;
import me.tomassetti.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration;
import me.tomassetti.symbolsolver.model.declarations.*;
import me.tomassetti.symbolsolver.model.declarations.TypeDeclaration;
import me.tomassetti.symbolsolver.model.typesystem.TypeUsage;
import me.tomassetti.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import me.tomassetti.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import me.tomassetti.symbolsolver.resolution.typesolvers.JreTypeSolver;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.laamella.gencodegen.java.JavaCodeGenerators.call;
import static me.tomassetti.symbolsolver.model.typesystem.PrimitiveTypeUsage.BOOLEAN;
import static me.tomassetti.symbolsolver.model.typesystem.PrimitiveTypeUsage.INT;

class FieldGenerator {
	public final TypeUsage typeUsage;
	public final VariableDeclarator field;

	FieldGenerator(VariableDeclarator field, TypeUsage typeUsage) {
		this.typeUsage = typeUsage;
		this.field = field;
	}
}

class TypeGenerator {
	public final TypeDeclaration type;
	public final ClassOrInterfaceDeclaration coi;
	public final List<FieldGenerator> fields = new ArrayList<>();

	TypeGenerator(ClassOrInterfaceDeclaration coi, TypeDeclaration type) {
		this.type = type;
		this.coi = coi;
	}
}

public class Boot {
	private static final String SRC = "javaparser-core/src/main/java/";
	private static final OutputAggregator aggregator = new FileOutputAggregator(new File(SRC));

	public static void main(String[] args) throws Exception {
		final List<TypeGenerator> typeGenerators = new ArrayList<>();
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
			System.out.println("Looking at " + sourcePath);
			for (File sourceFile : sourcePath.listFiles()) {
				// TODO make this recursive
				if (sourceFile.isFile()) {
					System.out.println("Reading file " + sourceFile);
					final Node nodeFile = JavaParser.parse(sourceFile);
					for (Node node : nodeFile.getChildrenNodes()) {
						if (node instanceof ClassOrInterfaceDeclaration) {
							final ClassOrInterfaceDeclaration coiNode = (ClassOrInterfaceDeclaration) node;
							if ((coiNode.getModifiers() & ModifierSet.ABSTRACT) > 0) {
								// Visitors only care about concrete classes.
								continue;
							}

							final TypeGenerator typeGenerator = new TypeGenerator(coiNode, JavaParserFacade.get(typeSolver).getTypeDeclaration(coiNode));
							if (typeGenerator.type.canBeAssignedTo(nodeType)) {
								typeGenerators.add(typeGenerator);

								if (!coiNode.isInterface() && !coiNode.getName().equals("Utils")) {
									System.out.println("Found class " + coiNode.getName());
//									typeGenerator.type
									for (BodyDeclaration bd : coiNode.getMembers()) {
										if (bd instanceof FieldDeclaration) {
											FieldDeclaration field = (FieldDeclaration) bd;
											if ((field.getModifiers() & (ModifierSet.STATIC | ModifierSet.FINAL)) > 0) {
												continue;
											}
											VariableDeclarator var = field.getVariables().get(0);
											System.out.println("Field " + field.toStringWithoutComments());
											// TODO go through all variables.
											typeGenerator.fields.add(new FieldGenerator(var, JavaParserFacade.get(typeSolver).getType(var)));
										}
									}
								}
							}
						}
					}
				}
			}
		}
		generateHashCodeVisitor(typeGenerators);
		generateVoidVisitor(typeGenerators);
	}

	private static void generateHashCodeVisitor(List<TypeGenerator> typeGenerators) throws Exception {
		JavaFile file = new JavaFile("com.github.javaparser.ast.visitor", "HashCodeVisitor2");
		file.imports.add("com.github.javaparser.ast.*");
		file.imports.add("com.github.javaparser.ast.body.*");
		file.imports.add("com.github.javaparser.ast.expr.*");
		file.imports.add("com.github.javaparser.ast.comments.*");
		file.imports.add("com.github.javaparser.ast.stmt.*");
		file.imports.add("com.github.javaparser.ast.type.*");
		file.imports.add("java.util.List");

		ClassBody classBody = file.class_("public class HashCodeVisitor2 implements GenericVisitor<Integer, Void>");

		for (TypeGenerator t : typeGenerators) {
			Block method = classBody.method("@Override public Integer visit(%s n, Void arg)", t.type.getName());
			if (t.fields.size() == 0) {
				method.add("return 0;");
			} else {
				String prefix = "return";
				for (FieldGenerator f : t.fields) {
					if (f.typeUsage == BOOLEAN) {
						method.add("%s (n.%s?1:0)", prefix, get(t, f));
					} else if (f.typeUsage == INT) {
						method.add("%s n.%s", prefix, get(t, f));
					} else if (f.typeUsage.isReferenceType()) {
						method.add("%s (n.%s==null?0:  n.%s.hashCode())", prefix, get(t, f), get(t, f));
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

	private static void generateVoidVisitor(List<TypeGenerator> typeGenerators) throws Exception {
		JavaFile file = new JavaFile("com.github.javaparser.ast.visitor", "VoidVisitor2");
		file.imports.add("com.github.javaparser.ast.*");
		file.imports.add("com.github.javaparser.ast.body.*");
		file.imports.add("com.github.javaparser.ast.expr.*");
		file.imports.add("com.github.javaparser.ast.comments.*");
		file.imports.add("com.github.javaparser.ast.stmt.*");
		file.imports.add("com.github.javaparser.ast.type.*");

		ClassBody classBody = file.class_("public interface VoidVisitor2<T>");
		for (TypeGenerator t : typeGenerators) {
			classBody.add("void visit(%s n, T arg);", t.type.getName()).ln();
		}

		file.write(aggregator);
	}

	private static final Map<String, String> irregularGetterMappings = new HashMap<String, String>() {{
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

	private static String get(TypeGenerator t, FieldGenerator f) {
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

	public static String capitalize(final String string) {
		if (string.length() == 0) {
			return string;
		}
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	public static String decapitalize(String string) {
		if (string.length() == 0) {
			return string;
		}
		return string.substring(0, 1).toLowerCase() + string.substring(1);
	}


}
