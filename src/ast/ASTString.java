package ast;

import semantics.IEnv;
import semantics.SemanticException;
import semantics.StringValue;
import semantics.Visitor;

public class ASTString implements ASTNode {

	public final StringValue string;
	
	public ASTString(String string)
	{
		this.string = new StringValue(string);
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor, IEnv e) throws SemanticException {
		return visitor.visit(this, e);
	}

}
