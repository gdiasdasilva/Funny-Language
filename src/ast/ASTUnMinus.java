package ast;

import semantics.IValue;
import semantics.Visitor;

public class ASTUnMinus implements ASTNode {

	public final ASTNode v;
	
	public ASTUnMinus(ASTNode v) {
		this.v = v;
	}

	@Override
	public IValue accept(Visitor<IValue> visitor) throws Exception {
		return visitor.visit(this);
	}

}
