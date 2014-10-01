package semantics;

import ast.ASTAnd;
import ast.ASTCond;
import ast.ASTDiv;
import ast.ASTEq;
import ast.ASTGr;
import ast.ASTGreq;
import ast.ASTLs;
import ast.ASTLseq;
import ast.ASTMul;
import ast.ASTNeq;
import ast.ASTNum;
import ast.ASTOr;
import ast.ASTPlus;
import ast.ASTSub;
import ast.ASTTruth;
import ast.ASTUnMinus;

public interface Visitor<T> {
	T visit(ASTNum num);
	T visit(ASTTruth truth);
	T visit(ASTPlus plus);
	T visit(ASTSub sub);
	T visit(ASTMul mul);
	T visit(ASTDiv div);
	T visit(ASTUnMinus um);
	T visit(ASTEq eq);
	T visit(ASTNeq neq);
	T visit(ASTLs ls);
	T visit(ASTGr gr);
	T visit(ASTLseq lseq);
	T visit(ASTGreq greq);
	T visit(ASTAnd and);
	T visit(ASTOr or);
	T visit(ASTCond cond);
}
