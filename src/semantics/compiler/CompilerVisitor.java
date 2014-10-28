package semantics.compiler;

import semantics.*;
import ast.ASTAnd;
import ast.ASTAssign;
import ast.ASTCall;
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
import ast.ASTBool;
import ast.ASTUnMinus;
import ast.ASTWhile;

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
		cb.insertIntArgument(((IntValue) num.integer).getVal());
		return cb;
	}

	@Override
	public CodeBlock visit(ASTBool truth) {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(((BoolValue) truth.bool).getVal() ? TRUE : FALSE);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTPlus plus) throws SemanticException {
		CodeBlock cb = plus.l.accept(this);
		cb.merge(plus.r .accept(this));
		cb.insertOp(Op.ADD);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTSub sub) throws SemanticException {
		CodeBlock cb = sub.l.accept(this);
		cb.merge(sub.r.accept(this));
		cb.insertOp(Op.SUB);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTMul mul) throws SemanticException {
		CodeBlock cb = mul.l.accept(this);
		cb.merge(mul.r.accept(this));
		cb.insertOp(Op.MUL);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTDiv div) throws SemanticException {
		CodeBlock cb = div.l.accept(this);
		cb.merge(div.r.accept(this));
		cb.insertOp(Op.DIV);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTUnMinus um) throws SemanticException {
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
	private CodeBlock addBI(CodeBlock cb, int label)
	{
		cb.insertIntArgument(FALSE);
		cb.insertGotoContinue(label);
		cb.insertThenLabel(label);
		cb.insertIntArgument(TRUE);
		cb.insertContinueLabel(label);
		this.label++;
		return cb;
	}

	@Override
	public CodeBlock visit(ASTEq eq) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = eq.l.accept(this);
		this.label++;
		cb.merge(eq.r.accept(this));
		cb.insertCondBranching(Cond.EQ, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTNeq neq) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = neq.l.accept(this);
		this.label++;
		cb.merge(neq.r.accept(this));
		cb.insertCondBranching(Cond.NEQ, label);
		return this.addBI(cb, label);
	}
	
	@Override
	public CodeBlock visit(ASTCond cond) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = cond.condNode.accept(this);
		cb.insertIntArgument(FALSE);
		cb.insertCondBranching(Cond.NEQ, label);
		this.label++;
		cb.merge(cond.elseNode.accept(this));
		cb.insertGotoContinue(label);
		cb.insertThenLabel(label);
		this.label++;
		cb.merge(cond.thenNode.accept(this));
		cb.insertContinueLabel(label);
		this.label++;
		return cb;
	}

	@Override
	public CodeBlock visit(ASTLs ls) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = ls.l.accept(this);
		this.label++;
		cb.merge(ls.r.accept(this));
		cb.insertCondBranching(Cond.LS, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTGr gr) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = gr.l.accept(this);
		this.label++;
		cb.merge(gr.r.accept(this));
		cb.insertCondBranching(Cond.GR, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTLseq lseq) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = lseq.l.accept(this);
		this.label++;
		cb.merge(lseq.r.accept(this));
		cb.insertCondBranching(Cond.LSEQ, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTGreq greq) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = greq.l.accept(this);
		this.label++;
		cb.merge(greq.r.accept(this));
		cb.insertCondBranching(Cond.GREQ, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTAnd and) throws SemanticException {
		CodeBlock cb = and.l.accept(this);
		cb.merge(and.r .accept(this));
		cb.insertOp(Op.AND);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTOr or) throws SemanticException {
		CodeBlock cb = or.l.accept(this);
		cb.merge(or.r .accept(this));
		cb.insertOp(Op.OR);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTNot n) throws SemanticException {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(1);
		cb.merge(n.v.accept(this));
		cb.insertOp(Op.SUB);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTId id) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTDecl decl) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTAssign astAssign) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTWhile astWhile) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTNew astNew) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTDeref astDeref) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTPrint astPrint) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTPrintln astPrintln) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTString astString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTSeq astSeq) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTCall astCall) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}
}
