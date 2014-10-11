package ast;

import semantics.Value;
import semantics.Visitor;

public class ASTTruth implements ASTNode {
	
	public final Value<Boolean> tVal;

	public ASTTruth(Value<Boolean> tVal) {
		this.tVal = tVal;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
