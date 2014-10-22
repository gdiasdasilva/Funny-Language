package ast;

import semantics.BoolValue;
import semantics.IValue;
import semantics.Visitor;

public class ASTTruth implements ASTNode {
	
	public final BoolValue tVal;

	public ASTTruth(BoolValue tVal) {
		this.tVal = tVal;
	}

	@Override
	public IValue accept(Visitor<IValue> visitor) throws Exception {
		return visitor.visit(this);
	}

}
