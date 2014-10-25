package ast;

import semantics.SemanticException;
import semantics.Visitor;

public class ASTUnMinus implements ASTNode {

	public final ASTNode v;
	
	public ASTUnMinus(ASTNode v) {
		this.v = v;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) throws SemanticException {
		return visitor.visit(this);
	}

}
