package ast;

import semantics.SemanticException;
import semantics.Visitor;

public interface ASTNode {
	<T> T accept(Visitor<T> visitor) throws SemanticException;
}