package ast;

import compiler.CodeBlock;

public class ASTUnMinus implements ASTNode {

	private ASTNode n;
	
	public ASTUnMinus(ASTNode n) {
		this.n = n;
	}

	@Override
	public int eval() {
		return -n.eval();
	}

	@Override
	public void compile(CodeBlock c) {
		// TODO Auto-generated method stub
		
	}

}
