package semantics;

import ast.*;

public interface Visitor<T> {
	T visit(ASTNum num, Environment<T> e);
	T visit(ASTBool truth, Environment<T> e);
	T visit(ASTString astString, Environment<T> e);
	T visit(ASTPlus plus, Environment<T> e) throws SemanticException;
	T visit(ASTSub sub, Environment<T> e) throws SemanticException;
	T visit(ASTMul mul, Environment<T> e) throws SemanticException;
	T visit(ASTDiv div, Environment<T> e) throws SemanticException;
	T visit(ASTUnMinus um, Environment<T> e) throws SemanticException;
	T visit(ASTNot n, Environment<T> e) throws SemanticException;
	T visit(ASTEq eq, Environment<T> e) throws SemanticException;
	T visit(ASTNeq neq, Environment<T> e) throws SemanticException;
	T visit(ASTLs ls, Environment<T> e) throws SemanticException;
	T visit(ASTGr gr, Environment<T> e) throws SemanticException;
	T visit(ASTLseq lseq, Environment<T> e) throws SemanticException;
	T visit(ASTGreq greq, Environment<T> e) throws SemanticException;
	T visit(ASTAnd and, Environment<T> e) throws SemanticException;
	T visit(ASTOr or, Environment<T> e) throws SemanticException;
	T visit(ASTCond cond, Environment<T> e) throws SemanticException;
	T visit(ASTId id, Environment<T> e) throws SemanticException;
	T visit(ASTDecl decl, Environment<T> e) throws SemanticException;
	T visit(ASTAssign astAssign, Environment<T> e) throws SemanticException;
	T visit(ASTWhile astWhile, Environment<T> e) throws SemanticException;
	T visit(ASTNew astNew, Environment<T> e) throws SemanticException;
	T visit(ASTDeref astDeref, Environment<T> e) throws SemanticException;
	T visit(ASTPrint astPrint, Environment<T> e) throws SemanticException;
	T visit(ASTPrintln astPrintln, Environment<T> e) throws SemanticException;
	T visit(ASTSeq astSeq, Environment<T> e) throws SemanticException;
	T visit(ASTCall astCall, Environment<T> e) throws SemanticException;
	T visit(ASTFun astFun, Environment<T> e) throws SemanticException;
	T visit(ASTIf astIf, Environment<T> e) throws SemanticException;
}
