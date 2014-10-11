package ast;

import semantics.Visitor;

public interface ASTNode {
	<T> T accept(Visitor<T> visitor);
}