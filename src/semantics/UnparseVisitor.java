package semantics;

import ast.*;

public class UnparseVisitor implements Visitor<String> {

	@Override
	public String visit(ASTNum num) {
		return num.integer.toString();
	}

	@Override
	public String visit(ASTBool truth) {
		return truth.bool.toString();
	}

	@Override
	public String visit(ASTPlus plus) throws SemanticException{
		return "ASTPlus( " + plus.l.accept(this) + " , " + plus.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTSub sub) throws SemanticException {
		return "ASTSub( " + sub.l.accept(this) + " , " + sub.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTMul mul) throws SemanticException {
		return "ASTMul( " + mul.l.accept(this) + " , " + mul.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTDiv div) throws SemanticException {
		return "ASTPlus( " + div.l.accept(this) + " , " + div.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTUnMinus um) throws SemanticException {
		return "ASTUnMinus( " + um.v.accept(this) + " )";
	}

	@Override
	public String visit(ASTEq eq) throws SemanticException {
		return "ASTEq( " + eq.l.accept(this) + " , " + eq.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTNeq neq) throws SemanticException {
		return "ASTNeq( " + neq.l.accept(this) + " , " + neq.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTLs ls) throws SemanticException {
		return "ASTLs( " + ls.l.accept(this) + " , " + ls.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTGr gr) throws SemanticException {
		return "ASTGr( " + gr.l.accept(this) + " , " + gr.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTLseq lseq) throws SemanticException {
		return "ASTLseq( " + lseq.l.accept(this) + " , " + lseq.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTGreq greq) throws SemanticException {
		return "ASTGreq( " + greq.l.accept(this) + " , " + greq.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTAnd and) throws SemanticException {
		return "ASTAnd( " + and.l.accept(this) + " , " + and.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTOr or) throws SemanticException {
		return "ASTOr( " + or.l.accept(this) + " , " + or.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTCond cond) throws SemanticException {
		return "ASTCond( " + cond.condNode.accept(this) + " , " + cond.thenNode.accept(this) + " , " + cond.elseNode.accept(this) + " )";
	}

	@Override
	public String visit(ASTNot n) throws SemanticException {
		return "ASTUnMinus( " + n.v.accept(this) + " )";
	}

	@Override
	public String visit(ASTId id) throws SemanticException {
		return "ASTId( " + id.id + " )";
	}
	
	@Override
	public String visit(ASTDecl decl) throws SemanticException {
		StringBuilder sb = new StringBuilder();
		for (ASTNode def : decl.defs)
			sb.append(def.accept(this) + " ");
		return "ASTDecl( " + decl.ids + " , [ " + sb.toString() + " ] , " + decl.body.accept(this) + " )";
	}

	@Override
	public String visit(ASTAssign astAssign) throws SemanticException {
		return "ASTAssign( " + astAssign.l.accept(this) + " , " + astAssign.r.accept(this) + " )";
	}

	@Override
	public String visit(ASTWhile astWhile) throws SemanticException {
		return "ASTWhile( " + astWhile.c.accept(this) + " , " + astWhile.b.accept(this) + " )";
	}

	@Override
	public String visit(ASTNew astNew) throws SemanticException {
		return "ASTNew( " + astNew.node.accept(this) + " )";
	}

	@Override
	public String visit(ASTDeref astDeref) throws SemanticException {
		return "ASTDeref( " + astDeref.node.accept(this) + " )";
	}

	@Override
	public String visit(ASTPrint astPrint) throws SemanticException {
		return "ASTPrint( " + astPrint.node.accept(this) + " )";
	}

	@Override
	public String visit(ASTPrintln astPrintln) throws SemanticException {
		return "ASTPrintln( " + astPrintln.node.accept(this) + " )";
	}

	@Override
	public String visit(ASTString astString) {
		return astString.string.toString();
	}

	@Override
	public String visit(ASTSeq astSeq) throws SemanticException {
		return "ASTSeq( " + astSeq.f.accept(this) + " , " + astSeq.s.accept(this) + " )";
	}
}