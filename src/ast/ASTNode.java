package ast;

import semantics.Visitor;
import compiler.CodeBlock;

public interface ASTNode {
	int eval();
	void compile(CodeBlock c);
	<T> T accept(Visitor<T> visitor);
}