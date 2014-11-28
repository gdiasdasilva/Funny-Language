package semantics;

import ast.*;

public class UnparseVisitor implements Visitor<String> {

	@Override
	public String visit(ASTNum num, IEnv e) {
		return num.integer.toString();
	}

	@Override
	public String visit(ASTBool truth, IEnv e) {
		return truth.bool.toString();
	}

	@Override
	public String visit(ASTPlus plus, IEnv e) throws SemanticException{
		return "ASTPlus( " + plus.l.accept(this, e) + " , " + plus.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTSub sub, IEnv e) throws SemanticException {
		return "ASTSub( " + sub.l.accept(this, e) + " , " + sub.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTMul mul, IEnv e) throws SemanticException {
		return "ASTMul( " + mul.l.accept(this, e) + " , " + mul.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTDiv div, IEnv e) throws SemanticException {
		return "ASTPlus( " + div.l.accept(this, e) + " , " + div.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTUnMinus um, IEnv e) throws SemanticException {
		return "ASTUnMinus( " + um.v.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTEq eq, IEnv e) throws SemanticException {
		return "ASTEq( " + eq.l.accept(this, e) + " , " + eq.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTNeq neq, IEnv e) throws SemanticException {
		return "ASTNeq( " + neq.l.accept(this, e) + " , " + neq.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTLs ls, IEnv e) throws SemanticException {
		return "ASTLs( " + ls.l.accept(this, e) + " , " + ls.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTGr gr, IEnv e) throws SemanticException {
		return "ASTGr( " + gr.l.accept(this, e) + " , " + gr.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTLseq lseq, IEnv e) throws SemanticException {
		return "ASTLseq( " + lseq.l.accept(this, e) + " , " + lseq.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTGreq greq, IEnv e) throws SemanticException {
		return "ASTGreq( " + greq.l.accept(this, e) + " , " + greq.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTAnd and, IEnv e) throws SemanticException {
		return "ASTAnd( " + and.l.accept(this, e) + " , " + and.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTOr or, IEnv e) throws SemanticException {
		return "ASTOr( " + or.l.accept(this, e) + " , " + or.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTCond cond, IEnv e) throws SemanticException {
		return "ASTCond( " + cond.condNode.accept(this, e) + " , " + cond.thenNode.accept(this, e) + " , " + cond.elseNode.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTNot n, IEnv e) throws SemanticException {
		return "ASTUnMinus( " + n.v.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTId id, IEnv e) throws SemanticException {
		return "ASTId( " + id.id + " )";
	}
	
	@Override
	public String visit(ASTDecl decl, IEnv e) throws SemanticException {
		StringBuilder sb = new StringBuilder();
		for (ASTNode def : decl.defs)
			sb.append(def.accept(this, e) + " ");
		return "ASTDecl( " + decl.ids + " , [ " + sb.toString() + " ] , " + decl.body.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTAssign astAssign, IEnv e) throws SemanticException {
		return "ASTAssign( " + astAssign.l.accept(this, e) + " , " + astAssign.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTWhile astWhile, IEnv e) throws SemanticException {
		return "ASTWhile( " + astWhile.c.accept(this, e) + " , " + astWhile.b.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTNew astNew, IEnv e) throws SemanticException {
		return "ASTNew( " + astNew.node.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTDeref astDeref, IEnv e) throws SemanticException {
		return "ASTDeref( " + astDeref.node.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTPrint astPrint, IEnv e) throws SemanticException {
		return "ASTPrint( " + astPrint.node.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTPrintln astPrintln, IEnv e) throws SemanticException {
		return "ASTPrintln( " + astPrintln.node.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTString astString, IEnv e) {
		return astString.string.toString();
	}

	@Override
	public String visit(ASTSeq astSeq, IEnv e) throws SemanticException {
		return "ASTSeq( " + astSeq.f.accept(this, e) + " , " + astSeq.s.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTCall astCall, IEnv e) throws SemanticException {
		return "ASTCall( " + astCall.fun.accept(this, e) + " , " + astCall.args.toString() + " )";
	}

	@Override
	public String visit(ASTFun astFun, IEnv e) throws SemanticException {
		return "ASTFun( " + astFun.body.accept(this, e) + " , " + astFun.params.toString() + " )";
	}

	@Override
	public String visit(ASTIf astIf, IEnv e) throws SemanticException {
		return "ASTIf( " + astIf.condNode.accept(this, null) + " , " +  astIf.thenNode.accept(this, null) + " , " + (astIf.elseNode != null ? astIf.elseNode.accept(this, null) : "no else branch") + " )";
	}
}