package semantics.interpreter;

import semantics.*;
import ast.ASTAnd;
import ast.ASTAssign;
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
import ast.ASTSub;
import ast.ASTTruth;
import ast.ASTUnMinus;
import ast.ASTWhile;

public class EvalVisitor implements Visitor<IValue> {
	
	private IEnv env;
	
	public EvalVisitor(IEnv env) {
		this.env = env;
	}

	@Override
	public IValue visit(ASTNum num) {
		return num.iVal;
	}
	
	@Override
	public IValue visit(ASTTruth truth) {
		return truth.tVal;
	}

	@Override
	public IValue visit(ASTPlus plus) throws Exception {
		IntValue l = (IntValue) plus.l.accept(this);
		IntValue r = (IntValue) plus.r.accept(this);
		return new IntValue(l.getVal() + r.getVal());
	}

	@Override
	public IValue visit(ASTSub sub) throws Exception {
		IntValue l = (IntValue) sub.l.accept(this);
		IntValue r = (IntValue) sub.r.accept(this);
		return new IntValue(l.getVal() - r.getVal());
	}

	@Override
	public IValue visit(ASTMul mul) throws Exception {
		IntValue l = (IntValue) mul.l.accept(this);
		IntValue r = (IntValue) mul.r.accept(this);
		return new IntValue(l.getVal() * r.getVal());
	}

	@Override
	public IValue visit(ASTDiv div) throws Exception {
		IntValue l = (IntValue) div.l.accept(this);
		IntValue r = (IntValue) div.r.accept(this);
		return new IntValue(l.getVal() / r.getVal());
	}

	@Override
	public IValue visit(ASTUnMinus um) throws Exception {
		IntValue v = (IntValue) um.v.accept(this);
		return new IntValue(-v.getVal());
	}
	
	@Override
	public IValue visit(ASTEq eq) throws Exception {
		BoolValue l = (BoolValue) eq.l.accept(this);
		BoolValue r = (BoolValue) eq.r.accept(this);
		return new BoolValue(l.getVal() == r.getVal());
	}

	@Override
	public IValue visit(ASTAnd and) throws Exception{
		BoolValue l = (BoolValue) and.l.accept(this);
		BoolValue r = (BoolValue) and.r.accept(this);
		return new BoolValue(l.getVal() && r.getVal());
	}

	@Override
	public IValue visit(ASTOr or) throws Exception {
		BoolValue l = (BoolValue) or.l.accept(this);
		BoolValue r = (BoolValue) or.r.accept(this);
		return new BoolValue(l.getVal() || r.getVal());
	}

	@Override
	public IValue visit(ASTNeq neq) throws Exception {
		BoolValue l = (BoolValue) neq.l.accept(this);
		BoolValue r = (BoolValue) neq.r.accept(this);
		return new BoolValue(l.getVal() != r.getVal());
	}

	@Override
	public IValue visit(ASTLseq lseq) throws Exception {
		IntValue l = (IntValue) lseq.l.accept(this);
		IntValue r = (IntValue) lseq.r.accept(this);
		return new BoolValue(l.getVal() <= r.getVal());
	}

	@Override
	public IValue visit(ASTGreq greq) throws Exception{
		IntValue l = (IntValue) greq.l.accept(this);
		IntValue r = (IntValue) greq.r.accept(this);
		return new BoolValue(l.getVal() >= r.getVal());
	}

	@Override
	public IValue visit(ASTLs ls) throws Exception {
		IntValue l = (IntValue) ls.l.accept(this);
		IntValue r = (IntValue) ls.r.accept(this);
		return new BoolValue(l.getVal() < r.getVal());
	}

	@Override
	public IValue visit(ASTGr gr) throws Exception{
		IntValue l = (IntValue) gr.l.accept(this);
		IntValue r = (IntValue) gr.r.accept(this);
		return new BoolValue(l.getVal() > r.getVal());
	}

	@Override
	public IValue visit(ASTCond cond) throws Exception{
		BoolValue c = (BoolValue) cond.condNode.accept(this);
		IValue thenV = cond.thenNode.accept(this);
		IValue elseV = cond.elseNode.accept(this);
		return (c.getVal() ? thenV : elseV);
	}

	@Override
	public IValue visit(ASTNot n) throws Exception {
		BoolValue v = (BoolValue) n.v.accept(this);
		return new BoolValue(!v.getVal());
	}
	
	@Override
	public IValue visit(ASTId id) throws Exception {
		return env.find(id.id);
	}

	@Override
	public IValue visit(ASTDecl decl) throws Exception
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
	public IValue visit(ASTAssign astAssign) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IValue visit(ASTWhile astWhile) throws Exception {
		IValue v1 = astWhile.l.accept(this);
		boolean cond = ((BoolValue) v1).getVal();
		
		if (v1.typeOf() != IValue.VType.BOOLEAN)
			throw new Exception("Error: Type of parameter is not BOOLEAN.");
		
		while(cond)
		{
			astWhile.r.accept(this);
		}
		
		return new BoolValue(true);
	}

	@Override
	public IValue visit(ASTNew astNew) throws Exception {
		IValue v1 = astNew.node.accept(this);
		RefValue ref = new RefValue();
		ref.setVal(v1);
		

		return ref;
	}

	@Override
	public IValue visit(ASTDeref astDeref) throws Exception {
		IValue v = astDeref.node.accept(this);
		
		if(v.typeOf() == IValue.VType.VREFERENCE)
			return ((RefValue) v).getVal();
		else
			throw new Exception("Error: Type of parameter is not VREFERENCE.");
	}
}
