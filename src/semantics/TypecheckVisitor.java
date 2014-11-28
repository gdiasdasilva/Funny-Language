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
import ast.ASTIf;
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
		return new StringType();
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
				return new BoolType();
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
				return new BoolType();
		default:
			break;
		}

		throw new TypeErrorException("Trying to not equal " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTLs ls, IEnv e) throws SemanticException {
		IType lType = ls.l.accept(this, e);
		IType rType = ls.r.accept(this, e);
		if (lType.getType() == IType.VType.INTEGER && rType.getType() == IType.VType.INTEGER)
			return new BoolType();
		throw new TypeErrorException("Trying to 'lesser than' " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTGr gr, IEnv e) throws SemanticException {
		IType lType = gr.l.accept(this, e);
		IType rType = gr.r.accept(this, e);
		if (lType.getType() == IType.VType.INTEGER && rType.getType() == IType.VType.INTEGER)
			return new BoolType();
		throw new TypeErrorException("Trying to 'greater than' " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTLseq lseq, IEnv e) throws SemanticException {
		IType lType = lseq.l.accept(this, e);
		IType rType = lseq.r.accept(this, e);
		if (lType.getType() == IType.VType.INTEGER && rType.getType() == IType.VType.INTEGER)
			return new BoolType();
		throw new TypeErrorException("Trying to 'lesser or equal than' " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTGreq greq, IEnv e) throws SemanticException {
		IType lType = greq.l.accept(this, e);
		IType rType = greq.r.accept(this, e);
		if (lType.getType() == IType.VType.INTEGER && rType.getType() == IType.VType.INTEGER)
			return new BoolType();
		throw new TypeErrorException("Trying to 'greater or equal than' " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTAnd and, IEnv e) throws SemanticException {
		IType lType = and.l.accept(this, e);
		IType rType = and.r.accept(this, e);
		if (lType.getType() == IType.VType.BOOLEAN && rType.getType() == IType.VType.BOOLEAN)
			return lType;
		throw new TypeErrorException("Trying to and " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTOr or, IEnv e) throws SemanticException {
		IType lType = or.l.accept(this, e);
		IType rType = or.r.accept(this, e);
		if (lType.getType() == IType.VType.BOOLEAN && rType.getType() == IType.VType.BOOLEAN)
			return lType;
		throw new TypeErrorException("Trying to and " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTCond cond, IEnv e) throws SemanticException
	{
		IType condType = cond.condNode.accept(this, e);
		IType thenType = cond.thenNode.accept(this, e);
		IType elseType = cond.elseNode.accept(this, e);

		if(condType.getType() != IType.VType.BOOLEAN)
			throw new TypeErrorException("Trying to IF a " + condType + " condition.");

		if(thenType.getType() == elseType.getType())
			return thenType;
		else
			throw new TypeErrorException("Trying to then and else: " + thenType + " and " + elseType + ", respectively.");
	}

	@Override
	public IType visit(ASTId id, IEnv e) throws SemanticException
	{
		return ((TyEnv) e).find(id.id);
	}

	@Override
	public IType visit(ASTDecl decl, IEnv e) throws SemanticException 
	{
		e = e.beginScope();

		for (int i = 0; i < decl.ids.size( ); i++)
		{
			((TyEnv)e).assoc(decl.ids.get(i), decl.defs.get(i).accept(this, e));
		}

		IType v = decl.body.accept(this, e);

		e.endScope();
		return v;
	}

	@Override
	public IType visit(ASTAssign astAssign, IEnv e) throws SemanticException
	{
		IType ref = astAssign.l.accept(this, e);
		IType value = astAssign.r.accept(this, e);

		if(ref.getType() == IType.VType.REFERENCE)
		{
			if(((RefType)ref).getRefType().getType() == value.getType())
				return new CmdType();

			throw new TypeErrorException("Memory cell was of type " + ((RefType)ref).getRefType() + ", value had type " + value);
		}	

		throw new TypeErrorException("Reference was of type " + ref);
	}

	@Override
	public IType visit(ASTWhile astWhile, IEnv e) throws SemanticException
	{
		IType condType = astWhile.c.accept(this, e);
		IType bodyType = astWhile.b.accept(this, e);

		if(condType.getType() == IType.VType.BOOLEAN && bodyType.getType() == IType.VType.CMD)
			return bodyType;
		else
			throw new TypeErrorException("Condition was " + condType + ", body was " + bodyType);
	}

	@Override
	public IType visit(ASTNew astNew, IEnv e) throws SemanticException
	{
		return new RefType(astNew.node.accept(this, e));
	}

	@Override
	public IType visit(ASTDeref astDeref, IEnv e) throws SemanticException
	{
		IType ref = astDeref.node.accept(this, e);

		if(ref.getType() == IType.VType.REFERENCE)
		{
			return ((RefType) ref).getRefType();
		}
		else
			throw new TypeErrorException("Reference was of type " + ref);
	}

	@Override
	public IType visit(ASTPrint astPrint, IEnv e) throws SemanticException {
		return new CmdType();
	}

	@Override
	public IType visit(ASTPrintln astPrintln, IEnv e) throws SemanticException {
		return new CmdType();
	}

	@Override
	public IType visit(ASTSeq astSeq, IEnv e) throws SemanticException {
		astSeq.f.accept(this, e);
		return astSeq.s.accept(this, e);
	}

	@Override
	public IType visit(ASTCall astCall, IEnv e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType visit(ASTFun astFun, IEnv e) throws SemanticException
	{
		IType bodyType = astFun.body.accept(this, e);

		for(int i = 0; i < astFun.types.size(); i++)
		{
			((TyEnv) e).assoc(astFun.params.get(i), astFun.types.get(i));
		}

		return new FunType(astFun.types, bodyType);	
	}

	@Override
	public IType visit(ASTIf astIf, IEnv e) throws SemanticException {
		IType condType = astIf.condNode.accept(this, e);
		IType thenType = astIf.thenNode.accept(this, e);
		
		if(condType.getType() != IType.VType.BOOLEAN)
			throw new TypeErrorException("Trying to IF a " + condType + " condition.");
		
		if(astIf.elseNode == null)
		{
			return thenType;
		}
		
		IType elseType = astIf.elseNode.accept(this, e);		

		if(thenType.getType() == elseType.getType())
			return thenType;
		else
			throw new TypeErrorException("Trying to then and else: " + thenType + " and " + elseType + ", respectively.");
	}
}
