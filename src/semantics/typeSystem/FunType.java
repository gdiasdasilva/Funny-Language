package semantics.typeSystem;

import java.util.List;

import semantics.Environment;
import ast.ASTNode;
import ast.TypeTag;

public final class FunType implements Type {

	final List<String> paramNames;
	final List<TypeTag> paramTypeTags;
	final ASTNode body;
	final Environment<Type> e;
	
	public FunType(ASTNode body, List<TypeTag> paramTypeTags, List<String> paramNames, Environment<Type> e) {
		this.paramTypeTags = paramTypeTags;
		this.paramNames = paramNames;
		this.body = body;
		this.e = e;
	}
	
	@Override
	public VType getType() {
		return VType.FUNCTION;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FunType)
			return (paramTypeTags.equals(((FunType) obj).paramTypeTags));
		return false;
	}

	public String toString() {
		return "FUNCTION w/ params of type " + paramTypeTags + ".";
	}
	
	public Environment<Type> beginScope()
	{
		return e.beginScope();
	}
	
	public void endScope()
	{
		e.endScope();
	}
}
