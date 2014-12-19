package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;
import semantics.typeSystem.Type;

public class ASTId implements ASTNode {

	public final String id;
	private Type idType;
	
	public ASTId(String id) {
		this.id = id;
	}
	
	public void setIdType(Type idType) {
		this.idType = idType;
	}
	
	public Type getIdType() {
		return idType;
	}

	@Override
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S>
 e) throws SemanticException {
		return visitor.visit(this, e);
	}

}
