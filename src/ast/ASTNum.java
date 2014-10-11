package ast;

import semantics.Value;
import semantics.Visitor;

public class ASTNum implements ASTNode {
	
	public final Value<Integer> iVal;

	public ASTNum(Value<Integer> iVal) {
		this.iVal = iVal;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
