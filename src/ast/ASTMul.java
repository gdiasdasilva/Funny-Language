package ast;

import semantics.Visitor;
import semantics.compiler.CodeBlock;
import semantics.compiler.Op;

public class ASTMul implements ASTNode {

	public final ASTNode l, r;
	
	public ASTMul(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}
	
	@Override
	public int eval() {
		return l.eval() * r.eval();
	}

	@Override
	public void compile(CodeBlock c) {
		l.compile(c);
		r.compile(c);
		c.insertOp(Op.MUL);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
