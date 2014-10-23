package semantics;

import ast.*;

@SuppressWarnings("hiding")
public interface Visitor<IValue> {
	IValue visit(ASTNum num);
	IValue visit(ASTTruth truth) throws Exception;
	IValue visit(ASTPlus plus) throws Exception;
	IValue visit(ASTSub sub) throws Exception;
	IValue visit(ASTMul mul) throws Exception;
	IValue visit(ASTDiv div) throws Exception;
	IValue visit(ASTUnMinus um) throws Exception;
	IValue visit(ASTNot n) throws Exception;
	IValue visit(ASTEq eq) throws Exception;
	IValue visit(ASTNeq neq) throws Exception;
	IValue visit(ASTLs ls) throws Exception;
	IValue visit(ASTGr gr) throws Exception;
	IValue visit(ASTLseq lseq) throws Exception;
	IValue visit(ASTGreq greq) throws Exception;
	IValue visit(ASTAnd and) throws Exception;
	IValue visit(ASTOr or) throws Exception;
	IValue visit(ASTCond cond) throws Exception;
	IValue visit(ASTId id) throws Exception;
	IValue visit(ASTDecl decl) throws Exception;
	IValue visit(ASTAssign astAssign) throws Exception;
	IValue visit(ASTWhile astWhile) throws Exception;
	IValue visit(ASTNew astNew) throws Exception;
	IValue visit(ASTDeref astDeref) throws Exception;
	IValue visit(ASTPrint astPrint) throws Exception;
	IValue visit(ASTPrintln astPrintln) throws Exception;
	IValue visit(ASTString astString);
}
