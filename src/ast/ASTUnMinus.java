package ast;

import semantics.Visitor;

public class ASTUnMinus implements ASTNode {

	public final ASTNode v;
	
	public ASTUnMinus(ASTNode v) {
		this.v = v;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}

}
