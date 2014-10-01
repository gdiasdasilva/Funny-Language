package semantics.compiler;

import ast.ASTAnd;
import ast.ASTCond;
import ast.ASTDiv;
import ast.ASTEq;
import ast.ASTGr;
import ast.ASTGreq;
import ast.ASTLs;
import ast.ASTLseq;
import ast.ASTMul;
import ast.ASTNeq;
import ast.ASTNum;
import ast.ASTOr;
import ast.ASTPlus;
import ast.ASTSub;
import ast.ASTTruth;
import ast.ASTUnMinus;
import semantics.CodeBlock;
import semantics.CodeBlock;
import semantics.IValue;
import semantics.Op;
import semantics.Visitor;

public class CompilerVisitor implements Visitor<CodeBlock> {

	@Override
	public CodeBlock visit(ASTNum num) {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(((IValue) num.iVal).i);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTTruth truth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTPlus plus) {
		CodeBlock cb = plus.l.accept(this);
		cb.merge(plus.r.accept(this));
		cb.insertOp(Op.ADD);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTSub sub) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTMul mul) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTDiv div) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTUnMinus um) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTEq eq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTNeq neq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTLs ls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTGr gr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTLseq lseq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTGreq greq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTAnd and) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTOr or) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTCond cond) {
		// TODO Auto-generated method stub
		return null;
	}

}
