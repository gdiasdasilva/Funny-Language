package semantics;

import ast.*;

public interface Visitor<T> {
	T visit(ASTNum num);
	T visit(ASTTruth truth) throws Exception;
	T visit(ASTPlus plus) throws Exception;
	T visit(ASTSub sub) throws Exception;
	T visit(ASTMul mul) throws Exception;
	T visit(ASTDiv div) throws Exception;
	T visit(ASTUnMinus um) throws Exception;
	T visit(ASTNot n) throws Exception;
	T visit(ASTEq eq) throws Exception;
	T visit(ASTNeq neq) throws Exception;
	T visit(ASTLs ls) throws Exception;
	T visit(ASTGr gr) throws Exception;
	T visit(ASTLseq lseq) throws Exception;
	T visit(ASTGreq greq) throws Exception;
	T visit(ASTAnd and) throws Exception;
	T visit(ASTOr or) throws Exception;
	T visit(ASTCond cond) throws Exception;
	T visit(ASTId id) throws Exception;
	T visit(ASTDecl decl) throws Exception;
}
