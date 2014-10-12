package ast;

import semantics.Visitor;

public class ASTNot implements ASTNode {
	
	public final ASTNode v;

	public ASTNot(ASTNode v) {
		this.v = v;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}

}
