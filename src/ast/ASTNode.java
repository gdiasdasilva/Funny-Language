package ast;

import semantics.CodeBlock;
import semantics.Visitor;

public interface ASTNode {
	int eval();
	void compile(CodeBlock c);
	<T> T accept(Visitor<T> visitor);
}