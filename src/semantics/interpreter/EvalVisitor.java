package semantics.interpreter;

import semantics.*;
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

@SuppressWarnings({"rawtypes", "unchecked"})
public class EvalVisitor implements Visitor<Value> {
	
	private IEnv env;
	
	public EvalVisitor(IEnv env) {
		this.env = env;
	}

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
		Value<Integer> l = plus.l.accept(this);
		Value<Integer> r = plus.r.accept(this);
		return new Value<Integer>(l.value + r.value);
	}

	@Override
	public Value<Integer> visit(ASTSub sub) {
		Value<Integer> l = sub.l.accept(this);
		Value<Integer> r = sub.r.accept(this);
		return new Value<Integer>(l.value - r.value);
	}

	@Override
	public Value<Integer> visit(ASTMul mul) {
		Value<Integer> l = mul.l.accept(this);
		Value<Integer> r = mul.r.accept(this);
		return new Value<Integer>(l.value * r.value);
	}

	@Override
	public Value<Integer> visit(ASTDiv div) {
		Value<Integer> l = div.l.accept(this);
		Value<Integer> r = div.r.accept(this);
		return new Value<Integer>(l.value / r.value);
	}

	@Override
	public Value<Integer> visit(ASTUnMinus um) {
		Value<Integer> v = (Value<Integer>) um.v.accept(this);
		return new Value<Integer>(-v.value);
	}
	
	@Override
	public Value<Boolean> visit(ASTEq eq) {
		Value<Integer> l = (Value<Integer>) eq.l.accept(this);
		Value<Integer> r = (Value<Integer>) eq.r.accept(this);
		return new Value<Boolean>(l.value == r.value);
	}

	@Override
	public Value<Boolean> visit(ASTAnd and) {
		Value<Boolean> l =  and.l.accept(this);
		Value<Boolean> r =  and.r.accept(this);
		return new Value<Boolean>(l.value && r.value);
	}

	@Override
	public Value<Boolean> visit(ASTOr or) {
		Value<Boolean> l =  or.l.accept(this);
		Value<Boolean> r =  or.r.accept(this);
		return new Value<Boolean>(l.value || r.value);
	}

	@Override
	public Value<Boolean> visit(ASTNeq neq) {
		Value<Integer> l = (Value<Integer>) neq.l.accept(this);
		Value<Integer> r = (Value<Integer>) neq.r.accept(this);
		return new Value<Boolean>(l.value != r.value);
	}

	@Override
	public Value<Boolean> visit(ASTLseq lseq) {
		Value<Integer> l = (Value<Integer>) lseq.l.accept(this);
		Value<Integer> r = (Value<Integer>) lseq.r.accept(this);
		return new Value<Boolean>(l.value <= r.value);
	}

	@Override
	public Value<Boolean> visit(ASTGreq greq) {
		Value<Integer> l = (Value<Integer>) greq.l.accept(this);
		Value<Integer> r = (Value<Integer>) greq.r.accept(this);
		return new Value<Boolean>(l.value >= r.value);
	}

	@Override
	public Value<Boolean> visit(ASTLs ls) {
		Value<Integer> l = (Value<Integer>) ls.l.accept(this);
		Value<Integer> r = (Value<Integer>) ls.r.accept(this);
		return new Value<Boolean>(l.value < r.value);
	}

	@Override
	public Value<Boolean> visit(ASTGr gr) {
		Value<Integer> l = (Value<Integer>) gr.l.accept(this);
		Value<Integer> r = (Value<Integer>) gr.r.accept(this);
		return new Value<Boolean>(l.value > r.value);
	}

	@Override
	public Value visit(ASTCond cond) {
		Value<Boolean> c =  cond.condNode.accept(this);
		Value thenV = cond.thenNode.accept(this);
		Value elseV = cond.elseNode.accept(this);
		return (c.value ? thenV : elseV);
	}

	@Override
	public Value visit(ASTNot n) {
		Value<Boolean> v =  n.v.accept(this);
		return new Value<Boolean>(!v.value);
	}
	
	@Override
	public Value visit(ASTId id) {
		return env.find(id.id);
	}

	@Override
	public Value visit(ASTDecl decl)
	{
		env.beginScope();
		env.assoc(decl.id, decl.def.accept(this));
		Value v = decl.body.accept(this);
		return v;
	}
}
