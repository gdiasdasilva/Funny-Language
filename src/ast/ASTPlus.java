package ast;

import compiler.CodeBlock;
import compiler.Op;

public class ASTPlus implements ASTNode {
	
	ASTNode l, r;
	
	public ASTPlus(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}
	
	@Override
	public int eval() {
		return (l.eval() + r.eval());
	}

	@Override
	public void compile(CodeBlock c) {
		l.compile(c);
		r.compile(c);
		c.insertOp(Op.ADD);
	}

}
