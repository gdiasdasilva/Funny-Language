package ast;

import semantics.BoolValue;
import semantics.IValue;
import semantics.Visitor;

public class ASTTruth implements ASTNode {
	
	public final IValue tVal;

	public ASTTruth(boolean tVal) {
		this.tVal = new BoolValue(tVal);
	}

	@Override
	public IValue accept(Visitor<IValue> visitor) throws Exception {
		return visitor.visit(this);
	}

}
