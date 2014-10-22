package semantics;

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
	public String visit(ASTPlus plus) throws Exception{
		return "ASTPlus( " + plus.l.accept(this) + " , " + plus.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTSub sub) throws Exception {
		return "ASTSub( " + sub.l.accept(this) + " , " + sub.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTMul mul) throws Exception {
		return "ASTMul( " + mul.l.accept(this) + " , " + mul.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTDiv div) throws Exception {
		return "ASTPlus( " + div.l.accept(this) + " , " + div.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTUnMinus um) throws Exception {
		return "ASTUnMinus( " + um.v.accept(this) + " )";
	}

	@Override
	public String visit(ASTEq eq) throws Exception {
		return "ASTEq( " + eq.l.accept(this) + " , " + eq.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTNeq neq) throws Exception {
		return "ASTNeq( " + neq.l.accept(this) + " , " + neq.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTLs ls) throws Exception {
		return "ASTLs( " + ls.l.accept(this) + " , " + ls.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTGr gr) throws Exception {
		return "ASTGr( " + gr.l.accept(this) + " , " + gr.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTLseq lseq) throws Exception {
		return "ASTLseq( " + lseq.l.accept(this) + " , " + lseq.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTGreq greq) throws Exception {
		return "ASTGreq( " + greq.l.accept(this) + " , " + greq.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTAnd and) throws Exception {
		return "ASTAnd( " + and.l.accept(this) + " , " + and.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTOr or) throws Exception {
		return "ASTOr( " + or.l.accept(this) + " , " + or.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTCond cond) throws Exception {
		return "ASTCond( " + cond.condNode.accept(this) + " , " + cond.thenNode.accept(this) + " , " + cond.elseNode.accept(this) + " )";
	}

	@Override
	public String visit(ASTNot n) throws Exception {
		return "ASTUnMinus( " + n.v.accept(this) + " )";
	}

	@Override
	public String visit(ASTId id) throws Exception {
		return "ASTId( " + id.id + " )";
	}
	
	@Override
	public String visit(ASTDecl decl) throws Exception {
//		return "ASTDecl( " + decl.ids + " , " + decl.defs.accept(this) + " , " + decl.body.accept(this) + " )";
		return "ASTDecl( NOT UNPARSING )";
	}

	@Override
	public String visit(ASTAssign astAssign) throws Exception {
		return "ASTAssign( " + astAssign.l.accept(this) + " , " + astAssign.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTWhile astWhile) throws Exception {
		return "ASTWhile( " + astWhile.l.accept(this) + " , " + astWhile.r.accept(this) + " )";
	}
}
