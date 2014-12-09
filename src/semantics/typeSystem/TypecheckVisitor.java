package semantics.typeSystem;

import java.awt.print.Printable;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;
import semantics.typeSystem.Type.VType;
import semantics.values.FunValue;
import semantics.values.IValue;
import ast.*;
import ast.TypeTag.FunTypeTag;
import ast.TypeTag.RefTypeTag;

public class TypecheckVisitor implements Visitor<Type> {

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
	public Type visit(ASTNum num, Environment<Type> e) {
		return intType;
	}

	@Override
	public Type visit(ASTBool truth, Environment<Type> e) {
		return boolType;
	}

	@Override
	public Type visit(ASTString astString, Environment<Type> e) {
		return stringType;
	}

	@Override
	public Type visit(ASTPlus plus, Environment<Type> e) throws SemanticException {
		Type lType = plus.l.accept(this, e);
		Type rType = plus.r.accept(this, e);
		if (lType == intType && rType == intType)
			return intType;
		throw new TypeErrorException("Can't add " + lType + " and " + rType);
	}

	@Override
	public Type visit(ASTSub sub, Environment<Type> e) throws SemanticException {
		Type lType = sub.l.accept(this, e);
		Type rType = sub.r.accept(this, e);
		if (lType == intType && rType == intType)
			return intType;
		throw new TypeErrorException("Can't subtract " + lType + " and " + rType);
	}

	@Override
	public Type visit(ASTMul mul, Environment<Type> e) throws SemanticException {
		Type lType = mul.l.accept(this, e);
		Type rType = mul.r.accept(this, e);
		if (lType == intType && rType == intType)
			return intType;
		throw new TypeErrorException("Can't multiply " + lType + " and " + rType);
	}

	@Override
	public Type visit(ASTDiv div, Environment<Type> e) throws SemanticException {
		Type lType = div.l.accept(this, e);
		Type rType = div.r.accept(this, e);
		if (lType == intType && rType == intType)
			return intType;
		throw new TypeErrorException("Can't divide " + lType + " and " + rType);
	}

	@Override
	public Type visit(ASTUnMinus um, Environment<Type> e) throws SemanticException {
		Type vType = um.v.accept(this, e);
		if (vType == intType)
			return intType;
		throw new TypeErrorException("Trying to unminus " + vType);
	}

	@Override
	public Type visit(ASTNot n, Environment<Type> e) throws SemanticException {
		Type vType = n.v.accept(this, e);
		if (vType == boolType)
			return boolType;
		throw new TypeErrorException("Trying to not " + vType);
	}

	@Override
	public Type visit(ASTEq eq, Environment<Type> e) throws SemanticException {
		Type lType = eq.l.accept(this, e);
		Type rType = eq.r.accept(this, e);
		if (lType == rType)
			if (lType == intType || lType == boolType || lType == stringType)
				return boolType;
		throw new TypeErrorException("Can't compare (eqt) " + lType + " and " + rType);
	}

	@Override
	public Type visit(ASTNeq neq, Environment<Type> e) throws SemanticException {
		Type lType = neq.l.accept(this, e);
		Type rType = neq.r.accept(this, e);
		if (lType == rType)
			if (lType == intType || lType == boolType || lType == stringType)
				return boolType;
		throw new TypeErrorException("Can't compare (neqt) " + lType + " and " + rType);
	}

	@Override
	public Type visit(ASTLs ls, Environment<Type> e) throws SemanticException {
		Type lType = ls.l.accept(this, e);
		Type rType = ls.r.accept(this, e);
		if (lType == rType)
			if (lType == intType || lType == boolType || lType == stringType)
				return boolType;
		throw new TypeErrorException("Can't compare (lst) " + lType + " and " + rType);
	}

	@Override
	public Type visit(ASTGr gr, Environment<Type> e) throws SemanticException {
		Type lType = gr.l.accept(this, e);
		Type rType = gr.r.accept(this, e);
		if (lType == rType)
			if (lType == intType || lType == boolType || lType == stringType)
				return boolType;
		throw new TypeErrorException("Can't compare (grt) " + lType + " and " + rType);
	}

	@Override
	public Type visit(ASTLseq lseq, Environment<Type> e) throws SemanticException {
		Type lType = lseq.l.accept(this, e);
		Type rType = lseq.r.accept(this, e);
		if (lType == rType)
			if (lType == intType || lType == boolType || lType == stringType)
				return boolType;
		throw new TypeErrorException("Can't compare (lseqt) " + lType + " and " + rType);
	}

	@Override
	public Type visit(ASTGreq greq, Environment<Type> e) throws SemanticException {
		Type lType = greq.l.accept(this, e);
		Type rType = greq.r.accept(this, e);
		if (lType == rType)
			if (lType == intType || lType == boolType || lType == stringType)
				return boolType;
		throw new TypeErrorException("Can't compare (greqt) " + lType + " and " + rType);
	}

	@Override
	public Type visit(ASTAnd and, Environment<Type> e) throws SemanticException {
		Type lType = and.l.accept(this, e);
		Type rType = and.r.accept(this, e);
		if (lType == boolType && rType == boolType)
			return boolType;
		throw new TypeErrorException("Can't AND " + lType + " and " + rType);
	}

	@Override
	public Type visit(ASTOr or, Environment<Type> e) throws SemanticException {
		Type lType = or.l.accept(this, e);
		Type rType = or.r.accept(this, e);
		if (lType == boolType && rType == boolType)
			return boolType;
		throw new TypeErrorException("Can't OR " + lType + " and " + rType);
	}

	@Override
	public Type visit(ASTCond cond, Environment<Type> e) throws SemanticException
	{
		Type condType = cond.condNode.accept(this, e);
		Type thenType = cond.thenNode.accept(this, e);
		Type elseType = cond.elseNode.accept(this, e);

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
	public Type visit(ASTId id, Environment<Type> e) throws SemanticException {
		return e.find(id.id);
	}

	@Override
	public Type visit(ASTDecl decl, Environment<Type> e) throws SemanticException 
	{
		e = e.beginScope();

		for (int i = 0; i < decl.ids.size( ); i++)
		{
			e.assoc(decl.ids.get(i), decl.defs.get(i).accept(this, e));
		}

		Type v = decl.body.accept(this, e);

		e.endScope();
		return v;
	}

	@Override
	public Type visit(ASTAssign astAssign, Environment<Type> e) throws SemanticException
	{
		Type ref = astAssign.l.accept(this, e);
		Type value = astAssign.r.accept(this, e);

		if (ref.getType() == Type.VType.REFERENCE) {
			if (((RefType) ref).type.equals(value))
				return new CmdType();
			throw new TypeErrorException("Memory cell was of type " + ((RefType)ref).type + ", value had type " + value);
		}
		throw new TypeErrorException("Reference was of type " + ref);
	}

	@Override
	public Type visit(ASTWhile astWhile, Environment<Type> e) throws SemanticException
	{
		Type condType = astWhile.c.accept(this, e);
		Type bodyType = astWhile.b.accept(this, e);
		if (condType == boolType && bodyType == cmdType)
			return cmdType;
		throw new TypeErrorException("Condition was " + condType + ", body was " + bodyType);
	}

	@Override
	public Type visit(ASTNew astNew, Environment<Type> e) throws SemanticException {
		return new RefType(astNew.node.accept(this, e));
	}

	@Override
	public Type visit(ASTDeref astDeref, Environment<Type> e) throws SemanticException {
		Type refType = astDeref.node.accept(this, e);
		if (refType.getType() == Type.VType.REFERENCE)
			return ((RefType) refType).type;
		throw new TypeErrorException("Expression to deref was of (illegal) type " + refType);
	}
	
	@Override
	public Type visit(ASTPrint astPrint, Environment<Type> e) throws SemanticException {
		if (!astPrint.node.accept(this, e).equals(cmdType))
			return cmdType;
		throw new TypeErrorException("Can't print a command");
	}

	@Override
	public Type visit(ASTPrintln astPrintln, Environment<Type> e) throws SemanticException {
		if (!astPrintln.node.accept(this, e).equals(cmdType))
			return cmdType;
		throw new TypeErrorException("Can't print a command");
	}

	@Override
	public Type visit(ASTSeq astSeq, Environment<Type> e) throws SemanticException {
		Type fType = astSeq.f.accept(this, e);
		Type sType = astSeq.s.accept(this, e);
		if (fType == cmdType || sType == cmdType)
			return cmdType;
		throw new TypeErrorException("Can't sequentiate (not COMMAND) " + fType + " and " + sType + " expressions.");
	}

	@Override
	public Type visit(ASTCall astCall, Environment<Type> e) throws SemanticException {
		Type ft = astCall.fun.accept(this, e);
		if (ft.getType() == Type.VType.FUNCTION) {
			FunType closureType = (FunType) ft;
			if (astCall.args.size() == closureType.paramTypeTags.size()) {
				List<Type> targs = new ArrayList<Type>();
				for (ASTNode arg : astCall.args)
					targs.add(arg.accept(this, e));
				Iterator<TypeTag> pit = closureType.paramTypeTags.iterator();
				Iterator<Type> tit = targs.iterator();
				Environment<Type> e1 = closureType.beginScope();
				while (pit.hasNext())
					while (tit.hasNext())
						e1.assoc(pit.next(), tit.next());
			}
			throw new TypeErrorException("Incompatible parameter/argument type");
		}
		throw new TypeErrorException("Not a function");
	}

	private Type getTypeForTypeTag(TypeTag tt, Environment<Type> e) {
		if (tt == TypeTag.getIntTypeTag())
			return intType;
		else if (tt == TypeTag.getBooleanTypeTag())
			return boolType;
		else if (tt == TypeTag.getStringTypeTag())
			return stringType;
		else if (tt == TypeTag.getCommandTypeTag())
			return cmdType;
		else if (tt.getTypeTagId() == TypeTag.TypeT.FUNCTION) {
			List<Type> paramTypes = new ArrayList<Type>();
			Type returnType = getTypeForTypeTag(((FunTypeTag) tt).returnTypeTag, e);
			for (TypeTag ptt : ((FunTypeTag) tt).paramTypeTags)
				paramTypes.add(getTypeForTypeTag(ptt, e));
//			return new FunType(paramTypes, returnType);
			return null; // TODO right implementation
		}
		else
			return new RefType(getTypeForTypeTag(((RefTypeTag) tt).referenceToTypeTag, e));
	}
	
	@Override
	public Type visit(ASTFun astFun, Environment<Type> e) throws SemanticException {
		return new FunType(astFun.body, astFun.paramTypeTags, astFun.paramNames, e);
	}

	@Override
	public Type visit(ASTIf astIf, Environment<Type> e) throws SemanticException {
		Type condType = astIf.condNode.accept(this, e);
		Type thenType = astIf.thenNode.accept(this, e);
		if (condType == boolType) {
			if (thenType == cmdType)
				if (astIf.elseNode == null)
					return cmdType;
				else {
					Type elseType = astIf.elseNode.accept(this, e);
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
