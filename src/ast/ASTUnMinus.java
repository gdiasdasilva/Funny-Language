package ast;

import semantics.IEnv;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTUnMinus implements ASTNode {

	public final ASTNode v;
	
	public ASTUnMinus(ASTNode v) {
		this.v = v;
	}

	@Override
	public <T> T accept(Visitor<T> visitor, IEnv e) throws SemanticException {
		return visitor.visit(this, e);
	}

}
