package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTNot implements ASTNode {
	
	public final ASTNode v;

	public ASTNot(ASTNode v) {
		this.v = v;
	}

	@Override
	public <T> T accept(Visitor<T> visitor, Environment<T> e) throws SemanticException {
		return visitor.visit(this, e);
	}

}
