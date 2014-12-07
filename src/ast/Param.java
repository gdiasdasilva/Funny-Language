package ast;

import ast.TypeTag;

public final class Param {
	
	public final String paramName;
	public final TypeTag paramTypeTag;

	public Param(String paramName, TypeTag paramTypeTag) {
		this.paramName = paramName;
		this.paramTypeTag = paramTypeTag;
	}

}
