package ast;

import compiler.CodeBlock;

public interface ASTNode {
	int eval();
	void compile(CodeBlock c);
}
