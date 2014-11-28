package semantics;

import ast.*;

public interface Visitor<T> {
	T visit(ASTNum num, IEnv e);
	T visit(ASTBool truth, IEnv e);
	T visit(ASTString astString, IEnv e);
	T visit(ASTPlus plus, IEnv e) throws SemanticException;
	T visit(ASTSub sub, IEnv e) throws SemanticException;
	T visit(ASTMul mul, IEnv e) throws SemanticException;
	T visit(ASTDiv div, IEnv e) throws SemanticException;
	T visit(ASTUnMinus um, IEnv e) throws SemanticException;
	T visit(ASTNot n, IEnv e) throws SemanticException;
	T visit(ASTEq eq, IEnv e) throws SemanticException;
	T visit(ASTNeq neq, IEnv e) throws SemanticException;
	T visit(ASTLs ls, IEnv e) throws SemanticException;
	T visit(ASTGr gr, IEnv e) throws SemanticException;
	T visit(ASTLseq lseq, IEnv e) throws SemanticException;
	T visit(ASTGreq greq, IEnv e) throws SemanticException;
	T visit(ASTAnd and, IEnv e) throws SemanticException;
	T visit(ASTOr or, IEnv e) throws SemanticException;
	T visit(ASTCond cond, IEnv e) throws SemanticException;
	T visit(ASTId id, IEnv e) throws SemanticException;
	T visit(ASTDecl decl, IEnv e) throws SemanticException;
	T visit(ASTAssign astAssign, IEnv e) throws SemanticException;
	T visit(ASTWhile astWhile, IEnv e) throws SemanticException;
	T visit(ASTNew astNew, IEnv e) throws SemanticException;
	T visit(ASTDeref astDeref, IEnv e) throws SemanticException;
	T visit(ASTPrint astPrint, IEnv e) throws SemanticException;
	T visit(ASTPrintln astPrintln, IEnv e) throws SemanticException;
	T visit(ASTSeq astSeq, IEnv e) throws SemanticException;
	T visit(ASTCall astCall, IEnv e) throws SemanticException;
	T visit(ASTFun astFun, IEnv e) throws SemanticException;
	T visit(ASTIf astIf, IEnv e) throws SemanticException;
}
