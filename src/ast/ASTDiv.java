package ast;

import semantics.CodeBlock;
import semantics.Op;
import semantics.Visitor;

public class ASTDiv implements ASTNode {
	
	public final ASTNode l, r;
	
	public ASTDiv(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}
	
	@Override
	public int eval() {
		return l.eval() / r.eval();
	}

	@Override
	public void compile(CodeBlock c) {
		l.compile(c);
		r.compile(c);
		c.insertOp(Op.DIV);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
