package ast;

import semantics.Environment;
import semantics.IntValue;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTNum implements ASTNode {
	
	public final IntValue integer;

	public ASTNum(int integer) {
		this.integer = new IntValue(integer);
	}

	@Override
	public <T> T accept(Visitor<T> visitor, Environment<T> e) throws SemanticException {
		return visitor.visit(this, e);
	}

}
