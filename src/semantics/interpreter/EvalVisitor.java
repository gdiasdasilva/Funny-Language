package semantics.interpreter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import semantics.BoolValue;
import semantics.Env;
import semantics.FunValue;
import semantics.IEnv;
import semantics.IValue;
import semantics.IntValue;
import semantics.RefValue;
import semantics.SemanticException;
import semantics.StringValue;
import semantics.TypeErrorException;
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

public class EvalVisitor implements Visitor<IValue> {
	
	private IEnv env;
	
	public EvalVisitor() {
		this.env = new Env();
	}

	@Override
	public IValue visit(ASTNum num) {
		return num.integer;
	}
	
	@Override
	public IValue visit(ASTBool truth) {
		return truth.bool;
	}
	
	@Override
	public IValue visit(ASTString astString) {
		return astString.string;
	}

	@Override
	public IValue visit(ASTPlus plus) throws SemanticException {
		IValue l = plus.l.accept(this);
		IValue r = plus.r.accept(this);
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
	public IValue visit(ASTSub sub) throws SemanticException {
		IValue l = sub.l.accept(this);
		IValue r = sub.r.accept(this);		
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new IntValue(vl.getVal() - vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to subtract non-integer values.");
	}

	@Override
	public IValue visit(ASTMul mul) throws SemanticException {
		IValue l = mul.l.accept(this);
		IValue r = mul.r.accept(this);
		
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new IntValue(vl.getVal() * vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to multiply non-integer values.");
	}

	@Override
	public IValue visit(ASTDiv div) throws SemanticException {
		IValue l = div.l.accept(this);
		IValue r = div.r.accept(this);
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new IntValue(vl.getVal() / vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to divide non-integer values.");	
	}

	@Override
	public IValue visit(ASTUnMinus um) throws SemanticException {
		IntValue v = (IntValue) um.v.accept(this);
		if(v.typeOf() == IValue.VType.INTEGER)
		{
			IntValue vv = (IntValue) v;
			return new IntValue(-vv.getVal());	
		}
		else
			throw new TypeErrorException("Trying to use unary minus with non-integer value.");
	}
	
	@Override
	public IValue visit(ASTEq eq) throws SemanticException {
		IValue lval = eq.l.accept(this);
		IValue rval = eq.r.accept(this);
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
	public IValue visit(ASTAnd and) throws SemanticException{
		IValue l = and.l.accept(this);
		IValue r = and.r.accept(this);		
		if (l.typeOf() == IValue.VType.BOOLEAN && r.typeOf() == IValue.VType.BOOLEAN) {
			BoolValue vl = (BoolValue) l;
			BoolValue vr = (BoolValue) r;
			return new BoolValue(vl.getVal() && vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to AND non-boolean values.");
	}

	@Override
	public IValue visit(ASTOr or) throws SemanticException {
		IValue l = or.l.accept(this);
		IValue r = or.r.accept(this);
		if (l.typeOf() == IValue.VType.BOOLEAN && r.typeOf() == IValue.VType.BOOLEAN) {
			BoolValue vl = (BoolValue) l;
			BoolValue vr = (BoolValue) r;
			return new BoolValue(vl.getVal() || vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to OR non-boolean values.");
	}

	@Override
	public IValue visit(ASTNeq neq) throws SemanticException {
		IValue lval = neq.l.accept(this);
		IValue rval = neq.r.accept(this);
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
	public IValue visit(ASTLseq lseq) throws SemanticException {
		IValue l = lseq.l.accept(this);
		IValue r = lseq.r.accept(this);
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new BoolValue(vl.getVal() <= vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to LSorEQthan non-integer values.");		
	}

	@Override
	public IValue visit(ASTGreq greq) throws SemanticException{
		IValue l = greq.l.accept(this);
		IValue r = greq.r.accept(this);
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new BoolValue(vl.getVal() >= vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to GRorEQthan non-integer values.");
	}

	@Override
	public IValue visit(ASTLs ls) throws SemanticException {
		IValue l = ls.l.accept(this);
		IValue r = ls.r.accept(this);
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new BoolValue(vl.getVal() < vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to LSthan non-integer values.");
	}

	@Override
	public IValue visit(ASTGr gr) throws SemanticException{
		IValue l = gr.l.accept(this);
		IValue r = gr.r.accept(this);
		if (l.typeOf() == IValue.VType.INTEGER && r.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new BoolValue(vl.getVal() > vr.getVal());
		}
		else
			throw new TypeErrorException("Trying to GRthan non-integer values.");
	}

	@Override
	public IValue visit(ASTCond cond) throws SemanticException{
		IValue c = cond.condNode.accept(this);
		if(c.typeOf() == IValue.VType.BOOLEAN)
		{
			BoolValue vc = (BoolValue) c;
			IValue thenV = cond.thenNode.accept(this);
			IValue elseV = cond.elseNode.accept(this);
			return (vc.getVal() ? thenV : elseV);
		}
		else
			throw new TypeErrorException("If's condition must be a boolean expression.");
	}

	@Override
	public IValue visit(ASTNot n) throws SemanticException {
		IValue v = n.v.accept(this);
		if(v.typeOf() == IValue.VType.BOOLEAN)
		{
			BoolValue vv = (BoolValue) v;
			return new BoolValue(!vv.getVal());
		}
		else
			throw new TypeErrorException("Trying to NOT a non-boolean value.");
	}
	
	@Override
	public IValue visit(ASTId id) throws SemanticException {
		return env.find(id.id);
	}

	@Override
	public IValue visit(ASTDecl decl) throws SemanticException
	{
		env.beginScope();
		
		for (int i = 0; i < decl.ids.size( ); i++)
		{
			env.assoc(decl.ids.get(i), decl.defs.get(i).accept(this));
		}
		
		IValue v = decl.body.accept(this);
		
		env.endScope();
		return v;
	}

	@Override
	public IValue visit(ASTAssign astAssign) throws SemanticException {
		IValue lval = astAssign.l.accept(this);
		IValue rval = astAssign.r.accept(this);
		if (lval.typeOf() == IValue.VType.REFERENCE) {
			RefValue lref = (RefValue) lval;
			lref.setVal(rval);
			return rval;
		} else
			throw new TypeErrorException("Assign's L-hand-val must be a reference");
	}

	@Override
	public IValue visit(ASTDeref astDeref) throws SemanticException {
		IValue v = astDeref.node.accept(this);
		if (v.typeOf() == IValue.VType.REFERENCE) {
			return ((RefValue) v).getVal();
		}
		else
			throw new TypeErrorException("Can only deref a REFERENCE.");
	}
	
	@Override
	public IValue visit(ASTWhile astWhile) throws SemanticException {
		IValue c = astWhile.c.accept(this);
		if (c.typeOf() == IValue.VType.BOOLEAN) {
			BoolValue boolc = (BoolValue) c;
			while (boolc.getVal()) {
				astWhile.b.accept(this);
				c = astWhile.c.accept(this);
				/* the following test is needed to make sure that no Java cast
				 * exception is risen due to a condition initially based in
				 * deref'ing a (mutable) variable containing a boolean value
				 * being replaced afterwards inside the loop by a non-boolean */
				if (c.typeOf() == IValue.VType.BOOLEAN)
					boolc = (BoolValue) astWhile.c.accept(this);
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
	public IValue visit(ASTNew astNew) throws SemanticException {
		IValue v0 = astNew.node.accept(this);
		RefValue ref = new RefValue();
		ref.setVal(v0);
		return ref;
	}
	
	@Override
	public IValue visit(ASTPrint astPrint) throws SemanticException {
		IValue val = astPrint.node.accept(this);
		System.out.print(val);
		return val;
	}

	@Override
	public IValue visit(ASTPrintln astPrintln) throws SemanticException {
		IValue val = astPrintln.node.accept(this);
		System.out.println(val);
		return val;
	}

	@Override
	public IValue visit(ASTSeq astSeq) throws SemanticException {
		astSeq.f.accept(this);
		return astSeq.s.accept(this);
	}

	@Override
	public IValue visit(ASTCall astCall) throws SemanticException {
		IValue vfun = astCall.fun.accept(this);
//		IValue varg = astCall.arg.accept(this);
		List<IValue> vargs = new ArrayList<IValue>();
		for (ASTNode arg : astCall.args)
			vargs.add(arg.accept(this));
		
		FunValue vf;
		
		if (vfun.typeOf() == IValue.VType.FUNCTION)
		{
			vf = (FunValue) vfun;
		}
		else
			throw new TypeErrorException("Wrong type in function.");
		
		env.beginScope();
//		env.assoc(vf.getParameter(), varg);
		Iterator<String> pit = vf.getParameters().iterator();
		Iterator<IValue> vit = vargs.iterator();
		
		while (pit.hasNext())
			while (vit.hasNext())
				env.assoc(pit.next(), vit.next());
		
		IValue result = vf.getBody().accept(this);
		env.endScope();
		return result;
	}

	@Override
	public IValue visit(ASTFun astFun) throws SemanticException {
		IValue v = new FunValue(astFun.body, astFun.params);
		return v;
	}
}
