package semantics;

import ast.*;

public class UnparseVisitor implements Visitor<String> {

	@Override
	public String visit(ASTNum num, Environment e) {
		return num.integer.toString();
	}

	@Override
	public String visit(ASTBool truth, Environment e) {
		return truth.bool.toString();
	}

	@Override
	public String visit(ASTPlus plus, Environment e) throws SemanticException{
		return "ASTPlus( " + plus.l.accept(this, e) + " , " + plus.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTSub sub, Environment e) throws SemanticException {
		return "ASTSub( " + sub.l.accept(this, e) + " , " + sub.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTMul mul, Environment e) throws SemanticException {
		return "ASTMul( " + mul.l.accept(this, e) + " , " + mul.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTDiv div, Environment e) throws SemanticException {
		return "ASTPlus( " + div.l.accept(this, e) + " , " + div.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTUnMinus um, Environment e) throws SemanticException {
		return "ASTUnMinus( " + um.v.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTEq eq, Environment e) throws SemanticException {
		return "ASTEq( " + eq.l.accept(this, e) + " , " + eq.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTNeq neq, Environment e) throws SemanticException {
		return "ASTNeq( " + neq.l.accept(this, e) + " , " + neq.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTLs ls, Environment e) throws SemanticException {
		return "ASTLs( " + ls.l.accept(this, e) + " , " + ls.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTGr gr, Environment e) throws SemanticException {
		return "ASTGr( " + gr.l.accept(this, e) + " , " + gr.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTLseq lseq, Environment e) throws SemanticException {
		return "ASTLseq( " + lseq.l.accept(this, e) + " , " + lseq.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTGreq greq, Environment e) throws SemanticException {
		return "ASTGreq( " + greq.l.accept(this, e) + " , " + greq.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTAnd and, Environment e) throws SemanticException {
		return "ASTAnd( " + and.l.accept(this, e) + " , " + and.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTOr or, Environment e) throws SemanticException {
		return "ASTOr( " + or.l.accept(this, e) + " , " + or.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTCond cond, Environment e) throws SemanticException {
		return "ASTCond( " + cond.condNode.accept(this, e) + " , " + cond.thenNode.accept(this, e) + " , " + cond.elseNode.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTNot n, Environment e) throws SemanticException {
		return "ASTUnMinus( " + n.v.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTId id, Environment e) throws SemanticException {
		return "ASTId( " + id.id + " )";
	}
	
	@Override
	public String visit(ASTDecl decl, Environment e) throws SemanticException {
		StringBuilder sb = new StringBuilder();
		for (ASTNode def : decl.defs)
			sb.append(def.accept(this, e) + " ");
		return "ASTDecl( " + decl.ids + " , [ " + sb.toString() + " ] , " + decl.body.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTAssign astAssign, Environment e) throws SemanticException {
		return "ASTAssign( " + astAssign.l.accept(this, e) + " , " + astAssign.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTWhile astWhile, Environment e) throws SemanticException {
		return "ASTWhile( " + astWhile.c.accept(this, e) + " , " + astWhile.b.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTNew astNew, Environment e) throws SemanticException {
		return "ASTNew( " + astNew.node.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTDeref astDeref, Environment e) throws SemanticException {
		return "ASTDeref( " + astDeref.node.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTPrint astPrint, Environment e) throws SemanticException {
		return "ASTPrint( " + astPrint.node.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTPrintln astPrintln, Environment e) throws SemanticException {
		return "ASTPrintln( " + astPrintln.node.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTString astString, Environment e) {
		return astString.string.toString();
	}

	@Override
	public String visit(ASTSeq astSeq, Environment e) throws SemanticException {
		return "ASTSeq( " + astSeq.f.accept(this, e) + " , " + astSeq.s.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTCall astCall, Environment e) throws SemanticException {
		return "ASTCall( " + astCall.fun.accept(this, e) + " , " + astCall.args.toString() + " )";
	}

	@Override
	public String visit(ASTFun astFun, Environment e) throws SemanticException {
		return "ASTFun( " + astFun.body.accept(this, e) + " , " + astFun.params.toString() + " )";
	}

	@Override
	public String visit(ASTIf astIf, Environment e) throws SemanticException {
		return "ASTIf( " + astIf.condNode.accept(this, null) + " , " +  astIf.thenNode.accept(this, null) + " , " + (astIf.elseNode != null ? astIf.elseNode.accept(this, null) : "no else branch") + " )";
	}
}