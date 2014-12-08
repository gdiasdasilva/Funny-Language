package semantics.interpreter;

import java.util.List;

import semantics.Environment;
import semantics.typeSystem.Type.VType;
import ast.ASTNode;

public class FunValue implements IValue {

	final ASTNode expBody;
	final List<String> parameters;
	final Environment<IValue> e;
	
	public FunValue(ASTNode expBody, List<String> parameters, Environment<IValue> e) {
		this.expBody = expBody;
		this.parameters = parameters;
		this.e = e;
	}

	@Override
	public String toString() {
		return "Function w/ params named " + parameters.toString();
	}
	
	public Environment<IValue> beginScope() {
		return e.beginScope();
	}
	
	public void endScope() {
		e.endScope();
	}

	@Override
	public VType getType() {
		return VType.FUNCTION;
	}
	
}
