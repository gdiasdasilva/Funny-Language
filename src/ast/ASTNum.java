package ast;

import semantics.IEnv;
import semantics.IntValue;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTNum implements ASTNode {
	
	public final IntValue integer;

	public ASTNum(int integer) {
		this.integer = new IntValue(integer);
	}

	@Override
	public <T> T accept(Visitor<T> visitor, IEnv e) throws SemanticException {
		return visitor.visit(this, e);
	}

}
