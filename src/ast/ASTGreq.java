package ast;

import semantics.Visitor;
import compiler.CodeBlock;

public class ASTGreq implements ASTNode {

	public final ASTNode l, r;
	
	public ASTGreq(ASTNode l, ASTNode r) {
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

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
