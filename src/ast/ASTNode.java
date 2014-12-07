package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public interface ASTNode {
	<T> T accept(Visitor<T> visitor, Environment<T> e) throws SemanticException;
}