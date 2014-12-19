package semantics.compiler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;
import semantics.typeSystem.Type;
import ast.ASTAnd;
import ast.ASTAssign;
import ast.ASTBool;
import ast.ASTCall;
import ast.ASTCond;
import ast.ASTDecl;
import ast.ASTDeref;
import ast.ASTDiv;
import ast.ASTEq;
import ast.ASTField;
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
import ast.ASTRecord;
import ast.ASTSeq;
import ast.ASTString;
import ast.ASTSub;
import ast.ASTUnMinus;
import ast.ASTWhile;

public class CompilerVisitor implements Visitor<CodeBlock, Id> {
	
	static final int FALSE = 0;
	static final int TRUE = 1;
	
	private int label;
	private int refCounter;
	
	public CompilerVisitor() {
		label = 1;
		refCounter = 0;
	}
	
	@Override
	public CodeBlock visit(ASTNum num, Environment<Id> e) {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(num.integer);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTBool truth, Environment<Id> e) {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(truth.bool ? TRUE : FALSE);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTPlus plus, Environment<Id> e) throws SemanticException {
		CodeBlock cb = plus.l.accept(this, e);
		cb.append(plus.r .accept(this, e));
		cb.insertOp(Op.ADD);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTSub sub, Environment<Id> e) throws SemanticException {
		CodeBlock cb = sub.l.accept(this, e);
		cb.append(sub.r.accept(this, e));
		cb.insertOp(Op.SUB);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTMul mul, Environment<Id> e) throws SemanticException {
		CodeBlock cb = mul.l.accept(this, e);
		cb.append(mul.r.accept(this, e));
		cb.insertOp(Op.MUL);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTDiv div, Environment<Id> e) throws SemanticException {
		CodeBlock cb = div.l.accept(this, e);
		cb.append(div.r.accept(this, e));
		cb.insertOp(Op.DIV);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTUnMinus um, Environment<Id> e) throws SemanticException {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(0);
		cb.append(um.v.accept(this, e));
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
	public CodeBlock visit(ASTEq eq, Environment<Id> e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = eq.l.accept(this, e);
		this.label++;
		cb.append(eq.r.accept(this, e));
		cb.insertCondBranching(Cond.EQ, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTNeq neq, Environment<Id> e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = neq.l.accept(this, e);
		this.label++;
		cb.append(neq.r.accept(this, e));
		cb.insertCondBranching(Cond.NEQ, label);
		return this.addBI(cb, label);
	}
	
	@Override
	public CodeBlock visit(ASTCond cond, Environment<Id> e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = cond.condNode.accept(this, e);
		cb.insertIntArgument(FALSE);
		cb.insertCondBranching(Cond.NEQ, label);
		this.label++;
		cb.append(cond.elseNode.accept(this, e));
		cb.insertGotoContinue(label);
		cb.insertThenLabel(label);
		this.label++;
		cb.append(cond.thenNode.accept(this, e));
		cb.insertContinueLabel(label);
		this.label++;
		return cb;
	}

	@Override
	public CodeBlock visit(ASTLs ls, Environment<Id> e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = ls.l.accept(this, e);
		this.label++;
		cb.append(ls.r.accept(this, e));
		cb.insertCondBranching(Cond.LS, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTGr gr, Environment<Id> e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = gr.l.accept(this, e);
		this.label++;
		cb.append(gr.r.accept(this, e));
		cb.insertCondBranching(Cond.GR, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTLseq lseq, Environment<Id> e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = lseq.l.accept(this, e);
		this.label++;
		cb.append(lseq.r.accept(this, e));
		cb.insertCondBranching(Cond.LSEQ, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTGreq greq, Environment<Id> e) throws SemanticException {
		int label = this.label++;
		CodeBlock cb = greq.l.accept(this, e);
		this.label++;
		cb.append(greq.r.accept(this, e));
		cb.insertCondBranching(Cond.GREQ, label);
		return this.addBI(cb, label);
	}

	@Override
	public CodeBlock visit(ASTAnd and, Environment<Id> e) throws SemanticException {
		CodeBlock cb = and.l.accept(this, e);
		cb.append(and.r .accept(this, e));
		cb.insertOp(Op.AND);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTOr or, Environment<Id> e) throws SemanticException {
		CodeBlock cb = or.l.accept(this, e);
		cb.append(or.r .accept(this, e));
		cb.insertOp(Op.OR);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTNot n, Environment<Id> e) throws SemanticException {
		CodeBlock cb = new CodeBlock();
		cb.insertIntArgument(1);
		cb.append(n.v.accept(this, e));
		cb.insertOp(Op.SUB);
		return cb;
	}

	@Override
	public CodeBlock visit(ASTId id, Environment<Id> e) throws SemanticException {
		CodeBlock cb = new CodeBlock();
		CompilerEnvironment ce = (CompilerEnvironment) e;
		cb.insertLoadSL();
		Id iid = ce.find(id.id);
		for (int i = ce.level; i > iid.level; i--) {
			cb.insertGetAncestorFrame(ce.getId(), ce.getUpperId());
			ce = ce.upper;
		}
		cb.insertGetFieldValue(ce.getId(), id.id, getStringForType(id.getIdType()));
		return cb;
	}
	
	private String getStringForType(Type t) {
		switch (t.getType()) {
		case INTEGER:
		case BOOLEAN:
			return "I";
		case REFERENCE:
			return "Ljava/lang/Object;";
		default:
			return null;
		}
	}

	@Override
	public CodeBlock visit(ASTDecl decl, Environment<Id> e) throws SemanticException {
		CodeBlock cb = new CodeBlock();
		Iterator<ASTNode> defsIt = decl.defs.iterator();
		Iterator<String> idsIt = decl.ids.iterator();
		Iterator<Type> typesIt = decl.getIdTypesIterator();
		List<Id> ids = new LinkedList<Id>();
		CompilerEnvironment ce = (CompilerEnvironment) e.beginScope();
		cb.insertFrame(new FrameClass(ce.getId(), ce.getUpperId(), ids));
		int l = ce.level;
		while (defsIt.hasNext()) {
			String name = idsIt.next();
			Id id = new Id(name, getStringForType(typesIt.next()), l);
			ce.assoc(name, id);
			ids.add(id);
			cb.insertLoadSL();
			cb.append(defsIt.next().accept(this, ce));
			cb.putFieldId(ce.getId(), name, id.type);
		}
		cb.append(decl.body.accept(this, ce));
		if (l > 0) {
			cb.insertLoadSL();
			cb.insertGetAncestorFrame(ce.getId(), ce.getUpperId());
			cb.insertStoreSL();
		}
		ce.endScope();
		return cb;
	}

	@Override
	public CodeBlock visit(ASTAssign astAssign, Environment<Id> e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeBlock visit(ASTWhile astWhile, Environment<Id> e) throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int getRefNumber() {
		return refCounter++;
	}

	@Override
	public CodeBlock visit(ASTNew astNew, Environment<Id> e) throws SemanticException {
		CodeBlock cb = new CodeBlock();
		String refId = "ref_" + getRefNumber();
		switch (astNew.getType().getType()) {
		case INTEGER:
		case BOOLEAN:
			cb.insertIntRef(new RefToIntClass(refId));
			cb.append(astNew.node.accept(this, e));
			cb.putFieldId(refId, "v", "I");
			break;
		default:
			break;
		}
		return cb;
	}

	@Override
	public CodeBlock visit(ASTDeref astDeref, Environment<Id> e) throws SemanticException {
		CodeBlock cb = new CodeBlock();
		return cb;
	}

	@Override
	public CodeBlock visit(ASTPrint astPrint, Environment<Id> e) throws SemanticException {
		// TODO Auto-generated method stub
		CodeBlock cb = new CodeBlock();
		return cb;
	}

	@Override
	public CodeBlock visit(ASTPrintln astPrintln, Environment<Id> e) throws SemanticException {
		// TODO Auto-generated method stub
		CodeBlock cb = new CodeBlock();
		return cb;
	}

	@Override
	public CodeBlock visit(ASTString astString, Environment<Id> e) {
		// TODO Auto-generated method stub
		CodeBlock cb = new CodeBlock();
		return cb;
	}

	@Override
	public CodeBlock visit(ASTSeq astSeq, Environment<Id> e) throws SemanticException {
		// TODO Auto-generated method stub
		CodeBlock cb = new CodeBlock();
		return cb;
	}

	@Override
	public CodeBlock visit(ASTCall astCall, Environment<Id> e) throws SemanticException {
		// TODO Auto-generated method stub
		CodeBlock cb = new CodeBlock();
		return cb;
	}

	@Override
	public CodeBlock visit(ASTFun astFun, Environment<Id> e) throws SemanticException {
		// TODO Auto-generated method stub
		CodeBlock cb = new CodeBlock();
		return cb;
	}

	@Override
	public CodeBlock visit(ASTIf astIf, Environment<Id> e) throws SemanticException {
		// TODO Auto-generated method stub
		CodeBlock cb = new CodeBlock();
		return cb;
	}

	@Override
	public CodeBlock visit(ASTField astField, Environment<Id> e)
			throws SemanticException {
		// TODO Auto-generated method stub
		CodeBlock cb = new CodeBlock();
		return cb;
	}

	@Override
	public CodeBlock visit(ASTRecord astRecord, Environment<Id> e)
			throws SemanticException {
		// TODO Auto-generated method stub
		CodeBlock cb = new CodeBlock();
		return cb;
	}
}
