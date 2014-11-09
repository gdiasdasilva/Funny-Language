package ast;

import semantics.IEnv;
import semantics.SemanticException;
import semantics.Visitor;

public interface ASTNode {
	<T> T accept(Visitor<T> visitor, IEnv e) throws SemanticException;
}