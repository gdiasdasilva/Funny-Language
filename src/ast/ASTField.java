package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTField implements ASTNode {
	
	public final ASTNode record;
	public final String fieldId;

	public ASTField(ASTNode record, String fieldId) {
		this.record = record;
		this.fieldId = fieldId;
	}

	@Override
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S>
 e)
			throws SemanticException {
		return visitor.visit(this, e);
	}

}
