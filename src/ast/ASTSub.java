package ast;

import semantics.Visitor;
import compiler.CodeBlock;
import compiler.Op;

public class ASTSub implements ASTNode{
	
	public final ASTNode l, r;

	public ASTSub(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}

	@Override
	public int eval() {
		return (l.eval() - r.eval());
	}

	@Override
	public void compile(CodeBlock c) {
		l.compile(c);
		r.compile(c);		
		c.insertOp(Op.SUB);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
