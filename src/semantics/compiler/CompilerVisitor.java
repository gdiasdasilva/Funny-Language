package semantics.compiler;

import semantics.*;
import semantics.interpreter.BoolValue;
import semantics.interpreter.IntValue;
import ast.ASTAnd;
import ast.ASTAssign;
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
	public CodeBlock visit(ASTNum num, Environment e) {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(((IntValue) num.integer).getVal());
		return cb;
	}

	@Override
	public CodeBlock visit(ASTBool truth, Environment e) {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(((BoolValue) truth.bool).getVal() ? TRUE : FALSE);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTPlus plus, Environment e) throws SemanticException {
		CodeBlock cb = plus.l.accept(this, e);
		cb.merge(plus.r .accept(this, e));
		cb.insertOp(Op.ADD);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTSub sub, Environment e) throws SemanticException {
		CodeBlock cb = sub.l.accept(this, e);
		cb.merge(sub.r.accept(this, e));
		cb.insertOp(Op.SUB);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTMul mul, Environment e) throws SemanticException {
		CodeBlock cb = mul.l.accept(this, e);
		cb.merge(mul.r.accept(this, e));
		cb.insertOp(Op.MUL);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTDiv div, Environment e) throws SemanticException {
		CodeBlock cb = div.l.accept(this, e);
		cb.merge(div.r.accept(this, e));
		cb.insertOp(Op.DIV);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTUnMinus um, Environment e) throws SemanticException {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(0);
		cb.merge(um.v.accept(this, e));
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
	public CodeBlock visit(ASTEq eq, Environment e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = eq.l.accept(this, e);
		this.label++;
		cb.merge(eq.r.accept(this, e));
		cb.insertCondBranching(Cond.EQ, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTNeq neq, Environment e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = neq.l.accept(this, e);
		this.label++;
		cb.merge(neq.r.accept(this, e));
		cb.insertCondBranching(Cond.NEQ, label);
		return this.addBI(cb, label);
	}
	
	@Override
	public CodeBlock visit(ASTCond cond, Environment e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = cond.condNode.accept(this, e);
		cb.insertIntArgument(FALSE);
		cb.insertCondBranching(Cond.NEQ, label);
		this.label++;
		cb.merge(cond.elseNode.accept(this, e));
		cb.insertGotoContinue(label);
		cb.insertThenLabel(label);
		this.label++;
		cb.merge(cond.thenNode.accept(this, e));
		cb.insertContinueLabel(label);
		this.label++;
		return cb;
	}

	@Override
	public CodeBlock visit(ASTLs ls, Environment e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = ls.l.accept(this, e);
		this.label++;
		cb.merge(ls.r.accept(this, e));
		cb.insertCondBranching(Cond.LS, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTGr gr, Environment e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = gr.l.accept(this, e);
		this.label++;
		cb.merge(gr.r.accept(this, e));
		cb.insertCondBranching(Cond.GR, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTLseq lseq, Environment e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = lseq.l.accept(this, e);
		this.label++;
		cb.merge(lseq.r.accept(this, e));
		cb.insertCondBranching(Cond.LSEQ, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTGreq greq, Environment e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = greq.l.accept(this, e);
		this.label++;
		cb.merge(greq.r.accept(this, e));
		cb.insertCondBranching(Cond.GREQ, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTAnd and, Environment e) throws SemanticException {
		CodeBlock cb = and.l.accept(this, e);
		cb.merge(and.r .accept(this, e));
		cb.insertOp(Op.AND);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTOr or, Environment e) throws SemanticException {
		CodeBlock cb = or.l.accept(this, e);
		cb.merge(or.r .accept(this, e));
		cb.insertOp(Op.OR);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTNot n, Environment e) throws SemanticException {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(1);
		cb.merge(n.v.accept(this, e));
		cb.insertOp(Op.SUB);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTId id, Environment e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTDecl decl, Environment e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTAssign astAssign, Environment e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTWhile astWhile, Environment e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTNew astNew, Environment e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTDeref astDeref, Environment e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTPrint astPrint, Environment e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTPrintln astPrintln, Environment e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTString astString, Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTSeq astSeq, Environment e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTCall astCall, Environment e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTFun astFun, Environment e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTIf astIf, Environment e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}
}
