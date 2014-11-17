package ast;

import java.util.List;

import semantics.IEnv;
import semantics.IType;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTFun implements ASTNode {

	public final List<String> params;
	public final List<IType> types;
	public final ASTNode body;
	
	public ASTFun(List<String> params, List<IType> types, ASTNode body) 
	{
		this.params = params;
		this.body = body;
		this.types = types;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor, IEnv e) throws SemanticException {
		return visitor.visit(this, e);
	}

}
