package ast;

import compiler.CodeBlock;

public class ASTNeq implements ASTNode {
	
	private ASTNode l, r;
	
	public ASTNeq(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}

	@Override
	public int eval() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void compile(CodeBlock c) {
		// TODO Auto-generated method stub

	}

}
