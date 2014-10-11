package semantics;

import ast.*;

public interface Visitor<T> {
	T visit(ASTNum num);
	T visit(ASTTruth truth);
	T visit(ASTPlus plus);
	T visit(ASTSub sub);
	T visit(ASTMul mul);
	T visit(ASTDiv div);
	T visit(ASTUnMinus um);
	T visit(ASTNot n);
	T visit(ASTEq eq);
	T visit(ASTNeq neq);
	T visit(ASTLs ls);
	T visit(ASTGr gr);
	T visit(ASTLseq lseq);
	T visit(ASTGreq greq);
	T visit(ASTAnd and);
	T visit(ASTOr or);
	T visit(ASTCond cond);
	T visit(ASTId id);
	T visit(ASTDecl decl);
}
