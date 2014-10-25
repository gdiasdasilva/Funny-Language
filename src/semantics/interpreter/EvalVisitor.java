package semantics.interpreter;

import semantics.BoolValue;
import semantics.Env;
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
import ast.ASTCond;
import ast.ASTDecl;
import ast.ASTDeref;
import ast.ASTDiv;
import ast.ASTEq;
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

public class EvalVisitor implements Visitor<IValue> {
	
	private IEnv env;
	
	public EvalVisitor(IEnv env) {
//		this.env = env;
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
		if (l.typeOf() == IValue.VType.INTEGER && l.typeOf() == IValue.VType.INTEGER) {
			IntValue vl = (IntValue) l;
			IntValue vr = (IntValue) r;
			return new IntValue(vl.getVal() + vr.getVal());
		} else
			throw new TypeErrorException("Trying to add non numerical values");
	}

	@Override
	public IValue visit(ASTSub sub) throws SemanticException {
		IntValue l = (IntValue) sub.l.accept(this);
		IntValue r = (IntValue) sub.r.accept(this);
		return new IntValue(l.getVal() - r.getVal());
	}

	@Override
	public IValue visit(ASTMul mul) throws SemanticException {
		IntValue l = (IntValue) mul.l.accept(this);
		IntValue r = (IntValue) mul.r.accept(this);
		return new IntValue(l.getVal() * r.getVal());
	}

	@Override
	public IValue visit(ASTDiv div) throws SemanticException {
		IntValue l = (IntValue) div.l.accept(this);
		IntValue r = (IntValue) div.r.accept(this);
		return new IntValue(l.getVal() / r.getVal());
	}

	@Override
	public IValue visit(ASTUnMinus um) throws SemanticException {
		IntValue v = (IntValue) um.v.accept(this);
		return new IntValue(-v.getVal());
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
		BoolValue l = (BoolValue) and.l.accept(this);
		BoolValue r = (BoolValue) and.r.accept(this);
		return new BoolValue(l.getVal() && r.getVal());
	}

	@Override
	public IValue visit(ASTOr or) throws SemanticException {
		BoolValue l = (BoolValue) or.l.accept(this);
		BoolValue r = (BoolValue) or.r.accept(this);
		return new BoolValue(l.getVal() || r.getVal());
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
		IntValue l = (IntValue) lseq.l.accept(this);
		IntValue r = (IntValue) lseq.r.accept(this);
		return new BoolValue(l.getVal() <= r.getVal());
	}

	@Override
	public IValue visit(ASTGreq greq) throws SemanticException{
		IntValue l = (IntValue) greq.l.accept(this);
		IntValue r = (IntValue) greq.r.accept(this);
		return new BoolValue(l.getVal() >= r.getVal());
	}

	@Override
	public IValue visit(ASTLs ls) throws SemanticException {
		IntValue l = (IntValue) ls.l.accept(this);
		IntValue r = (IntValue) ls.r.accept(this);
		return new BoolValue(l.getVal() < r.getVal());
	}

	@Override
	public IValue visit(ASTGr gr) throws SemanticException{
		IntValue l = (IntValue) gr.l.accept(this);
		IntValue r = (IntValue) gr.r.accept(this);
		return new BoolValue(l.getVal() > r.getVal());
	}

	@Override
	public IValue visit(ASTCond cond) throws SemanticException{
		BoolValue c = (BoolValue) cond.condNode.accept(this);
		IValue thenV = cond.thenNode.accept(this);
		IValue elseV = cond.elseNode.accept(this);
		return (c.getVal() ? thenV : elseV);
	}

	@Override
	public IValue visit(ASTNot n) throws SemanticException {
		BoolValue v = (BoolValue) n.v.accept(this);
		return new BoolValue(!v.getVal());
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
							"while's condition must be a boolean expression");
			}
			return new BoolValue(true);
		}
		else
			throw new TypeErrorException("while's condition must be a boolean expression");
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
		IValue val = astPrint.accept(this);
		System.out.print(val);
		return val;
	}

	@Override
	public IValue visit(ASTPrintln astPrintln) throws SemanticException {
		IValue val = astPrintln.accept(this);
		System.out.println(val);
		return val;
	}

	@Override
	public IValue visit(ASTSeq astSeq) throws SemanticException {
		astSeq.f.accept(this);
		return astSeq.s.accept(this);
	}
}
