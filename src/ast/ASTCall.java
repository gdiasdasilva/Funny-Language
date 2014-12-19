package ast;

import java.util.List;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTCall implements ASTNode {

	public final ASTNode fun;
	public final List<ASTNode> args;
	
	public ASTCall(ASTNode fun, List<ASTNode> args)
	{
		this.fun = fun;
		this.args = args;
	}
	
	@Override
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S>
 e) throws SemanticException {
		return visitor.visit(this, e);
	}

}
