package ast;

import semantics.BoolValue;
import semantics.IEnv;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTBool implements ASTNode {
	
	public final BoolValue bool;

	public ASTBool(boolean bool) {
		this.bool = new BoolValue(bool);
	}

	@Override
	public <T> T accept(Visitor<T> visitor, IEnv e) throws SemanticException {
		return visitor.visit(this, e);
	}

}
