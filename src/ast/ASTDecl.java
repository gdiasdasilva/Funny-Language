package ast;

import java.util.Iterator;
import java.util.List;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;
import semantics.typeSystem.Type;

public class ASTDecl implements ASTNode {

	public final List<String> ids;
	public final ASTNode body;
	public final List<ASTNode> defs;
	private List<Type> idTypes;
	
	public ASTDecl(List<String> ids, List<ASTNode> defs, ASTNode body)
	{
		this.ids = ids;
		this.defs = defs;
		this.body = body;
	}
	
	public void setIdTypes(List<Type> idTypes) {
		this.idTypes = idTypes;
	}
	
	public Iterator<Type> getIdTypesIterator() {
		return idTypes.iterator();
	}

	@Override
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S> e) throws SemanticException {
		return visitor.visit(this, e);
	}

}
