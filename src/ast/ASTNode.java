package ast;

import semantics.IValue;
import semantics.Visitor;

public interface ASTNode {
	IValue accept(Visitor<IValue> visitor) throws Exception;
}