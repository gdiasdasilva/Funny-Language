package semantics.compiler;

import ast.*;
import semantics.BValue;
import semantics.IValue;
import semantics.Visitor;

public class CompilerVisitor implements Visitor<CodeBlock> {
	
	static final int FALSE = 0;
	static final int TRUE = 1;
	
	private int label;
	
	public CompilerVisitor() {
		label = 1;
	}

	@Override
	public CodeBlock visit(ASTNum num) {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(((IValue) num.iVal).i);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTTruth truth) {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(((BValue) truth.tVal).b ? TRUE : FALSE);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTPlus plus) {
		CodeBlock cb = plus.l.accept(this);
		cb.merge(plus.r .accept(this));
		cb.insertOp(Op.ADD);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTSub sub) {
		CodeBlock cb = sub.l.accept(this);
		cb.merge(sub.r.accept(this));
		cb.insertOp(Op.SUB);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTMul mul) {
		CodeBlock cb = mul.l.accept(this);
		cb.merge(mul.r.accept(this));
		cb.insertOp(Op.MUL);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTDiv div) {
		CodeBlock cb = div.l.accept(this);
		cb.merge(div.r.accept(this));
		cb.insertOp(Op.DIV);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTUnMinus um) {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(0);
		cb.merge(um.v.accept(this));
		cb.insertOp(Op.SUB);
		return cb;
	}
	
	/**
	 * this method is to factorize code from comparison functions
	 * related to the labels in the branching part
	 * @param cb - the CodeBlock used by caller
	 * @return - the modified CodeBlock
	 */
	private CodeBlock addBI(CodeBlock cb)
	{
		cb.insertIntArgument(FALSE);
		cb.insertGotoContinue(label);
		cb.insertThenLabel(label);
		cb.insertIntArgument(TRUE);
		cb.insertContinueLabel(label);
		label++;
		return cb;
	}

	@Override
	public CodeBlock visit(ASTEq eq) {
		CodeBlock cb = eq.l.accept(this);
		cb.merge(eq.r.accept(this));
		cb.insertCondBranching(Cond.EQ, label);
		return this.addBI(cb);
	}

	@Override
	public CodeBlock visit(ASTNeq neq) {
		CodeBlock cb = neq.l.accept(this);
		cb.merge(neq.r.accept(this));
		cb.insertCondBranching(Cond.NEQ, label);
		return this.addBI(cb);
	}
	
	@Override
	public CodeBlock visit(ASTCond cond) { // TODO malfunctioning
		CodeBlock cb = cond.condNode.accept(this);
		cb.insertIntArgument(FALSE);
		cb.insertCondBranching(Cond.NEQ, label);
		cb.merge(cond.elseNode.accept(this));
		cb.insertGotoContinue(label);
		cb.insertThenLabel(label);
		cb.merge(cond.thenNode.accept(this));
		cb.insertContinueLabel(label);
		label++;
		return cb;
	}

	@Override
	public CodeBlock visit(ASTLs ls) {
		CodeBlock cb = ls.l.accept(this);
		cb.merge(ls.r.accept(this));
		cb.insertCondBranching(Cond.LS, label);
		return this.addBI(cb);
	}

	@Override
	public CodeBlock visit(ASTGr gr) {
		CodeBlock cb = gr.l.accept(this);
		cb.merge(gr.r.accept(this));
		cb.insertCondBranching(Cond.GR, label);
		return this.addBI(cb);
	}

	@Override
	public CodeBlock visit(ASTLseq lseq) {
		CodeBlock cb = lseq.l.accept(this);
		cb.merge(lseq.r.accept(this));
		cb.insertCondBranching(Cond.LSEQ, label);
		return this.addBI(cb);
	}

	@Override
	public CodeBlock visit(ASTGreq greq) {
		CodeBlock cb = greq.l.accept(this);
		cb.merge(greq.r.accept(this));
		cb.insertCondBranching(Cond.GREQ, label);
		return this.addBI(cb);
	}

	@Override
	public CodeBlock visit(ASTAnd and) {
		CodeBlock cb = and.l.accept(this);
		cb.merge(and.r .accept(this));
		cb.insertOp(Op.AND);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTOr or) {
		CodeBlock cb = or.l.accept(this);
		cb.merge(or.r .accept(this));
		cb.insertOp(Op.OR);
		return cb;
	}
}
