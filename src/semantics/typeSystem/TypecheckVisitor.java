package semantics.typeSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import semantics.IEnv;
import semantics.SemanticException;
import semantics.StringType;
import semantics.TypeErrorException;
import semantics.Visitor;
import semantics.typeSystem.IType.VType;
import ast.*;
import ast.TypeTag.FunTypeTag;
import ast.TypeTag.RefTypeTag;

public class TypecheckVisitor implements Visitor<IType> {
	
	private final IntType intType;
	private final BoolType boolType;
	private final CmdType cmdType;
	private final StringType stringType;
	
	public TypecheckVisitor() {
		intType = new IntType();
		boolType = new BoolType();
		cmdType = new CmdType();
		stringType = new StringType();
	}

	@Override
	public IType visit(ASTNum num, IEnv e) {
//		return new IntType();
		return intType;
	}

	@Override
	public IType visit(ASTBool truth, IEnv e) {
//		return new BoolType();
		return boolType;
	}

	@Override
	public IType visit(ASTString astString, IEnv e) {
//		return new StringType();
		return stringType;
	}

	@Override
	public IType visit(ASTPlus plus, IEnv e) throws SemanticException {
		IType lType = plus.l.accept(this, e);
		IType rType = plus.r.accept(this, e);
//		if (lType.getType() == IType.VType.INTEGER && rType.getType() == IType.VType.INTEGER)
		if (lType == intType && rType == intType)
			return intType;
		throw new TypeErrorException("Can't add " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTSub sub, IEnv e) throws SemanticException {
		IType lType = sub.l.accept(this, e);
		IType rType = sub.r.accept(this, e);
//		if (lType.getType() == IType.VType.INTEGER && rType.getType() == IType.VType.INTEGER)
		if (lType == intType && rType == intType)
			return intType;
		throw new TypeErrorException("Can't subtract " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTMul mul, IEnv e) throws SemanticException {
		IType lType = mul.l.accept(this, e);
		IType rType = mul.r.accept(this, e);
//		if (lType.getType() == IType.VType.INTEGER && rType.getType() == IType.VType.INTEGER)
		if (lType == intType && rType == intType)
			return intType;
		throw new TypeErrorException("Can't multiply " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTDiv div, IEnv e) throws SemanticException {
		IType lType = div.l.accept(this, e);
		IType rType = div.r.accept(this, e);
//		if (lType.getType() == IType.VType.INTEGER && rType.getType() == IType.VType.INTEGER)
		if (lType == intType && rType == intType)
			return intType;
		throw new TypeErrorException("Can't divide " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTUnMinus um, IEnv e) throws SemanticException {
		IType vType = um.v.accept(this, e);
//		if (vType.getType() == IType.VType.INTEGER)
		if (vType == intType)
			return intType;
		throw new TypeErrorException("Trying to unminus " + vType);
	}

	@Override
	public IType visit(ASTNot n, IEnv e) throws SemanticException {
		IType vType = n.v.accept(this, e);
//		if (vType.getType() == IType.VType.BOOLEAN)
		if (vType == boolType)
			return boolType;
		throw new TypeErrorException("Trying to not " + vType);
	}

	@Override
	public IType visit(ASTEq eq, IEnv e) throws SemanticException {
		IType lType = eq.l.accept(this, e);
		IType rType = eq.r.accept(this, e);
		if (lType == rType)
			if (lType == intType || lType == boolType || lType == stringType)
				return boolType;
		throw new TypeErrorException("Can't compare (eqt) " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTNeq neq, IEnv e) throws SemanticException {
		IType lType = neq.l.accept(this, e);
		IType rType = neq.r.accept(this, e);
		if (lType == rType)
			if (lType == intType || lType == boolType || lType == stringType)
				return boolType;
		throw new TypeErrorException("Can't compare (neqt) " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTLs ls, IEnv e) throws SemanticException {
		IType lType = ls.l.accept(this, e);
		IType rType = ls.r.accept(this, e);
		if (lType == rType)
			if (lType == intType || lType == boolType || lType == stringType)
				return boolType;
		throw new TypeErrorException("Can't compare (lst) " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTGr gr, IEnv e) throws SemanticException {
		IType lType = gr.l.accept(this, e);
		IType rType = gr.r.accept(this, e);
		if (lType == rType)
			if (lType == intType || lType == boolType || lType == stringType)
				return boolType;
		throw new TypeErrorException("Can't compare (grt) " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTLseq lseq, IEnv e) throws SemanticException {
		IType lType = lseq.l.accept(this, e);
		IType rType = lseq.r.accept(this, e);
		if (lType == rType)
			if (lType == intType || lType == boolType || lType == stringType)
				return boolType;
		throw new TypeErrorException("Can't compare (lseqt) " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTGreq greq, IEnv e) throws SemanticException {
		IType lType = greq.l.accept(this, e);
		IType rType = greq.r.accept(this, e);
		if (lType == rType)
			if (lType == intType || lType == boolType || lType == stringType)
				return boolType;
		throw new TypeErrorException("Can't compare (greqt) " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTAnd and, IEnv e) throws SemanticException {
		IType lType = and.l.accept(this, e);
		IType rType = and.r.accept(this, e);
//		if (lType.getType() == IType.VType.BOOLEAN && rType.getType() == IType.VType.BOOLEAN)
		if (lType == boolType && rType == boolType)
			return boolType;
		throw new TypeErrorException("Can't AND " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTOr or, IEnv e) throws SemanticException {
		IType lType = or.l.accept(this, e);
		IType rType = or.r.accept(this, e);
//		if (lType.getType() == IType.VType.BOOLEAN && rType.getType() == IType.VType.BOOLEAN)
		if (lType == boolType && rType == boolType)
			return boolType;
		throw new TypeErrorException("Can't OR " + lType + " and " + rType);
	}

	@Override
	public IType visit(ASTCond cond, IEnv e) throws SemanticException
	{
		IType condType = cond.condNode.accept(this, e);
		IType thenType = cond.thenNode.accept(this, e);
		IType elseType = cond.elseNode.accept(this, e);

		if (condType == boolType)
			if (thenType.equals(elseType))
				if (thenType.equals(intType) || thenType.equals(boolType) || thenType.equals(stringType) || thenType.getType() == VType.FUNCTION)
					return thenType;
				else
					throw new TypeErrorException(thenType + " invalid expression type.");
			else
				throw new TypeErrorException("\"Then\" expression of type " + thenType + " and \"else\" expression of type " + elseType + ".");
		throw new TypeErrorException("Condition is " + condType + ". Should be BOOL.");
	}

	@Override
	public IType visit(ASTId id, IEnv e) throws SemanticException {
		return ((TyEnv) e).find(id.id);
	}

	@Override
	public IType visit(ASTDecl decl, IEnv e) throws SemanticException 
	{
		e = e.beginScope();

		for (int i = 0; i < decl.ids.size( ); i++)
		{
			((TyEnv) e).assoc(decl.ids.get(i), decl.defs.get(i).accept(this, e));
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

		if (ref.getType() == IType.VType.REFERENCE)
		{
			if (((RefType) ref).type.equals(value.getType()))
				return new CmdType();
			throw new TypeErrorException("Memory cell was of type " + ((RefType)ref).type + ", value had type " + value);
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
	public IType visit(ASTDeref astDeref, IEnv e) throws SemanticException {
		IType refType = astDeref.node.accept(this, e);
		if (refType.getType() == IType.VType.REFERENCE)
			return ((RefType) refType).type;
		throw new TypeErrorException("Expression to deref was of (illegal) type " + refType);
	}

	@Override
	public IType visit(ASTPrint astPrint, IEnv e) throws SemanticException {
		if (!astPrint.node.accept(this, e).equals(cmdType))
			return cmdType;
		throw new TypeErrorException("Can't print a command");
	}

	@Override
	public IType visit(ASTPrintln astPrintln, IEnv e) throws SemanticException {
		if (!astPrintln.node.accept(this, e).equals(cmdType))
			return cmdType;
		throw new TypeErrorException("Can't print a command");
	}

	@Override
	public IType visit(ASTSeq astSeq, IEnv e) throws SemanticException {
		IType fType = astSeq.f.accept(this, e);
		IType sType = astSeq.s.accept(this, e);
		if (fType == cmdType || sType == cmdType)
			return cmdType;
		throw new TypeErrorException("Can't sequentiate (not COMMAND) " + fType + " and " + sType + " expressions.");
	}

	@Override
	public IType visit(ASTCall astCall, IEnv e) throws SemanticException {
		IType ft = astCall.fun.accept(this, e);
		if (ft.getType() == IType.VType.FUNCTION) {
			if (((FunType) ft).paramTypes.size() == astCall.args.size()) {
				Iterator<IType> paramTypesIt = ((FunType) ft).paramTypes.iterator();
				Iterator<ASTNode> argsIt = astCall.args.iterator();
				while (paramTypesIt.hasNext())
					if (!paramTypesIt.next().equals(argsIt.next().accept(this, e)))
						throw new TypeErrorException("Incompatible parameter/argument type");
				return ((FunType) ft).returnType;
			}
			throw new TypeErrorException("Incompatible parameter/argument type");
		}
		throw new TypeErrorException("Not a function");
	}

	private IType getTypeForTypeTag(TypeTag tt) {
		if (tt == TypeTag.getIntTypeTag())
			return intType;
		else if (tt == TypeTag.getBooleanTypeTag())
			return boolType;
		else if (tt == TypeTag.getStringTypeTag())
			return stringType;
		else if (tt == TypeTag.getCommandTypeTag())
			return cmdType;
		else if (tt.getTypeTagId() == TypeTag.TypeT.FUNCTION) {
			List<IType> paramTypes = new ArrayList<IType>();
			IType returnType = getTypeForTypeTag(((FunTypeTag) tt).returnTypeTag);
			for (TypeTag ptt : ((FunTypeTag) tt).paramTypeTags)
				paramTypes.add(getTypeForTypeTag(ptt));
			return new FunType(paramTypes, returnType);
		}
		else
			return new RefType(getTypeForTypeTag(((RefTypeTag) tt).referenceToTypeTag));
	}
	
	@Override
	public IType visit(ASTFun astFun, IEnv e) throws SemanticException
	{
		IType bodyType = astFun.body.accept(this, e);
		List<IType> paramTypes = new ArrayList<IType>();
		for (Param param : astFun.params) {
			IType pt = getTypeForTypeTag(param.paramTypeTag);
			((TyEnv) e).assoc(param.paramName, pt);
			paramTypes.add(pt);
		}
		return new FunType(paramTypes, bodyType);	
	}

	@Override
	public IType visit(ASTIf astIf, IEnv e) throws SemanticException {
		IType condType = astIf.condNode.accept(this, e);
		IType thenType = astIf.thenNode.accept(this, e);
		if (condType == boolType) {
			if (thenType == cmdType)
				if (astIf.elseNode == null)
					return cmdType;
				else {
					IType elseType = astIf.elseNode.accept(this, e);
					if (elseType == cmdType)
						return cmdType;
					else
						throw new TypeErrorException("else branch is of type " + elseType + ". Should be a COMMAND.");
				}
			else
				throw new TypeErrorException("then branch is of type " + thenType + ". Should be a COMMAND.");
		}
		throw new TypeErrorException("Condition must be of type BOOLEAN. It is of type " + condType);
	}
}
