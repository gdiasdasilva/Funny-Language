package semantics;

import ast.ASTAnd;
import ast.ASTAssign;
import ast.ASTBool;
import ast.ASTCall;
import ast.ASTCond;
import ast.ASTDecl;
import ast.ASTDeref;
import ast.ASTDiv;
import ast.ASTEq;
import ast.ASTFun;
import ast.ASTGr;
import ast.ASTGreq;
import ast.ASTId;
import ast.ASTLs;
import ast.ASTLseq;
import ast.ASTMul;
import ast.ASTNeq;
import ast.ASTNew;
import ast.ASTNot;
import ast.ASTNum;
import ast.ASTOr;
import ast.ASTPlus;
import ast.ASTPrint;
import ast.ASTPrintln;
import ast.ASTSeq;
import ast.ASTString;
import ast.ASTSub;
import ast.ASTUnMinus;
import ast.ASTWhile;

public class TypecheckVisitor implements Visitor<IType> {

	@Override
	public IType visit(ASTNum num, IEnv e) {
		return new IntType();
	}

	@Override
	public IType visit(ASTBool truth, IEnv e) {
		return new BoolType();
	}

	@Override
	public IType visit(ASTString astString, IEnv e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTPlus plus, IEnv e) throws SemanticException {
		IType lType = plus.l.accept(this, e);
		IType rType = plus.r.accept(this, e);
		if (lType.getType() == IType.VType.INTEGER && rType.getType() == IType.VType.INTEGER)
			return lType;
		throw new TypeErrorException("Trying to sum " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTSub sub, IEnv e) throws SemanticException {
		IType lType = sub.l.accept(this, e);
		IType rType = sub.r.accept(this, e);
		if (lType.getType() == IType.VType.INTEGER && rType.getType() == IType.VType.INTEGER)
			return lType;
		throw new TypeErrorException("Trying to subtract " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTMul mul, IEnv e) throws SemanticException {
		IType lType = mul.l.accept(this, e);
		IType rType = mul.r.accept(this, e);
		if (lType.getType() == IType.VType.INTEGER && rType.getType() == IType.VType.INTEGER)
			return lType;
		throw new TypeErrorException("Trying to multiply " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTDiv div, IEnv e) throws SemanticException {
		IType lType = div.l.accept(this, e);
		IType rType = div.r.accept(this, e);
		if (lType.getType() == IType.VType.INTEGER && rType.getType() == IType.VType.INTEGER)
			return lType;
		throw new TypeErrorException("Trying to divide " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTUnMinus um, IEnv e) throws SemanticException {
		IType vType = um.v.accept(this, e);
		if (vType.getType() == IType.VType.INTEGER)
			return vType;
		throw new TypeErrorException("Trying to unminus " + vType);
	}

	@Override
	public IType visit(ASTNot n, IEnv e) throws SemanticException {
		IType vType = n.v.accept(this, e);
		if (vType.getType() == IType.VType.BOOLEAN)
			return vType;
		throw new TypeErrorException("Trying to not " + vType);
	}

	@Override
	public IType visit(ASTEq eq, IEnv e) throws SemanticException {
		IType lType = eq.l.accept(this, e);
		IType rType = eq.r.accept(this, e);
		
		switch (lType.getType())
		{
		case BOOLEAN:
		case INTEGER:
		case STRING:
			if (lType.getType() == rType.getType())
				return lType;
		default:
			break;
		}
		
		throw new TypeErrorException("Trying to equal " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTNeq neq, IEnv e) throws SemanticException {
		IType lType = neq.l.accept(this, e);
		IType rType = neq.r.accept(this, e);
		
		switch (lType.getType())
		{
		case BOOLEAN:
		case INTEGER:
		case STRING:
			if (lType.getType() == rType.getType())
				return lType;
		default:
			break;
		}
		
		throw new TypeErrorException("Trying to not equal " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTLs ls, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTGr gr, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTLseq lseq, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTGreq greq, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTAnd and, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTOr or, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTCond cond, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTId id, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTDecl decl, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTAssign astAssign, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTWhile astWhile, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTNew astNew, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTDeref astDeref, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTPrint astPrint, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTPrintln astPrintln, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTSeq astSeq, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTCall astCall, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTFun astFun, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

}
