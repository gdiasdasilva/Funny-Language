package semantics;

import ast.*;

public interface Visitor<T> {
	T visit(ASTNum num);
	T visit(ASTBool truth);
	T visit(ASTString astString);
	T visit(ASTPlus plus) throws SemanticException;
	T visit(ASTSub sub) throws SemanticException;
	T visit(ASTMul mul) throws SemanticException;
	T visit(ASTDiv div) throws SemanticException;
	T visit(ASTUnMinus um) throws SemanticException;
	T visit(ASTNot n) throws SemanticException;
	T visit(ASTEq eq) throws SemanticException;
	T visit(ASTNeq neq) throws SemanticException;
	T visit(ASTLs ls) throws SemanticException;
	T visit(ASTGr gr) throws SemanticException;
	T visit(ASTLseq lseq) throws SemanticException;
	T visit(ASTGreq greq) throws SemanticException;
	T visit(ASTAnd and) throws SemanticException;
	T visit(ASTOr or) throws SemanticException;
	T visit(ASTCond cond) throws SemanticException;
	T visit(ASTId id) throws SemanticException;
	T visit(ASTDecl decl) throws SemanticException;
	T visit(ASTAssign astAssign) throws SemanticException;
	T visit(ASTWhile astWhile) throws SemanticException;
	T visit(ASTNew astNew) throws SemanticException;
	T visit(ASTDeref astDeref) throws SemanticException;
	T visit(ASTPrint astPrint) throws SemanticException;
	T visit(ASTPrintln astPrintln) throws SemanticException;
	T visit(ASTSeq astSeq) throws SemanticException;
	T visit(ASTCall astCall) throws SemanticException;
}
