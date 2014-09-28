package ast;

import compiler.CodeBlock;

public class ASTEq implements ASTNode {

	ASTNode l, r;
	
	public ASTEq(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}
	
	@Override
	public int eval() {
		return (l.eval() == r.eval() ? 1 : 0);
	}

	@Override
	public void compile(CodeBlock c) {
		l.compile(c);
		r.compile(c);
		
	}
	
}
