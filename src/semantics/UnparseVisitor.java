package semantics;

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

public class UnparseVisitor implements Visitor<String> {

	@Override
	public String visit(ASTNum num) {
		return num.iVal.toString();
	}

	@Override
	public String visit(ASTTruth truth) {
		return truth.tVal.toString();
	}

	@Override
	public String visit(ASTPlus plus) {
		return "ASTPlus( " + plus.l.accept(this) + " , " + plus.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTSub sub) {
		return "ASTSub( " + sub.l.accept(this) + " , " + sub.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTMul mul) {
		return "ASTMul( " + mul.l.accept(this) + " , " + mul.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTDiv div) {
		return "ASTPlus( " + div.l.accept(this) + " , " + div.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTUnMinus um) {
		return "ASTUnMinus( " + um.v.accept(this) + " )";
	}

	@Override
	public String visit(ASTEq eq) {
		return "ASTEq( " + eq.l.accept(this) + " , " + eq.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTNeq neq) {
		return "ASTNeq( " + neq.l.accept(this) + " , " + neq.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTLs ls) {
		return "ASTLs( " + ls.l.accept(this) + " , " + ls.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTGr gr) {
		return "ASTGr( " + gr.l.accept(this) + " , " + gr.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTLseq lseq) {
		return "ASTLseq( " + lseq.l.accept(this) + " , " + lseq.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTGreq greq) {
		return "ASTGreq( " + greq.l.accept(this) + " , " + greq.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTAnd and) {
		return "ASTAnd( " + and.l.accept(this) + " , " + and.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTOr or) {
		return "ASTOr( " + or.l.accept(this) + " , " + or.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTCond cond) {
		return "ASTCond( " + cond.condNode.accept(this) + " , " + cond.thenNode.accept(this) + " , " + cond.elseNode.accept(this) + " )";
	}

	@Override
	public String visit(ASTNot n) {
		return "ASTUnMinus( " + n.v.accept(this) + " )";
	}

	@Override
	public String visit(ASTId id) {
		return "ASTId( " + id.id + " )";
	}
	
	@Override
	public String visit(ASTDecl decl) {
		return "ASTDecl( " + decl.id + " , " + decl.def.accept(this) + " , " + decl.body.accept(this) + " )";
	}
}
