package ast;

import java.util.List;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTFun implements ASTNode {
	
	public final List<String> paramNames;
	public final List<TypeTag> paramTypeTags;
	public final ASTNode body;
	
	public ASTFun(List<String> paramNames, List<TypeTag> paramTypeTags, ASTNode body)  {
		this.paramNames = paramNames;
		this.paramTypeTags = paramTypeTags;
		this.body = body;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor, Environment<T> e) throws SemanticException {
		return visitor.visit(this, e);
	}

}
