package semantics.interpreter;

import semantics.BValue;
import semantics.IValue;
import semantics.Value;
import semantics.Visitor;
import ast.ASTAnd;
import ast.ASTCond;
import ast.ASTDecl;
import ast.ASTDiv;
import ast.ASTEq;
import ast.ASTGr;
import ast.ASTGreq;
import ast.ASTId;
import ast.ASTLs;
import ast.ASTLseq;
import ast.ASTMul;
import ast.ASTNeq;
import ast.ASTNot;
import ast.ASTNum;
import ast.ASTOr;
import ast.ASTPlus;
import ast.ASTSub;
import ast.ASTTruth;
import ast.ASTUnMinus;

@SuppressWarnings("rawtypes")
public class EvalVisitor implements Visitor<Value> {

	@Override
	public Value<Integer> visit(ASTNum num) {
		return num.iVal;
	}
	
	@Override
	public Value<Boolean> visit(ASTTruth truth) {
		return truth.tVal;
	}

	@Override
	public Value<Integer> visit(ASTPlus plus) {
		IValue l = (IValue) plus.l.accept(this);
		IValue r = (IValue) plus.r.accept(this);
		return new IValue(l.i + r.i);
	}

	@Override
	public Value<Integer> visit(ASTSub sub) {
		IValue l = (IValue) sub.l.accept(this);
		IValue r = (IValue) sub.r.accept(this);
		return new IValue(l.i - r.i);
	}

	@Override
	public Value<Integer> visit(ASTMul mul) {
		IValue l = (IValue) mul.l.accept(this);
		IValue r = (IValue) mul.r.accept(this);
		return new IValue(l.i * r.i);
	}

	@Override
	public Value<Integer> visit(ASTDiv div) {
		IValue l = (IValue) div.l.accept(this);
		IValue r = (IValue) div.r.accept(this);
		return new IValue(l.i / r.i);
	}

	@Override
	public Value<Integer> visit(ASTUnMinus um) {
		IValue v = (IValue) um.v.accept(this);
		return new IValue(-v.i);
	}
	
	@Override
	public Value<Boolean> visit(ASTEq eq) {
		IValue l = (IValue) eq.l.accept(this);
		IValue r = (IValue) eq.r.accept(this);
		return new BValue(l.i == r.i);
	}

	@Override
	public Value<Boolean> visit(ASTAnd and) {
		BValue l = (BValue) and.l.accept(this);
		BValue r = (BValue) and.r.accept(this);
		return new BValue(l.b && r.b);
	}

	@Override
	public Value<Boolean> visit(ASTOr or) {
		BValue l = (BValue) or.l.accept(this);
		BValue r = (BValue) or.r.accept(this);
		return new BValue(l.b || r.b);
	}

	@Override
	public Value<Boolean> visit(ASTNeq neq) {
		IValue l = (IValue) neq.l.accept(this);
		IValue r = (IValue) neq.r.accept(this);
		return new BValue(l.i != r.i);
	}

	@Override
	public Value<Boolean> visit(ASTLseq lseq) {
		IValue l = (IValue) lseq.l.accept(this);
		IValue r = (IValue) lseq.r.accept(this);
		return new BValue(l.i <= r.i);
	}

	@Override
	public Value<Boolean> visit(ASTGreq greq) {
		IValue l = (IValue) greq.l.accept(this);
		IValue r = (IValue) greq.r.accept(this);
		return new BValue(l.i >= r.i);
	}

	@Override
	public Value<Boolean> visit(ASTLs ls) {
		IValue l = (IValue) ls.l.accept(this);
		IValue r = (IValue) ls.r.accept(this);
		return new BValue(l.i < r.i);
	}

	@Override
	public Value<Boolean> visit(ASTGr gr) {
		IValue l = (IValue) gr.l.accept(this);
		IValue r = (IValue) gr.r.accept(this);
		return new BValue(l.i > r.i);
	}

	@Override
	public Value visit(ASTCond cond) {
		BValue c = (BValue) cond.condNode.accept(this);
		Value thenV = cond.thenNode.accept(this);
		Value elseV = cond.elseNode.accept(this);
		return (c.b ? thenV : elseV);
	}

	@Override
	public Value visit(ASTNot n) {
		BValue v = (BValue) n.v.accept(this);
		return new BValue(!v.b);
	}
	
	@Override
	public Value visit(ASTId id) {
		return id.accept(this);
	}

	@Override
	public Value visit(ASTDecl decl) {
		Value v = decl.def.accept(this); //TODO
		v = decl.body.accept(this);
		return v;
	}
}
