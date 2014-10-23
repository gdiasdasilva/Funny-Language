package ast;

import semantics.IValue;
import semantics.StringValue;
import semantics.Visitor;

public class ASTString implements ASTNode {

	public final IValue val;
	
	public ASTString(String val)
	{
		this.val = new StringValue(val);
	}
	
	@Override
	public IValue accept(Visitor<IValue> visitor) throws Exception {
		return visitor.visit(this);
	}

}
