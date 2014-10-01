package ast;

import semantics.Visitor;
import semantics.compiler.CodeBlock;

public interface ASTNode {
	int eval();
	void compile(CodeBlock c);
	<T> T accept(Visitor<T> visitor);
}