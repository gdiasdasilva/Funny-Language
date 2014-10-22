package semantics.interpreter;

import semantics.*;
import semantics.Value;
import ast.ASTAnd;
import ast.ASTAssign;
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
import ast.ASTWhile;

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
	public Value<Integer> visit(ASTPlus plus) throws Exception {
		Value<Integer> l = plus.l.accept(this);
		Value<Integer> r = plus.r.accept(this);
		return new Value<Integer>(l.value + r.value);
	}

	@Override
	public Value<Integer> visit(ASTSub sub) throws Exception {
		Value<Integer> l = sub.l.accept(this);
		Value<Integer> r = sub.r.accept(this);
		return new Value<Integer>(l.value - r.value);
	}

	@Override
	public Value<Integer> visit(ASTMul mul) throws Exception {
		Value<Integer> l = mul.l.accept(this);
		Value<Integer> r = mul.r.accept(this);
		return new Value<Integer>(l.value * r.value);
	}

	@Override
	public Value<Integer> visit(ASTDiv div) throws Exception {
		Value<Integer> l = div.l.accept(this);
		Value<Integer> r = div.r.accept(this);
		return new Value<Integer>(l.value / r.value);
	}

	@Override
	public Value<Integer> visit(ASTUnMinus um) throws Exception {
		Value<Integer> v = (Value<Integer>) um.v.accept(this);
		return new Value<Integer>(-v.value);
	}
	
	@Override
	public Value<Boolean> visit(ASTEq eq) throws Exception {
		Value<Integer> l = (Value<Integer>) eq.l.accept(this);
		Value<Integer> r = (Value<Integer>) eq.r.accept(this);
		return new Value<Boolean>(l.value == r.value);
	}

	@Override
	public Value<Boolean> visit(ASTAnd and) throws Exception{
		Value<Boolean> l =  and.l.accept(this);
		Value<Boolean> r =  and.r.accept(this);
		return new Value<Boolean>(l.value && r.value);
	}

	@Override
	public Value<Boolean> visit(ASTOr or) throws Exception {
		Value<Boolean> l =  or.l.accept(this);
		Value<Boolean> r =  or.r.accept(this);
		return new Value<Boolean>(l.value || r.value);
	}

	@Override
	public Value<Boolean> visit(ASTNeq neq) throws Exception {
		Value<Integer> l = (Value<Integer>) neq.l.accept(this);
		Value<Integer> r = (Value<Integer>) neq.r.accept(this);
		return new Value<Boolean>(l.value != r.value);
	}

	@Override
	public Value<Boolean> visit(ASTLseq lseq) throws Exception {
		Value<Integer> l = (Value<Integer>) lseq.l.accept(this);
		Value<Integer> r = (Value<Integer>) lseq.r.accept(this);
		return new Value<Boolean>(l.value <= r.value);
	}

	@Override
	public Value<Boolean> visit(ASTGreq greq) throws Exception{
		Value<Integer> l = (Value<Integer>) greq.l.accept(this);
		Value<Integer> r = (Value<Integer>) greq.r.accept(this);
		return new Value<Boolean>(l.value >= r.value);
	}

	@Override
	public Value<Boolean> visit(ASTLs ls) throws Exception {
		Value<Integer> l = (Value<Integer>) ls.l.accept(this);
		Value<Integer> r = (Value<Integer>) ls.r.accept(this);
		return new Value<Boolean>(l.value < r.value);
	}

	@Override
	public Value<Boolean> visit(ASTGr gr) throws Exception{
		Value<Integer> l = (Value<Integer>) gr.l.accept(this);
		Value<Integer> r = (Value<Integer>) gr.r.accept(this);
		return new Value<Boolean>(l.value > r.value);
	}

	@Override
	public Value visit(ASTCond cond) throws Exception{
		Value<Boolean> c =  cond.condNode.accept(this);
		Value thenV = cond.thenNode.accept(this);
		Value elseV = cond.elseNode.accept(this);
		return (c.value ? thenV : elseV);
	}

	@Override
	public Value visit(ASTNot n) throws Exception {
		Value<Boolean> v =  n.v.accept(this);
		return new Value<Boolean>(!v.value);
	}
	
	@Override
	public Value visit(ASTId id) throws Exception {
		return env.find(id.id);
	}

	@Override
	public Value visit(ASTDecl decl) throws Exception
	{
		env.beginScope();
		
		for (int i = 0; i < decl.ids.size( ); i++)
		{
			env.assoc(decl.ids.get(i), decl.defs.get(i).accept(this));
		}
		
		Value v = decl.body.accept(this);
		
		env.endScope();
		return v;
	}

	@Override
	public Value visit(ASTAssign astAssign) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(ASTWhile astWhile) throws Exception {
		while((astWhile.l.accept(this).toBoolean()))
		{
			astWhile.r.accept(this);
		}	
		return new Value(true);
	}
}
