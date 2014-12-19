package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTBool implements ASTNode {
	
	public final boolean bool;

	public ASTBool(boolean bool) {
		this.bool = bool;
	}

	@Override
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S> e) throws SemanticException {
		return visitor.visit(this, e);
	}

}
