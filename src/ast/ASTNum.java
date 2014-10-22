package ast;

import semantics.IValue;
import semantics.IntValue;
import semantics.Visitor;

public class ASTNum implements ASTNode {
	
	public final IntValue iVal;

	public ASTNum(IntValue iVal) {
		this.iVal = iVal;
	}

	@Override
	public IValue accept(Visitor<IValue> visitor) throws Exception {
		return visitor.visit(this);
	}

}
