package ast;

import semantics.IValue;
import semantics.Visitor;

public class ASTNot implements ASTNode {
	
	public final ASTNode v;

	public ASTNot(ASTNode v) {
		this.v = v;
	}

	@Override
	public IValue accept(Visitor<IValue> visitor) throws Exception {
		return visitor.visit(this);
	}

}
