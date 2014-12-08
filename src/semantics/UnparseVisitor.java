package semantics;

import ast.ASTAnd;
import ast.ASTAssign;
import ast.ASTBool;
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
import ast.ASTNode;
import ast.ASTNot;
import ast.ASTNum;
import ast.ASTOr;
import ast.ASTPlus;
import ast.ASTPrint;
import ast.ASTPrintln;
import ast.ASTSeq;
import ast.ASTString;
import ast.ASTSub;
import ast.ASTUnMinus;
import ast.ASTWhile;

public class UnparseVisitor implements Visitor<String> {

	@Override
	public String visit(ASTNum num, Environment<String> e) {
		return num.toString();
	}

	@Override
	public String visit(ASTBool bool, Environment<String> e) {
		return bool.toString();
	}

	@Override
	public String visit(ASTPlus plus, Environment<String> e) throws SemanticException{
		return "ASTPlus( " + plus.l.accept(this, e) + " , " + plus.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTSub sub, Environment<String> e) throws SemanticException {
		return "ASTSub( " + sub.l.accept(this, e) + " , " + sub.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTMul mul, Environment<String> e) throws SemanticException {
		return "ASTMul( " + mul.l.accept(this, e) + " , " + mul.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTDiv div, Environment<String> e) throws SemanticException {
		return "ASTPlus( " + div.l.accept(this, e) + " , " + div.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTUnMinus um, Environment<String> e) throws SemanticException {
		return "ASTUnMinus( " + um.v.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTEq eq, Environment<String> e) throws SemanticException {
		return "ASTEq( " + eq.l.accept(this, e) + " , " + eq.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTNeq neq, Environment<String> e) throws SemanticException {
		return "ASTNeq( " + neq.l.accept(this, e) + " , " + neq.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTLs ls, Environment<String> e) throws SemanticException {
		return "ASTLs( " + ls.l.accept(this, e) + " , " + ls.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTGr gr, Environment<String> e) throws SemanticException {
		return "ASTGr( " + gr.l.accept(this, e) + " , " + gr.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTLseq lseq, Environment<String> e) throws SemanticException {
		return "ASTLseq( " + lseq.l.accept(this, e) + " , " + lseq.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTGreq greq, Environment<String> e) throws SemanticException {
		return "ASTGreq( " + greq.l.accept(this, e) + " , " + greq.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTAnd and, Environment<String> e) throws SemanticException {
		return "ASTAnd( " + and.l.accept(this, e) + " , " + and.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTOr or, Environment<String> e) throws SemanticException {
		return "ASTOr( " + or.l.accept(this, e) + " , " + or.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTCond cond, Environment<String> e) throws SemanticException {
		return "ASTCond( " + cond.condNode.accept(this, e) + " , " + cond.thenNode.accept(this, e) + " , " + cond.elseNode.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTNot n, Environment<String> e) throws SemanticException {
		return "ASTUnMinus( " + n.v.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTId id, Environment<String> e) throws SemanticException {
		return "ASTId( " + id.id + " )";
	}
	
	@Override
	public String visit(ASTDecl decl, Environment<String> e) throws SemanticException {
		StringBuilder sb = new StringBuilder();
		for (ASTNode def : decl.defs)
			sb.append(def.accept(this, e) + " ");
		return "ASTDecl( " + decl.ids + " , [ " + sb.toString() + " ] , " + decl.body.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTAssign astAssign, Environment<String> e) throws SemanticException {
		return "ASTAssign( " + astAssign.l.accept(this, e) + " , " + astAssign.r.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTWhile astWhile, Environment<String> e) throws SemanticException {
		return "ASTWhile( " + astWhile.c.accept(this, e) + " , " + astWhile.b.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTNew astNew, Environment<String> e) throws SemanticException {
		return "ASTNew( " + astNew.node.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTDeref astDeref, Environment<String> e) throws SemanticException {
		return "ASTDeref( " + astDeref.node.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTPrint astPrint, Environment<String> e) throws SemanticException {
		return "ASTPrint( " + astPrint.node.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTPrintln astPrintln, Environment<String> e) throws SemanticException {
		return "ASTPrintln( " + astPrintln.node.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTString astString, Environment<String> e) {
		return astString.string.toString();
	}

	@Override
	public String visit(ASTSeq astSeq, Environment<String> e) throws SemanticException {
		return "ASTSeq( " + astSeq.f.accept(this, e) + " , " + astSeq.s.accept(this, e) + " )";
	}

	@Override
	public String visit(ASTCall astCall, Environment<String> e) throws SemanticException {
		return "ASTCall( " + astCall.fun.accept(this, e) + " , " + astCall.args.toString() + " )";
	}

	@Override
	public String visit(ASTFun astFun, Environment<String> e) throws SemanticException {
		return "ASTFun( " + astFun.body.accept(this, e) + " , " + astFun.paramNames.toString() + " )";
	}

	@Override
	public String visit(ASTIf astIf, Environment<String> e) throws SemanticException {
		return "ASTIf( " + astIf.condNode.accept(this, null) + " , " +  astIf.thenNode.accept(this, null) + " , " + (astIf.elseNode != null ? astIf.elseNode.accept(this, null) : "no else branch") + " )";
	}
}