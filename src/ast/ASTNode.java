package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public interface ASTNode {
	<T, S> T accept(Visitor<T, S> visitor, Environment<S> e) throws SemanticException;
}