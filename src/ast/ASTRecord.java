package ast;

import java.util.Iterator;
import java.util.List;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTRecord implements ASTNode {

	private final List<ASTNode> fields;
	private final List<String> fieldNames;
	
	public ASTRecord(List<ASTNode> fields, List<String> fieldNames) {
		this.fields = fields;
		this.fieldNames = fieldNames;
	}
	
	public Iterator<ASTNode> getFieldsIterator() {
		return fields.iterator();
	}
	
	public Iterator<String> getFieldNamesIterator() {
		return fieldNames.iterator();
	}

	@Override
	public <T> T accept(Visitor<T> visitor, Environment<T> e)
			throws SemanticException {
		return visitor.visit(this, e);
	}

}
