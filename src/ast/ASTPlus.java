package ast;

import semantics.Visitor;
import compiler.CodeBlock;
import compiler.Op;

public class ASTPlus implements ASTNode {
	
	public final ASTNode l, r;
	
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

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
