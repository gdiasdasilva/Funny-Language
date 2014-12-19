package semantics;

import ast.*;

public interface Visitor<T, S> {
	T visit(ASTNum num, Environment<S> e);
	T visit(ASTBool truth, Environment<S> e);
	T visit(ASTString astString, Environment<S> e);
	T visit(ASTPlus plus, Environment<S> e) throws SemanticException;
	T visit(ASTSub sub, Environment<S> e) throws SemanticException;
	T visit(ASTMul mul, Environment<S> e) throws SemanticException;
	T visit(ASTDiv div, Environment<S> e) throws SemanticException;
	T visit(ASTUnMinus um, Environment<S> e) throws SemanticException;
	T visit(ASTNot n, Environment<S> e) throws SemanticException;
	T visit(ASTEq eq, Environment<S> e) throws SemanticException;
	T visit(ASTNeq neq, Environment<S> e) throws SemanticException;
	T visit(ASTLs ls, Environment<S> e) throws SemanticException;
	T visit(ASTGr gr, Environment<S> e) throws SemanticException;
	T visit(ASTLseq lseq, Environment<S> e) throws SemanticException;
	T visit(ASTGreq greq, Environment<S> e) throws SemanticException;
	T visit(ASTAnd and, Environment<S> e) throws SemanticException;
	T visit(ASTOr or, Environment<S> e) throws SemanticException;
	T visit(ASTCond cond, Environment<S> e) throws SemanticException;
	T visit(ASTId id, Environment<S> e) throws SemanticException;
	T visit(ASTDecl decl, Environment<S> e) throws SemanticException;
	T visit(ASTAssign astAssign, Environment<S> e) throws SemanticException;
	T visit(ASTWhile astWhile, Environment<S> e) throws SemanticException;
	T visit(ASTNew astNew, Environment<S> e) throws SemanticException;
	T visit(ASTDeref astDeref, Environment<S> e) throws SemanticException;
	T visit(ASTPrint astPrint, Environment<S> e) throws SemanticException;
	T visit(ASTPrintln astPrintln, Environment<S> e) throws SemanticException;
	T visit(ASTSeq astSeq, Environment<S> e) throws SemanticException;
	T visit(ASTCall astCall, Environment<S> e) throws SemanticException;
	T visit(ASTFun astFun, Environment<S> e) throws SemanticException;
	T visit(ASTIf astIf, Environment<S> e) throws SemanticException;
	T visit(ASTField astField, Environment<S> e) throws SemanticException;
	T visit(ASTRecord astRecord, Environment<S> e) throws SemanticException;
}
