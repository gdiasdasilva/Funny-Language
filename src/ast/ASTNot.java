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
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S>
 e) throws SemanticException {
		return visitor.visit(this, e);
	}

}
