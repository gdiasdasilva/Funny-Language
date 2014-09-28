package ast;

import compiler.CodeBlock;

public class ASTNum implements ASTNode {
	int val;
	
	public ASTNum(int val) {
		this.val = val;
	}
	
	@Override
	public int eval() {
		return val;
	}

	@Override
	public void compile(CodeBlock c) {
		c.insertIntArgument(val);
	}
}
