package semantics.interpreter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import semantics.BoolValue;
import semantics.FunValue;
import semantics.Environment;
import semantics.IValue;
import semantics.IntValue;
import semantics.RefValue;
import semantics.SemanticException;
import semantics.StringValue;
import semantics.TypeErrorException;
import semantics.UndefinedIdException;
import semantics.Visitor;
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
import ast.ASTNode;
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
import ast.Param;

public class EvalVisitor implements Visitor<IValue> {
	
	@Override
	public IValue visit(ASTNum num, Environment<IValue> e) {
		return num.integer;
	}
	
	@Override
	public IValue visit(ASTBool truth, Environment<IValue> e) {
		return truth.bool;
	}
	
	@Override
	public IValue visit(ASTString astString, Environment<IValue> e) {
		return astString.string;
	}

	@Override
	public IValue visit(ASTPlus plus, Environment<IValue> e) throws SemanticException {
		IValue l = plus.l.accept(this, e);
		IValue r = plus.r.accept(this, e);
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new IntValue(vl.getVal() + vr.getVal());
		}
		else if (l.typeOf() == IValue.VType.STRING && r.typeOf() == IValue.VType.STRING)
		{
			StringValue vl = (StringValue) l;
			StringValue vr = (StringValue) r;
			return new StringValue(vl.getVal() + vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to add values with different type.");
	}

	@Override
	public IValue visit(ASTSub sub, Environment<IValue> e) throws SemanticException {
		IValue l = sub.l.accept(this, e);
		IValue r = sub.r.accept(this, e);		
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new IntValue(vl.getVal() - vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to subtract non-integer values.");
	}

	@Override
	public IValue visit(ASTMul mul, Environment<IValue> e) throws SemanticException {
		IValue l = mul.l.accept(this, e);
		IValue r = mul.r.accept(this, e);
		
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new IntValue(vl.getVal() * vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to multiply non-integer values.");
	}

	@Override
	public IValue visit(ASTDiv div, Environment<IValue> e) throws SemanticException {
		IValue l = div.l.accept(this, e);
		IValue r = div.r.accept(this, e);
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new IntValue(vl.getVal() / vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to divide non-integer values.");	
	}

	@Override
	public IValue visit(ASTUnMinus um, Environment<IValue> e) throws SemanticException {
		IntValue v = (IntValue) um.v.accept(this, e);
		if(v.typeOf() == IValue.VType.INTEGER)
		{
			IntValue vv = (IntValue) v;
			return new IntValue(-vv.getVal());	
		}
		else
			throw new TypeErrorException("Trying to use unary minus with non-integer value.");
	}
	
	@Override
	public IValue visit(ASTEq eq, Environment<IValue> e) throws SemanticException {
		IValue lval = eq.l.accept(this, e);
		IValue rval = eq.r.accept(this, e);
		if (lval.typeOf() == rval.typeOf()) {
			switch (lval.typeOf()) {
			case INTEGER:
				IntValue intLval = (IntValue) lval;
				IntValue intRval = (IntValue) rval;
				return new BoolValue(intLval.getVal() == intRval.getVal());
			case BOOLEAN:
				BoolValue boolLval = (BoolValue) lval;
				BoolValue boolRval = (BoolValue) rval;
				return new BoolValue(boolLval.getVal() == boolRval.getVal());
			case STRING:
				StringValue stringLval = (StringValue) lval;
				StringValue stringRval = (StringValue) rval;
				return new BoolValue(stringLval.getVal().equals(stringRval.getVal()));
//			case REFERENCE:
//				break;
			default:
				throw new TypeErrorException("Operation not defined for this type");
			}
		} else
			throw new TypeErrorException("Can't compare values of different types");
	}

	@Override
	public IValue visit(ASTAnd and, Environment<IValue> e) throws SemanticException{
		IValue l = and.l.accept(this, e);
		IValue r = and.r.accept(this, e);		
		if (l.typeOf() == IValue.VType.BOOLEAN && r.typeOf() == IValue.VType.BOOLEAN) {
			BoolValue vl = (BoolValue) l;
			BoolValue vr = (BoolValue) r;
			return new BoolValue(vl.getVal() && vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to AND non-boolean values.");
	}

	@Override
	public IValue visit(ASTOr or, Environment<IValue> e) throws SemanticException {
		IValue l = or.l.accept(this, e);
		IValue r = or.r.accept(this, e);
		if (l.typeOf() == IValue.VType.BOOLEAN && r.typeOf() == IValue.VType.BOOLEAN) {
			BoolValue vl = (BoolValue) l;
			BoolValue vr = (BoolValue) r;
			return new BoolValue(vl.getVal() || vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to OR non-boolean values.");
	}

	@Override
	public IValue visit(ASTNeq neq, Environment<IValue> e) throws SemanticException {
		IValue lval = neq.l.accept(this, e);
		IValue rval = neq.r.accept(this, e);
		if (lval.typeOf() == rval.typeOf()) {
			switch (lval.typeOf()) {
			case INTEGER:
				IntValue intLval = (IntValue) lval;
				IntValue intRval = (IntValue) rval;
				return new BoolValue(intLval.getVal() != intRval.getVal());
			case BOOLEAN:
				BoolValue boolLval = (BoolValue) lval;
				BoolValue boolRval = (BoolValue) rval;
				return new BoolValue(boolLval.getVal() != boolRval.getVal());
			case STRING:
				StringValue stringLval = (StringValue) lval;
				StringValue stringRval = (StringValue) rval;
				return new BoolValue(!stringLval.getVal().equals(stringRval.getVal()));
//			case REFERENCE:
//				break;
			default:
				throw new TypeErrorException("Operation not defined for this type");
			}
		} else
			throw new TypeErrorException("Can't compare values of different types");
	}

	@Override
	public IValue visit(ASTLseq lseq, Environment<IValue> e) throws SemanticException {
		IValue l = lseq.l.accept(this, e);
		IValue r = lseq.r.accept(this, e);
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new BoolValue(vl.getVal() <= vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to LSorEQthan non-integer values.");		
	}

	@Override
	public IValue visit(ASTGreq greq, Environment<IValue> e) throws SemanticException{
		IValue l = greq.l.accept(this, e);
		IValue r = greq.r.accept(this, e);
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new BoolValue(vl.getVal() >= vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to GRorEQthan non-integer values.");
	}

	@Override
	public IValue visit(ASTLs ls, Environment<IValue> e) throws SemanticException {
		IValue l = ls.l.accept(this, e);
		IValue r = ls.r.accept(this, e);
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new BoolValue(vl.getVal() < vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to LSthan non-integer values.");
	}

	@Override
	public IValue visit(ASTGr gr, Environment<IValue> e) throws SemanticException{
		IValue l = gr.l.accept(this, e);
		IValue r = gr.r.accept(this, e);
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new BoolValue(vl.getVal() > vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to GRthan non-integer values.");
	}

	@Override
	public IValue visit(ASTCond cond, Environment<IValue> e) throws SemanticException{
		IValue c = cond.condNode.accept(this, e);
		if(c.typeOf() == IValue.VType.BOOLEAN)
		{
			BoolValue vc = (BoolValue) c;
			return (vc.getVal() ? cond.thenNode.accept(this, e) : cond.elseNode.accept(this, e));
		}
		throw new TypeErrorException("If's condition must be a boolean expression.");
	}

	@Override
	public IValue visit(ASTNot n, Environment<IValue> e) throws SemanticException {
		IValue v = n.v.accept(this, e);
		if(v.typeOf() == IValue.VType.BOOLEAN)
		{
			BoolValue vv = (BoolValue) v;
			return new BoolValue(!vv.getVal());
		}
		else
			throw new TypeErrorException("Trying to NOT a non-boolean value.");
	}
	
	@Override
	public IValue visit(ASTId id, Environment<IValue> e) throws SemanticException {
		if (e != null)
			return e.find(id.id);
		throw new UndefinedIdException("Undefined id " + id);
	}

	@Override
	public IValue visit(ASTDecl decl, Environment<IValue> e) throws SemanticException
	{		
		e = e.beginScope();
		
		for (int i = 0; i < decl.ids.size( ); i++)
		{
			e.assoc(decl.ids.get(i), decl.defs.get(i).accept(this, e));
		}
		
		IValue v = decl.body.accept(this, e);
		
		e.endScope();
		return v;
	}

	@Override
	public IValue visit(ASTAssign astAssign, Environment<IValue> e) throws SemanticException {
		IValue lval = astAssign.l.accept(this, e);
		IValue rval = astAssign.r.accept(this, e);
		if (lval.typeOf() == IValue.VType.REFERENCE) {
			RefValue lref = (RefValue) lval;
			lref.setVal(rval);
			return rval;
		} else
			throw new TypeErrorException("Assign's L-hand-val must be a reference");
	}

	@Override
	public IValue visit(ASTDeref astDeref, Environment<IValue> e) throws SemanticException {
		IValue v = astDeref.node.accept(this, e);
		if (v.typeOf() == IValue.VType.REFERENCE) {
			return ((RefValue) v).getVal();
		}
		else
			throw new TypeErrorException("Can only deref a REFERENCE.");
	}
	
	@Override
	public IValue visit(ASTWhile astWhile, Environment<IValue> e) throws SemanticException {
		IValue c = astWhile.c.accept(this, e);
		if (c.typeOf() == IValue.VType.BOOLEAN) {
			BoolValue boolc = (BoolValue) c;
			while (boolc.getVal()) {
				astWhile.b.accept(this, e);
				c = astWhile.c.accept(this, e);
				/* the following test is needed to make sure that no Java cast
				 * exception is risen due to a condition initially based in
				 * deref'ing a (mutable) variable containing a boolean value
				 * being replaced afterwards inside the loop by a non-boolean */
				if (c.typeOf() == IValue.VType.BOOLEAN)
					boolc = (BoolValue) astWhile.c.accept(this, e);
				else
					throw new TypeErrorException(
							"While's condition must be a boolean expression");
			}
			return new BoolValue(true);
		}
		else
			throw new TypeErrorException("While's condition must be a boolean expression");
	}
	
	@Override
	public IValue visit(ASTNew astNew, Environment<IValue> e) throws SemanticException {
		IValue v0 = astNew.node.accept(this, e);
		RefValue ref = new RefValue();
		ref.setVal(v0);
		return ref;
	}
	
	@Override
	public IValue visit(ASTPrint astPrint, Environment<IValue> e) throws SemanticException {
		IValue val = astPrint.node.accept(this, e);
		System.out.print(val);
		return val;
	}

	@Override
	public IValue visit(ASTPrintln astPrintln, Environment<IValue> e) throws SemanticException {
		IValue val = astPrintln.node.accept(this, e);
		System.out.println(val);
		return val;
	}

	@Override
	public IValue visit(ASTSeq astSeq, Environment<IValue> e) throws SemanticException {
		astSeq.f.accept(this, e);
		return astSeq.s.accept(this, e);
	}

	@Override
	public IValue visit(ASTCall astCall, Environment<IValue> e) throws SemanticException {
		IValue vfun = astCall.fun.accept(this, e);
		List<IValue> vargs = new ArrayList<IValue>();
		
		for (ASTNode arg : astCall.args)
			vargs.add(arg.accept(this, e));
		
		FunValue vf;
		
		if (vfun.typeOf() == IValue.VType.FUNCTION)
		{
			vf = (FunValue) vfun;
		}
		else
			throw new TypeErrorException("Wrong type in function.");
		
		Environment<IValue> e1 = vf.beginScope();
		
		Iterator<Param> pit = vf.parameters.iterator();
		Iterator<IValue> vit = vargs.iterator();
		
		while (pit.hasNext())
			while (vit.hasNext())
				e1.assoc(pit.next().paramName, vit.next());
		
		IValue result = vf.expBody.accept(this, e1);
		e1.endScope();
		return result;
	}

	@Override
	public IValue visit(ASTFun astFun, Environment<IValue> e) throws SemanticException {
		return new FunValue(astFun.body, astFun.params, e);
	}

	@Override
	public IValue visit(ASTIf astIf, Environment<IValue> e) throws SemanticException {
		if (((BoolValue) astIf.condNode.accept(this, e)).getVal())
			return astIf.thenNode.accept(this, e);
		if (astIf.elseNode != null)
			return astIf.elseNode.accept(this, e);
		return null;
	}
}
